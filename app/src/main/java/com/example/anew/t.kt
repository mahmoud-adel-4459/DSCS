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

class t : AppCompatActivity() {
    private lateinit var getVideoT: Button
    private lateinit var video20: VideoView
    private lateinit var fab20 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "t.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_t)



        getVideoT = findViewById(R.id.getVideoT)
        video20 = findViewById(R.id.video20)
        fab20 = findViewById(R.id.fab20)


        getVideoT.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video20.setVideoURI(Uri.parse(videoUri))
                video20.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video20.start()
                }
                video20.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab20.setOnClickListener {
            val intent = Intent(this@t, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
