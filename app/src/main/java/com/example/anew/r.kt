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

class r : AppCompatActivity() {
    private lateinit var getVideoR: Button
    private lateinit var video18: VideoView
    private lateinit var fab18 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "r.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_r)



        getVideoR = findViewById(R.id.getVideoR)
        video18 = findViewById(R.id.video18)
        fab18 = findViewById(R.id.fab18)


        getVideoR.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video18.setVideoURI(Uri.parse(videoUri))
                video18.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video18.start()
                }
                video18.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab18.setOnClickListener {
            val intent = Intent(this@r, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
