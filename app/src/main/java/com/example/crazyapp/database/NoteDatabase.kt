package com.example.crazyapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crazyapp.model.Note


@Database(version=1,
    entities = [Note::class])

abstract class NoteDatabase:RoomDatabase() {

    abstract fun getNotDao():NoteDao


    companion object{
        @Volatile
        private var instance:NoteDatabase?=null
        private val LOCK=Any()

        operator fun invoke(context:Context)= instance?:
        synchronized(LOCK){
            instance ?:
            createDatabase(context).also{
                instance=it
            }
        }

        private fun createDatabase(context:Context)=
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "notes_db"
            ).build()

    }


}
