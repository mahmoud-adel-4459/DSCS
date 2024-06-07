package com.example.anew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class FirstScreen : AppCompatActivity() {

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var logout: TextView
    private var mAuth: FirebaseAuth? = null
    private lateinit var profileInfo:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first_screen)

        init()


        mAuth = FirebaseAuth.getInstance()

        btn1.setOnClickListener {
            val intent = Intent(this, TextToSign::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            val intent = Intent(this, LearnSign::class.java)
            startActivity(intent)
        }


        btn3.setOnClickListener {
            val intent = Intent(this, gl::class.java)
            startActivity(intent)
        }


        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

         profileInfo.setOnClickListener {
        val intent=Intent(this,UserProfile::class.java)
        startActivity(intent)
        }

}


private fun init() {
        logout = findViewById(R.id.logout_tv)
        profileInfo=findViewById(R.id.profile_info_tv)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)

    }
}

