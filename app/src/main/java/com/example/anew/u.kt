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

class u : AppCompatActivity() {
    private lateinit var getVideoU: Button
    private lateinit var video21: VideoView
    private lateinit var fab21 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "u.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u)



        getVideoU = findViewById(R.id.getVideoU)
        video21 = findViewById(R.id.video21)
        fab21 = findViewById(R.id.fab21)


        getVideoU.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video21.setVideoURI(Uri.parse(videoUri))
                video21.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video21.start()
                }
                video21.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab21.setOnClickListener {
            val intent = Intent(this@u, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
