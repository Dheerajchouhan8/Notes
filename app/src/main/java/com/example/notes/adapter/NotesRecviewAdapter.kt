package com.example.notes.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.notesModel
import kotlin.random.Random

class NotesRecviewAdapter(val ctx:Context,val arrnotes:ArrayList<notesModel>):
    RecyclerView.Adapter<NotesRecviewAdapter.viewHolder>(){
    class viewHolder(view:View):RecyclerView.ViewHolder(view){

        val title = view.findViewById<TextView>(R.id.title)
        val mesg = view.findViewById<TextView>(R.id.msg)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recview)
        val recviewItem = view.findViewById<ConstraintLayout>(R.id.notes)
        val imgDelet = view.findViewById<ImageView>(R.id.imgDelet)
        val notes = view.findViewById<ConstraintLayout>(R.id.Notes)
        val renameNote = view.findViewById<TextView>(R.id.txtNote)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(ctx).inflate(R.layout.notes_row,parent,false))
    }

    override fun getItemCount(): Int {
        return arrnotes.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.title.text = arrnotes[position].title
        holder.mesg.text = arrnotes[position].mesg
        holder.renameNote.text = arrnotes[position].title
       /* val model = IndexModel(position)*/
        val color = Color.argb(200, Random.nextInt(256), Random.nextInt(280), Random.nextInt(300))
        holder.notes.setBackgroundColor(color)
      /*  holder.imgDelet.setOnClickListener {
            arrnotes.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(0, arrnotes.size - 1)
        }
        holder.notes.setOnClickListener {
            var dialog = Dialog(ctx).apply {
                setContentView(R.layout.add_note_dilog)
                val txtNote = this.findViewById<TextView>(R.id.txtNote)
                val edtTitle = this.findViewById<EditText>(R.id.edtTitle)
                val edtMsg = this.findViewById<EditText>(R.id.edtMsg)
                val btnAdd = this.findViewById<Button>(R.id.btnAdd)
                val btnCancel = this.findViewById<Button>(R.id.btnCancel)
                btnAdd.setText("Update")
                txtNote.text = "Update Note"

                edtTitle.setText(arrnotes[position].title)
                edtMsg.setText(arrnotes[position].mesg)

                btnAdd.setOnClickListener {
                    arrnotes[position] = notesModel(edtTitle.text.toString(),edtMsg.text.toString())
                    notifyItemChanged(position)

                    dismiss()
                }
                show()
            }

            dialog.dismiss()

        }*/
    }
    }
