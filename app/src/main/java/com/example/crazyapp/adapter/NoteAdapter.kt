package com.example.crazyapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.crazyapp.databinding.NoteLayoutItemBinding
import com.example.crazyapp.fragments.HomeFragmentDirections
import com.example.crazyapp.model.Note

class NoteAdapter(val context: Context)
    :RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    class NoteViewHolder(val Itembinding:NoteLayoutItemBinding):RecyclerView.ViewHolder(Itembinding.root)

    private val differCallBack=object:DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id==newItem.id &&
                    oldItem.noteDescription==newItem.noteDescription &&
                    oldItem.noteTitle==newItem.noteTitle

        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=differ.currentList[position]

        holder.Itembinding.noteTitle.text=currentNote.noteTitle
        holder.Itembinding.noteDesc.text=currentNote.noteTitle


        holder.itemView.setOnClickListener{
            val direction=HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }


}