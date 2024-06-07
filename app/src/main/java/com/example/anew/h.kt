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

class h : AppCompatActivity() {
    private lateinit var getVideoH: Button
    private lateinit var video8: VideoView
    private lateinit var fab8 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "h.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h)



        getVideoH = findViewById(R.id.getVideoH)
        video8 = findViewById(R.id.video8)
        fab8 = findViewById(R.id.fab8)


        getVideoH.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video8.setVideoURI(Uri.parse(videoUri))
                video8.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video8.start()
                }
                video8.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab8.setOnClickListener {
            val intent = Intent(this@h, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
