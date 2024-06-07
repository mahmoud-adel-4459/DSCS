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

class w : AppCompatActivity() {
    private lateinit var getVideoW: Button
    private lateinit var video23: VideoView
    private lateinit var fab23 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "w.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_w)



        getVideoW = findViewById(R.id.getVideoW)
        video23 = findViewById(R.id.video23)
        fab23 = findViewById(R.id.fab23)


        getVideoW.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video23.setVideoURI(Uri.parse(videoUri))
                video23.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video23.start()
                }
                video23.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab23.setOnClickListener {
            val intent = Intent(this@w, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
