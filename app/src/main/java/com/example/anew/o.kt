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

class o : AppCompatActivity() {
    private lateinit var getVideoO: Button
    private lateinit var video15: VideoView
    private lateinit var fab15 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "o.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o)



        getVideoO = findViewById(R.id.getVideoO)
        video15 = findViewById(R.id.video15)
        fab15 = findViewById(R.id.fab15)


        getVideoO.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video15.setVideoURI(Uri.parse(videoUri))
                video15.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video15.start()
                }
                video15.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab15.setOnClickListener {
            val intent = Intent(this@o, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
