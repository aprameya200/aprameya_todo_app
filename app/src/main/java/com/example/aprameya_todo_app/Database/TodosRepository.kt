package com.example.aprameya_todo_app.Database

import androidx.lifecycle.LiveData
import com.example.aprameya_todo_app.Models.Todo

/**
 * Repository class to implement methods in databse class
 *
 * @property todoDao
 */
class TodosRepository(private val todoDao: TodoDB) {

    //get all todos from databse
    val allTodos : LiveData<List<Todo>> = todoDao.getAllTodos()

    //insert todo
    suspend fun insert(todo : Todo){
        todoDao.insert(todo)
    }

    //delete todo
    suspend fun delete(todo : Todo){
        todoDao.delete(todo)
    }

    //update todo
    suspend fun update(todo : Todo){
        todoDao.update(todo.id,todo.title,todo.todo)
    }
}