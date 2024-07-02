package com.example.crazyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.crazyapp.database.NoteDatabase
import com.example.crazyapp.repository.NoteRepository
import com.example.crazyapp.viewmodel.NoteViewModel
import com.example.crazyapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViwModel()
    }

    private fun setUpViwModel(){
        val noteRepository=NoteRepository(NoteDatabase(this))
        val viewModelProvideFactory=NoteViewModelFactory(application,noteRepository)
        noteViewModel= ViewModelProvider(this,viewModelProvideFactory)[NoteViewModel::class.java]
    }


}

