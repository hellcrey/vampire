package com.example.vampire.room_database.ToDoRepository

import com.example.vampire.room_database.ToDo
import com.example.vampire.room_database.ToDoDAD

class ToDoRepository(private val toDoDAD: ToDoDAD) {
    suspend fun getAllTasks(): List<ToDo>
}