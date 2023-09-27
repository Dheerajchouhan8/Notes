package com.example.notes.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.R
import com.example.notes.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    val db = Firebase.firestore
    val auth = Firebase.auth
    lateinit var binding: ActivityLoginBinding
    companion object{
        val SP_NAME = "sp"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}