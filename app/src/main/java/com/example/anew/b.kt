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

class b : AppCompatActivity() {
    private lateinit var getVideoB: Button
    private lateinit var video2: VideoView
    private lateinit var fab2 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "b.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)



        getVideoB = findViewById(R.id.getVideoB)
        video2 = findViewById(R.id.video2)
        fab2 = findViewById(R.id.fab2)


        getVideoB.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video2.setVideoURI(Uri.parse(videoUri))
                video2.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video2.start()
                }
                video2.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab2.setOnClickListener {
            val intent = Intent(this@b, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
