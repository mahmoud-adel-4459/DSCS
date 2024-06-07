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

class q : AppCompatActivity() {
    private lateinit var getVideoQ: Button
    private lateinit var video17: VideoView
    private lateinit var fab17 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "q.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q)



        getVideoQ = findViewById(R.id.getVideoB)
        video17 = findViewById(R.id.video17)
        fab17 = findViewById(R.id.fab17)


        getVideoQ.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video17.setVideoURI(Uri.parse(videoUri))
                video17.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video17.start()
                }
                video17.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab17.setOnClickListener {
            val intent = Intent(this@q, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
