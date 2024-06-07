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

class p : AppCompatActivity() {
    private lateinit var getVideoP: Button
    private lateinit var video16: VideoView
    private lateinit var fab16 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "p.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p)



        getVideoP = findViewById(R.id.getVideoP)
        video16 = findViewById(R.id.video16)
        fab16 = findViewById(R.id.fab16)


        getVideoP.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video16.setVideoURI(Uri.parse(videoUri))
                video16.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video16.start()
                }
                video16.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab16.setOnClickListener {
            val intent = Intent(this@p, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
