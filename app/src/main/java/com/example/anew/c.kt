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

class c : AppCompatActivity() {
    private lateinit var getVideoC: Button
    private lateinit var video3: VideoView
    private lateinit var fab3 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "c.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)



        getVideoC = findViewById(R.id.getVideoC)
        video3 = findViewById(R.id.video3)
        fab3 = findViewById(R.id.fab3)


        getVideoC.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video3.setVideoURI(Uri.parse(videoUri))
                video3.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video3.start()
                }
                video3.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab3.setOnClickListener {
            val intent = Intent(this@c, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
