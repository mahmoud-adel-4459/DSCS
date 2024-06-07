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

class y : AppCompatActivity() {
    private lateinit var getVideoY: Button
    private lateinit var video25: VideoView
    private lateinit var fab25 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "y.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_y)



        getVideoY = findViewById(R.id.getVideoY)
        video25 = findViewById(R.id.video25)
        fab25 = findViewById(R.id.fab25)


        getVideoY.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video25.setVideoURI(Uri.parse(videoUri))
                video25.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video25.start()
                }
                video25.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab25.setOnClickListener {
            val intent = Intent(this@y, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
