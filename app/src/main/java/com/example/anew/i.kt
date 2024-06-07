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

class i : AppCompatActivity() {
    private lateinit var getVideoI: Button
    private lateinit var video9: VideoView
    private lateinit var fab9 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "i.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_i)



        getVideoI = findViewById(R.id.getVideoI)
        video9 = findViewById(R.id.video9)
        fab9 = findViewById(R.id.fab9)


        getVideoI.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video9.setVideoURI(Uri.parse(videoUri))
                video9.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video9.start()
                }
                video9.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab9.setOnClickListener {
            val intent = Intent(this@i, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
