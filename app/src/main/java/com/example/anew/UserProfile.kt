package com.example.anew

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.anew.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import de.hdodenhof.circleimageview.CircleImageView

class UserProfile : AppCompatActivity() {
    private lateinit var firstName:TextView
    private lateinit var lastName:TextView
    private lateinit var userImg:CircleImageView
    private lateinit var bio:TextView
    private lateinit var saveInfo:Button
    private lateinit var UserInfoBtn:Button
    private lateinit var binding:ActivityUserProfileBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set an onClickListener for the circleImageView
        binding.circleImageView.setOnClickListener {
            // Open the gallery to select an image
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }


        auth= FirebaseAuth.getInstance()
        val uid=auth.currentUser?.uid
        databaseReference=FirebaseDatabase.getInstance().getReference("Users")

        binding.saveInfoButton.setOnClickListener {
            val firstName=binding.FN.text.toString()
            val lastName=binding.LN.text.toString()
            val age=binding.Age.text.toString()
//            val bio=binding.Bio.toString()
            val user=Users(firstName, lastName, age)
            if (uid != null) {
                databaseReference.child(uid).setValue(user).addOnCompleteListener {
                    if (it.isSuccessful){
//                        uploadProfilePic()
                        Toast.makeText(this,"Profile Updated Successfully",Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this,"Failed to update profile",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        initialization()

        binding.fabProf.setOnClickListener {
            val intent= Intent(this,FirstScreen::class.java)
            startActivity(intent)
        }

        UserInfoBtn.setOnClickListener {
            val intent= Intent(this,GetUserInfo::class.java)
            startActivity(intent)
        }

    }

    private fun uploadProfilePic() {
        // Open the gallery to select an image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }


    // Override onActivityResult to handle the selected image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            val imageUri = data.data
            if (imageUri != null) {
                // Upload the selected image to Firebase Storage
                storageReference = FirebaseStorage.getInstance().getReference("Users/${auth.currentUser?.uid}.jpeg")
                storageReference.putFile(imageUri)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Pic Updated Successfully", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to Upload the image", Toast.LENGTH_LONG).show()
                    }
            }
        }
    }




//    private fun uploadProfilePic() {
//        imageUri=Uri.parse("android.resource://$packageName/${R.drawable.avatar}")
//        storageReference=FirebaseStorage.getInstance().getReference("Users/"+auth.currentUser?.uid+".jpeg")
//        storageReference.putFile(imageUri).addOnSuccessListener {
//            Toast.makeText(this,"Profile Updated Successfully",Toast.LENGTH_LONG).show()
//        } .addOnFailureListener {
//            Toast.makeText(this,"Failed to Upload the image",Toast.LENGTH_LONG).show()
//        }
//    }



    private fun initialization(){
        firstName=findViewById(R.id.FN)
        lastName=findViewById(R.id.LN)
        bio=findViewById(R.id.Age)
        saveInfo=findViewById(R.id.saveInfoButton)
        UserInfoBtn=findViewById(R.id.BtnGetUserInfo)
        userImg=findViewById(R.id.circleImageView)
    }


}
