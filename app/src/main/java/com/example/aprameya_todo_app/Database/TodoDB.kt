package com.example.aprameya_todo_app.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aprameya_todo_app.Models.Todo

@Dao
interface TodoDB {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo : Todo)

    @Delete
    suspend fun delete(todo : Todo)

    @Query("SELECT * from todos_table order by id ASC")
    fun getAllTodos() : LiveData<List<Todo>>

    @Query("UPDATE todos_table Set title = :title, todo = :todo WHERE id = :id")
    suspend fun update(id : Int?, title: String?,todo: String?)


}