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

class z : AppCompatActivity() {
    private lateinit var getVideoZ: Button
    private lateinit var video26: VideoView
    private lateinit var fab26 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "z.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_z)



        getVideoZ= findViewById(R.id.getVideoZ)
        video26 = findViewById(R.id.video26)
        fab26 = findViewById(R.id.fab26)


        getVideoZ.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video26.setVideoURI(Uri.parse(videoUri))
                video26.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video26.start()
                }
                video26.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener{
            }
        }

        fab26.setOnClickListener {
            val intent = Intent(this@z, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
