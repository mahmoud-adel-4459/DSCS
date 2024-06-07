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

class m : AppCompatActivity() {
    private lateinit var getVideoM: Button
    private lateinit var video13: VideoView
    private lateinit var fab13 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "m.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m)



        getVideoM = findViewById(R.id.getVideoM)
        video13 = findViewById(R.id.video13)
        fab13 = findViewById(R.id.fab13)


        getVideoM.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video13.setVideoURI(Uri.parse(videoUri))
                video13.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video13.start()
                }
                video13.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab13.setOnClickListener {
            val intent = Intent(this@m, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
