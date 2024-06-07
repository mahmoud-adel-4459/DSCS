package com.example.anew

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class SignUp : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var do_have_account:TextView
    private lateinit var SignUpButton:Button
    private lateinit var actionSU:FloatingActionButton
    private lateinit var remember1: CheckBox
    private lateinit var googleImg1:ImageView
    private lateinit var email1:TextInputLayout
    private lateinit var password1:TextInputLayout
    private lateinit var emailSignUp: TextInputEditText
    private lateinit var passwordSignUp: TextInputEditText
    private lateinit var resetPassword:TextView
    var mAuth: FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        initialization()

        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient= GoogleSignIn.getClient(this,gso)

        googleImg1.setOnClickListener {
            signInWithGoogle()
        }

        actionSU.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        do_have_account.setOnClickListener {
            val intent=Intent(this,SingIn::class.java)
            startActivity(intent)
        }

        mAuth=FirebaseAuth.getInstance()

        remember1.setOnClickListener {
            if (validateEmail(emailSignUp,email1) && validatePassword(passwordSignUp, password1)){
                signUp()
            }
        }

        passwordSignUp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validatePassword(passwordSignUp,password1)
            }
        })

        emailSignUp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validateEmail(emailSignUp,email1)
            }

        })

        resetPassword.setOnClickListener {
            val intent=Intent(this,ResetPassword::class.java)
            startActivity(intent)
        }

    }

    private fun validateEmail(emailSignUp:TextInputEditText, email1: TextInputLayout) : Boolean {
        val emailPattern=Regex("[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")
        return when{
            emailSignUp.text.toString().trim().isEmpty() -> {
                email1.error="Required"
                false
            }
            !emailSignUp.text.toString().trim().matches(emailPattern) -> {
                email1.error=null
                false
            }
            else -> {
                email1.error=null
                true
            }
        }
    }


    private fun validatePassword(passwordSignUp: TextInputEditText, password1: TextInputLayout): Boolean {
        val password = passwordSignUp.text.toString().trim()

        // Check if the password is empty
        if (password.isEmpty()) {
            password1.error = "Required"
            return false
        }

        // Check password length (at least 8 characters)
        if (password.length < 8) {
            password1.error = "At least 8 characters long"
            return false
        }

        // Check if the password contains at least one digit
        if (!password.any { it.isDigit() }) {
            password1.error = "At least one digit"
            return false
        }

        // Check if the password contains at least one uppercase letter
        if (!password.any { it.isUpperCase() }) {
            password1.error = "At least one uppercase letter"
            return false
        }

        // Check if the password contains at least one lowercase letter
        if (!password.any { it.isLowerCase() }) {
            password1.error = "At least one lowercase letter"
            return false
        }

        // Check if the password contains at least one special character
        if (password.all { it.isLetterOrDigit() }) {
            password1.error = "At least one special character"
            return false
        }

        // Password meets all requirements
        password1.error = "Strong Password"
        return true
    }


    private fun signInWithGoogle(){
        val signInIntent=googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if (result.resultCode== Activity.RESULT_OK){
            val task=GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResult(task)
        }
    }

    private fun handleResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account: GoogleSignInAccount?=task.result
            if (account!=null){
                updateUI(account)
            }
        }
        else{
            Toast.makeText(applicationContext, "SignIn Failed , Try again later!", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential= GoogleAuthProvider.getCredential(account.idToken,null)
        mAuth!!.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val intent=Intent(this,FirstScreen::class.java)
                startActivity(intent)
            } else{
                Toast.makeText(applicationContext, "Can't Login!", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun initialization(){
        googleImg1=findViewById(R.id.googleImg1)
        do_have_account=findViewById(R.id.do_have_account_TV)
        SignUpButton=findViewById(R.id.SignUpBtn)
        remember1=findViewById(R.id.remember1)
        emailSignUp=findViewById(R.id.email_ed1) //مكان كتابه الايميل
        resetPassword=findViewById(R.id.forgetPasswordSingUp)
        passwordSignUp=findViewById(R.id.password_ed1) //مكان كتابه الباسورد
        actionSU=findViewById(R.id.actionSU)
        email1=findViewById(R.id.emailL_ed1) //مكام كتابه ان الايميل صح
        password1=findViewById(R.id.passwordL_ed1) //مكان كتابه ان الباسورد قويه
    }



    private fun signUp() {
        val email = emailSignUp.text.toString()
        val password = passwordSignUp.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            mAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    sendEmailVerification()
//                    Toast.makeText(applicationContext, "login successfully", Toast.LENGTH_LONG).show()
//                    SignUpButton.setOnClickListener {
//                        val intent=Intent(this,FirstScreen::class.java)
//                        startActivity(intent)
//                    }
                } else {
                    Toast.makeText(applicationContext, it.exception.toString(), Toast.LENGTH_LONG).show()
                    SignUpButton.isEnabled = false
                }
            }
        } else {
            Toast.makeText(applicationContext, "Empty Email or Password", Toast.LENGTH_LONG).show()
            SignUpButton.isEnabled = false
        }
    }

    private fun sendEmailVerification() {
        val user=mAuth?.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener {
            if (it.isSuccessful){
                val intent=Intent(this,SingIn::class.java)
                startActivity(intent)
                Toast.makeText(this,"Please check your Email",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, it.exception.toString(), Toast.LENGTH_LONG).show()
                SignUpButton.isEnabled = false
            }
        }
    }
}