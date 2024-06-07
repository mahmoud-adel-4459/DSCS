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

class j : AppCompatActivity() {
    private lateinit var getVideoJ: Button
    private lateinit var video10: VideoView
    private lateinit var fab10 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "j.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_j)



        getVideoJ = findViewById(R.id.getVideoJ)
        video10 = findViewById(R.id.video10)
        fab10 = findViewById(R.id.fab10)


        getVideoJ.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video10.setVideoURI(Uri.parse(videoUri))
                video10.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video10.start()
                }
                video10.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab10.setOnClickListener {
            val intent = Intent(this@j, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
