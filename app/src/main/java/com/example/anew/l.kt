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

class l : AppCompatActivity() {
    private lateinit var getVideoL: Button
    private lateinit var video12: VideoView
    private lateinit var fab12 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "l.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l)



        getVideoL = findViewById(R.id.getVideoL)
        video12 = findViewById(R.id.video12)
        fab12 = findViewById(R.id.fab12)


        getVideoL.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video12.setVideoURI(Uri.parse(videoUri))
                video12.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video12.start()
                }
                video12.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab12.setOnClickListener {
            val intent = Intent(this@l, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
