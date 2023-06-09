package com.example.aprameya_todo_app.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.aprameya_todo_app.Database.TodoDatabase
import com.example.aprameya_todo_app.Database.TodosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View model for todo db
 *
 * @constructor
 * TODO
 *
 * @param application
 */
class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : TodosRepository

    val alltodos : LiveData<List<Todo>>

    init {
        val dao = TodoDatabase.getDatabase(application).getTodoDB()
        repository = TodosRepository(dao)
        alltodos = repository.allTodos
    }

    //delete todo
    fun deleteTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(todo)
    }

    //insert todo
    fun insertTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(todo)
    }

    //update todo
    fun updateTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(todo)
    }
}