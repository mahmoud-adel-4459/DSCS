package com.example.anew

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
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


class SingIn : AppCompatActivity() {
    private lateinit var googleSignInClient:GoogleSignInClient
    private lateinit var do_not_have_account:TextView
    private lateinit var LogInBtn:Button
    private lateinit var actionSI:FloatingActionButton
    //    private lateinit var remember:CheckBox
    private lateinit var emailSignIn:TextInputEditText
    private lateinit var googleImg:ImageView
    private lateinit var passwordSignIn:TextInputEditText
    private lateinit var passwordSignInL:TextInputLayout
    private lateinit var emailSignInL:TextInputLayout
    private lateinit var resetPassword:TextView
    var mAuth:FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sing_in)

        mAuth=FirebaseAuth.getInstance()

        initialization()

        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient=GoogleSignIn.getClient(this,gso)


        do_not_have_account.setOnClickListener {
            val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        actionSI.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        LogInBtn.setOnClickListener {
            if (validateEmail(emailSignIn,emailSignInL) && validatePassword(passwordSignIn,passwordSignInL)){
                login()
            }
        }

        resetPassword.setOnClickListener {
            val intent=Intent(this,ResetPassword::class.java)
            startActivity(intent)
        }
        googleImg.setOnClickListener {
            signInWithGoogle()
        }

        passwordSignIn.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validatePassword(passwordSignIn,passwordSignInL)
            }
        })

        emailSignIn.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                validateEmail(emailSignIn,emailSignInL)
            }

        })

    }

    private fun validateEmail(emailSignIn: TextInputEditText, emailSignInL: TextInputLayout) : Boolean {
        val emailPattern=Regex("[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")
        return when{
            emailSignIn.text.toString().trim().isEmpty() -> {
                emailSignInL.error="Required"
                false
            }
            !emailSignIn.text.toString().trim().matches(emailPattern) -> {
                emailSignInL.error=null
                false
            }
            else -> {
                emailSignInL.error=null
                true
            }
        }
    }

    private fun validatePassword(passwordSignIn: TextInputEditText, passwordSignInL: TextInputLayout): Boolean {
        val password = passwordSignIn.text.toString().trim()

        // Check if the password is empty
        if (password.isEmpty()) {
            passwordSignInL.error = "Required"
            return false
        }

        // Check password length (at least 8 characters)
        if (password.length < 8) {
            passwordSignInL.error = "At least 8 characters long"
            return false
        }

        // Check if the password contains at least one digit
        if (!password.any { it.isDigit() }) {
            passwordSignInL.error = "At least one digit"
            return false
        }

        // Check if the password contains at least one uppercase letter
        if (!password.any { it.isUpperCase() }) {
            passwordSignInL.error = "At least one uppercase letter"
            return false
        }

        // Check if the password contains at least one lowercase letter
        if (!password.any { it.isLowerCase() }) {
            passwordSignInL.error = "At least one lowercase letter"
            return false
        }

        // Check if the password contains at least one special character
        if (password.all { it.isLetterOrDigit() }) {
            passwordSignInL.error = "At least one special character"
            return false
        }

        // Password meets all requirements
        passwordSignInL.error = "Strong Password"
        return true
    }


//    private fun validatePassword(passwordSignIn: TextInputEditText, passwordSignInL: TextInputLayout) : Boolean {
//        return when{
//            passwordSignIn.text.toString().trim().isEmpty() -> {
//                passwordSignInL.error="Required"
//                false
//            }
//            passwordSignIn.text.toString().trim().length<6||passwordSignIn.text.toString().trim().length>8 -> {
//                passwordSignInL.error="Password must be 6 to 8 character!"
//                false
//            }
//            else -> {
//                passwordSignInL.error=null
//                true
//            }
//        }
//    }


    private fun signInWithGoogle(){
        val signInIntent=googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if (result.resultCode==Activity.RESULT_OK){
            val task=GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResult(task)
        }
    }

    private fun handleResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account:GoogleSignInAccount?=task.result
            if (account!=null){
                updateUI(account)
            }
        }
        else{
            Toast.makeText(applicationContext, "SignIn Failed , Try again later!", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential=GoogleAuthProvider.getCredential(account.idToken,null)
        mAuth!!.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val intent=Intent(this,FirstScreen::class.java)
                startActivity(intent)
            } else{
                Toast.makeText(applicationContext, "Can't LogIn!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun login(){
        val email1=emailSignIn.text.toString()
        val password1=passwordSignIn.text.toString()
        mAuth?.signInWithEmailAndPassword(email1,password1)?.addOnCompleteListener {
            if (it.isSuccessful) {
                verifyEmailAddress()
            } else {
                Toast.makeText(applicationContext, "Can't find your Email", Toast.LENGTH_LONG).show()
                val intent=Intent(this,SignUp::class.java)
                startActivity(intent)
            }
        }

    }

    private fun initialization(){
        do_not_have_account=findViewById(R.id.do_not_have_account_TV)
        actionSI=findViewById(R.id.actionSI)
        LogInBtn=findViewById(R.id.loginBtn)
        googleImg=findViewById(R.id.googleImg)
//        remember=findViewById(R.id.remember)
        emailSignIn=findViewById(R.id.email_ed)
        resetPassword=findViewById(R.id.forgetPasswordSingIn)
        passwordSignIn=findViewById(R.id.password_ed)
        emailSignInL=findViewById(R.id.emailL_ed)
        passwordSignInL=findViewById(R.id.passwordL_ed)
    }

    private fun verifyEmailAddress(){
        val user=mAuth?.currentUser
        if (user!!.isEmailVerified) {
            Toast.makeText(applicationContext, "login successfully", Toast.LENGTH_LONG).show()
            val intent=Intent(this,FirstScreen::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "Your Account is not verified", Toast.LENGTH_LONG).show()
        }
    }

}