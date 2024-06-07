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

class s : AppCompatActivity() {
    private lateinit var getVideoS: Button
    private lateinit var video19: VideoView
    private lateinit var fab19 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "s.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s)



        getVideoS = findViewById(R.id.getVideoS)
        video19 = findViewById(R.id.video19)
        fab19 = findViewById(R.id.fab19)


        getVideoS.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video19.setVideoURI(Uri.parse(videoUri))
                video19.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video19.start()
                }
                video19.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab19.setOnClickListener {
            val intent = Intent(this@s, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
