/*package com.example.anew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class com.example.anew.MainActivity : AppCompatActivity() {

    private lateinit var SignUp:Button
    private lateinit var logInBtn:Button

    private var mAuth: FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initialization()
        SignUp.setOnClickListener {
            val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        logInBtn.setOnClickListener {
            val intent=Intent(this,SingIn::class.java)
            startActivity(intent)
        }

        mAuth=FirebaseAuth.getInstance()

    }

    private fun initialization(){
        SignUp=findViewById(R.id.SignUp)
        logInBtn=findViewById(R.id.LogInBtn)
    }

    override fun onStart() {
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth!!.currentUser
        if(currentUser != null){
            val i = Intent(this@com.example.anew.MainActivity,FirstScreen::class.java)
            startActivity(i)
        }
        super.onStart()
    }

}*/

package com.example.anew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var SignUpBtn:Button
    private lateinit var logInBtn:Button

    private var mAuth: FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initialization()
        SignUpBtn.setOnClickListener {
            val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        logInBtn.setOnClickListener {
            val intent=Intent(this,SingIn::class.java)
            startActivity(intent)
        }

        mAuth=FirebaseAuth.getInstance()

    }

    private fun initialization(){
        SignUpBtn=findViewById(R.id.SignUpBtn)
        logInBtn=findViewById(R.id.LogInBtn)
    }

    override fun onStart() {
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth!!.currentUser
        if(currentUser != null){
            val i = Intent(this@MainActivity,FirstScreen::class.java)
            startActivity(i)
        }
        super.onStart()
    }

}

