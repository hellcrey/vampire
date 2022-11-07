package com.example.vampire.room_database

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title: String,
    val time: String,
    val place: String
)
