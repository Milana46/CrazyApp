package com.example.crazyapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Entity(tableName="notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,

    val noteTitle:String,
    val noteDescription:String

):Parcelable
