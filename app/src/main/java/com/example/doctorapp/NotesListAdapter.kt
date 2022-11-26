package com.example.doctorapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorapp.data.NoteEntity
import com.example.doctorapp.databinding.ListItemBinding

class NotesListAdapter (private val notesList: List<NoteEntity>,
    private val listener: ListItemListener):
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>(){

    val selectedNotes = arrayListOf<NoteEntity>()

    inner class ViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
            val binding = ListItemBinding.bind(itemView)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notesList[position]
        with(holder.binding){
            noteText.text = note.text
            root.setOnClickListener{
                listener.onItemClick(note.id)
            }
            fab.setOnClickListener{
                if(selectedNotes.contains(note)){
                    selectedNotes.remove(note)
                    fab.setImageResource(R.drawable.ic_notes)
                }else{
                    selectedNotes.add(note)
                    fab.setImageResource(R.drawable.ic_check_circle)
                }
                listener.onItemSelectionChanged()
            }
            //to maintain the view state of icons when the recyclerview is scrolled up and down
            fab.setImageResource(
                if(selectedNotes.contains(note)){
                    R.drawable.ic_check_circle
                }else{
                    R.drawable.ic_notes
                }
            )
        }
    }

    interface ListItemListener{
        fun onItemClick(noteId: Int)
        fun onItemSelectionChanged()
    }
}