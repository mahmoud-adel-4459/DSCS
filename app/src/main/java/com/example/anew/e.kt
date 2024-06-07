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

class e : AppCompatActivity() {
    private lateinit var getVideoE: Button
    private lateinit var video5: VideoView
    private lateinit var fab5 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "e.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e)



        getVideoE = findViewById(R.id.getVideoE)
        video5 = findViewById(R.id.video5)
        fab5 = findViewById(R.id.fab5)


        getVideoE.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video5.setVideoURI(Uri.parse(videoUri))
                video5.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video5.start()
                }
                video5.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab5.setOnClickListener {
            val intent = Intent(this@e, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
