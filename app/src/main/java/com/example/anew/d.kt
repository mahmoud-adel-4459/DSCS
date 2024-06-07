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

class d : AppCompatActivity() {
    private lateinit var getVideoD: Button
    private lateinit var video4: VideoView
    private lateinit var fab4 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "d.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d)



        getVideoD = findViewById(R.id.getVideoD)
        video4 = findViewById(R.id.video4)
        fab4 = findViewById(R.id.fab4)


        getVideoD.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video4.setVideoURI(Uri.parse(videoUri))
                video4.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video4.start()
                }
                video4.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab4.setOnClickListener {
            val intent = Intent(this@d, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
