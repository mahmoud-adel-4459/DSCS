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

class v : AppCompatActivity() {
    private lateinit var getVideoV: Button
    private lateinit var video22: VideoView
    private lateinit var fab22 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "v.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_v)



        getVideoV = findViewById(R.id.getVideoV)
        video22 = findViewById(R.id.video22)
        fab22 = findViewById(R.id.fab22)


        getVideoV.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video22.setVideoURI(Uri.parse(videoUri))
                video22.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video22.start()
                }
                video22.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab22.setOnClickListener {
            val intent = Intent(this@v, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
