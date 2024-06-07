@file:Suppress("DEPRECATION")

package com.example.anew

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.anew.databinding.ActivityTextToSignBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.storage.FirebaseStorage

class TextToSign : AppCompatActivity() {

    private lateinit var floatingActionButton1 : FloatingActionButton
    private lateinit var floatingActionButton2 : FloatingActionButton
    private lateinit var video: VideoView
    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private lateinit var clearButton2: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTextToSignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        floatingActionButton1 = findViewById(R.id.floatingActionButton1)
        floatingActionButton2 = findViewById(R.id.floatingActionButton2)
        video = binding.video
        editText = binding.edittext
        textView = binding.textView3
        clearButton2 = binding.clearbutton2

        binding.getImage.setOnClickListener {
            val progressDialog = ProgressDialog(this@TextToSign)
            progressDialog.setMessage("Fetching GIF...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val imageName = editText.text.toString()
            val storageRef = FirebaseStorage.getInstance().reference.child("images/$imageName.mp4")

            storageRef.downloadUrl.addOnSuccessListener { uri ->
                val gifUri = uri.toString()
                video.setVideoURI(Uri.parse(gifUri))
                video.setOnPreparedListener { mediaPlayer ->
                    progressDialog.dismiss()
                    mediaPlayer.isLooping = true
                    mediaPlayer.start()
                }
            }.addOnFailureListener { exception ->
                progressDialog.dismiss()
                Toast.makeText(
                    this@TextToSign,
                    "Failed to retrieve the GIF: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        clearButton2.setOnClickListener {

            editText.setText("")

        }
        floatingActionButton1.setOnClickListener {
            val intent = Intent(this@TextToSign, FirstScreen::class.java)
            startActivity(intent)
        }
        floatingActionButton2.setOnClickListener {
            val intent = Intent(this@TextToSign, LearnSign::class.java)
            startActivity(intent)
        }
    }
}