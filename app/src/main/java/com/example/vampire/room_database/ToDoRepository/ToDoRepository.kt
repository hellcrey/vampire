package com.example.vampire.room_database.ToDoRepository

import com.example.vampire.room_database.ToDo
import com.example.vampire.room_database.ToDoDAD

class ToDoRepository(private val toDoDAo: ToDoDAD) {
    suspend fun getAllTasks(): List<ToDo>{
        return toDoDAo.getAllTasks()
    }
    suspend fun  insertTask(toDo : ToDo): Long{
        return toDoDAo.insertTask(toDo)
    }
    suspend fun insertTasks(toDo: List<ToDo>?): List<Long>{
        return toDoDAo.insertTasks(toDo)
    }
}