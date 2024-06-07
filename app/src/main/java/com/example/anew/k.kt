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

class k : AppCompatActivity() {
    private lateinit var getVideoK: Button
    private lateinit var video11: VideoView
    private lateinit var fab11 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "k.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_k)



        getVideoK = findViewById(R.id.getVideoK)
        video11 = findViewById(R.id.video11)
        fab11 = findViewById(R.id.fab11)


        getVideoK.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video11.setVideoURI(Uri.parse(videoUri))
                video11.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video11.start()
                }
                video11.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab11.setOnClickListener {
            val intent = Intent(this@k, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
