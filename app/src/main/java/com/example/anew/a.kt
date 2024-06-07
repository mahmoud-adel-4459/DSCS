package com.example.anew
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.storage.FirebaseStorage

class a : AppCompatActivity() {
    private lateinit var getVideoA: Button
    private lateinit var video1: VideoView
    private lateinit var fab1 : FloatingActionButton

    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "a.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)



        getVideoA = findViewById(R.id.getVideoA)
        video1 = findViewById(R.id.video1)
        fab1 = findViewById(R.id.fab1)


        getVideoA.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video1.setVideoURI(Uri.parse(videoUri))
                video1.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video1.start()
                }
                video1.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab1.setOnClickListener {
            val intent = Intent(this@a, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
