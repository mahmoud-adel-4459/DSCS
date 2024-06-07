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

class x : AppCompatActivity() {
    private lateinit var getVideoX: Button
    private lateinit var video24: VideoView
    private lateinit var fab24 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "x.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_x)



        getVideoX = findViewById(R.id.getVideoX)
        video24 = findViewById(R.id.video24)
        fab24 = findViewById(R.id.fab24)


        getVideoX.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video24.setVideoURI(Uri.parse(videoUri))
                video24.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video24.start()
                }
                video24.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab24.setOnClickListener {
            val intent = Intent(this@x, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
