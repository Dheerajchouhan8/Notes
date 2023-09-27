package com.example.notes.screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.notes.R
import com.example.notes.databinding.ActivitySignUpBinding
import com.example.notes.model.userModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    val auth = Firebase.auth
    val db = Firebase.firestore

    lateinit var binding: ActivitySignUpBinding

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnSignup.setOnClickListener {
            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val pass = binding.edtPass.text.toString()
            val mobileNo = binding.edtMobileNo.text.toString()

            //create account
            auth.createUserWithEmailAndPassword(email, pass) .addOnFailureListener {
                Log.d("failed","${it.message}")
                it.printStackTrace()
            }.addOnSuccessListener {
                val usermodel = userModel(it.user!!.uid,name, email, pass,mobileNo)
                db.collection("user").document(it.user!!.uid).set(usermodel)
                    .addOnSuccessListener {
                        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignUpActivity,MainActivity::class.java))
                }.addOnFailureListener {
                        Toast.makeText(this, "Failed to Create User", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }
}