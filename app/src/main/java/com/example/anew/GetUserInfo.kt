package com.example.anew

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.anew.databinding.ActivityGetUserInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class GetUserInfo : AppCompatActivity() {
    private lateinit var binding: ActivityGetUserInfoBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var user:Users
    private lateinit var uid:String
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGetUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()
        uid=auth.currentUser?.uid.toString()
        databaseReference=FirebaseDatabase.getInstance().getReference("Users")
        if (uid.isNotEmpty()) {
            getUserData()
        }

        val currentUser=FirebaseAuth.getInstance().currentUser
        if(currentUser != null){
            val email=currentUser.email
            binding.emailTxt.text="Email : ${email}"
        }

//        binding.backButtonUserInfo.setOnClickListener {
//            val intent= Intent(this,UserProfile::class.java)
//            startActivity(intent)
//        }

        binding.fabGetInfo.setOnClickListener {
            val intent= Intent(this,UserProfile::class.java)
            startActivity(intent)
        }
    }

    private fun getUserData() {
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener{
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                user=snapshot.getValue(Users::class.java)!!
                binding.getFullName.text = user.firstName + " " + user.lastName
                binding.getAge.text = user.age
//                binding.bio1.text = user.bio
                getUserProfile()
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@GetUserInfo,"Failed to get the Data",Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getUserProfile() {
        storageReference=FirebaseStorage.getInstance().reference.child("Users/$uid.jpeg")
        val localFile=File.createTempFile("tempImage","jpeg")
        storageReference.getFile(localFile).addOnSuccessListener {
            val bitmap=BitmapFactory.decodeFile(localFile.absolutePath)
            binding.circleImageView1.setImageBitmap(bitmap)
        } .addOnFailureListener {
            Toast.makeText(this@GetUserInfo,"Failed to retrieve the image",Toast.LENGTH_LONG).show()
        }
    }
}