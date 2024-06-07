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

class n : AppCompatActivity() {
    private lateinit var getVideoN: Button
    private lateinit var video14: VideoView
    private lateinit var fab14 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "n.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_n)



        getVideoN = findViewById(R.id.getVideoN)
        video14 = findViewById(R.id.video14)
        fab14 = findViewById(R.id.fab14)


        getVideoN.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video14.setVideoURI(Uri.parse(videoUri))
                video14.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video14.start()
                }
                video14.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab14.setOnClickListener {
            val intent = Intent(this@n, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
