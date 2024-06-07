package com.example.anew

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class gl : AppCompatActivity() {

    private lateinit var buttonRightHand: Button
    private lateinit var buttonLeftHand: Button
    private lateinit var buttonTwoHand: Button
    private lateinit var floatingActionButton10 : FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gl)

        init()

        buttonRightHand.setOnClickListener {
            val intent = Intent(this,right_hand::class.java)
            startActivity(intent)
        }

        buttonLeftHand.setOnClickListener {
            val intent = Intent(this, left_hand::class.java)
            startActivity(intent)
        }


        buttonTwoHand.setOnClickListener {
            val intent = Intent(this, two_hand::class.java)
            startActivity(intent)
        }
        floatingActionButton10.setOnClickListener {
            val intent = Intent(this@gl, FirstScreen::class.java)
            startActivity(intent)
        }

    }
    private fun init() {

            buttonRightHand = findViewById(R.id.buttonRightHand)
            buttonLeftHand = findViewById(R.id.buttonLeftHand)
            buttonTwoHand = findViewById(R.id.buttonTwoHand)
        floatingActionButton10 = findViewById(R.id.floatingActionButton10)

        }
    }
