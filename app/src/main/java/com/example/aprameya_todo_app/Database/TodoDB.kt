package com.example.aprameya_todo_app.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aprameya_todo_app.Models.Todo

/**
 * Interface containg CRUD methods to be
 * used in database as well as queriers to run various CRUD
 * functionlaities
 *
 */
@Dao
interface TodoDB {

    /**
     * insert method to insert new todo in database
     *
     * @param todo
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo : Todo)

    /**
     * delete method to delete todo
     *
     * @param todo
     */
    @Delete
    suspend fun delete(todo : Todo)

    /**
     * get all todo  method to get all the todos present in database
     *
     * @return
     */
    @Query("SELECT * from todos_table order by id ASC")
    fun getAllTodos() : LiveData<List<Todo>>

    /**
     * update method to update todo
     *
     * @param id of updated todo
     * @param title of todo
     * @param todo main body of todo
     */
    @Query("UPDATE todos_table Set title = :title, todo = :todo WHERE id = :id")
    suspend fun update(id : Int?, title: String?,todo: String?)


}