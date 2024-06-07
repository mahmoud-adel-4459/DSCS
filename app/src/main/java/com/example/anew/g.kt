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

class g : AppCompatActivity() {
    private lateinit var getVideoG: Button
    private lateinit var video7: VideoView
    private lateinit var fab7 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "g.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_g)



        getVideoG = findViewById(R.id.getVideoG)
        video7 = findViewById(R.id.video7)
        fab7 = findViewById(R.id.fab7)


        getVideoG.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video7.setVideoURI(Uri.parse(videoUri))
                video7.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video7.start()
                }
                video7.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab7.setOnClickListener {
            val intent = Intent(this@g, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
