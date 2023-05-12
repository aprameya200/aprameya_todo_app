package com.example.aprameya_todo_app.Database

import androidx.lifecycle.LiveData
import com.example.aprameya_todo_app.Models.Todo

class TodosRepository(private val todoDao: TodoDB) {

    val allTodos : LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(todo : Todo){
        todoDao.insert(todo)
    }

    suspend fun delete(todo : Todo){
        todoDao.delete(todo)
    }

    suspend fun update(todo : Todo){
        todoDao.update(todo.id,todo.title,todo.todo)
    }
}