package com.example.crazyapp.repository

import com.example.crazyapp.database.NoteDatabase
import com.example.crazyapp.model.Note

class NoteRepository(private val db:NoteDatabase) {


    suspend fun insertNote(note: Note)=db.getNotDao().insertNote(note)

    suspend fun updateNote(note:Note)=db.getNotDao().updateNote(note)

    suspend fun deleteNote(note:Note)=db.getNotDao().deleteNote(note)

    fun searchNote(query:String?)=db.getNotDao().searchNote(query)

    fun getAllNotes()=db.getNotDao().getAllNotes()
}