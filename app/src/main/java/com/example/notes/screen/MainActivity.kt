package com.example.notes.screen

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.adapter.NotesRecviewAdapter
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.databinding.AddNoteDilogBinding
import com.example.notes.model.notesModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val arrNotes = ArrayList<notesModel>()
    val db = Firebase.firestore

    /*lateinit var Nadapter: NotesRecviewAdapter*/
    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val auth = Firebase.auth
        val sp = getSharedPreferences(LoginActivity.SP_NAME, MODE_PRIVATE)
        val uid = sp.getString("uid", "")

        binding.btnExit.setOnClickListener {
            finish()
        }

        binding.fabButton.setOnClickListener {

            val dialog = Dialog(this@MainActivity)
            val bindingDilog = AddNoteDilogBinding.inflate(layoutInflater)
            setContentView(bindingDilog.root)

            bindingDilog.btnAdd.setOnClickListener {
                val title = bindingDilog.edtTitle.text.toString()
                val message = bindingDilog.edtMsg.text.toString()
                val noteModel = notesModel(title, message)
                db.collection("user").document("$uid").collection("notes").add(noteModel)
                    .addOnSuccessListener {
                    Toast.makeText(this@MainActivity, "Note Added", Toast.LENGTH_LONG).show()
                    /*db.collection("user").document("notes").get().addOnSuccessListener {}*/

                        /*for (document in it.collection() ){
                            val notes = document(notesModel::class.java)
                            arrNotes.add(notes!!)
                        }*/
                        dialog.dismiss()
                }
                    .addOnFailureListener {
                        Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                    }
            }
            bindingDilog.btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
        /*
                getData(uid.toString())
                Toast.makeText(this@MainActivity, "$uid", Toast.LENGTH_SHORT).show()*/

        binding.btnExit.setOnClickListener {
            Firebase.auth.signOut()
            finishActivity(100)
        }
        binding.recview.layoutManager = GridLayoutManager(this@MainActivity, 2)
        /*   binding.recview.adapter = NotesRecviewAdapter(this@MainActivity,arrNotes)*/

    }

    fun getData(uids: String) {
        db.collection("User").document("$uids").collection("Notes").get().addOnSuccessListener {
            for (document in it.documents) {
                val notes = document.toObject(notesModel::class.java)
                arrNotes.add(notes!!)
            }
            binding.recview.adapter = NotesRecviewAdapter(this@MainActivity, arrNotes)
        }
    }

}
