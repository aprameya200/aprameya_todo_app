package com.example.aprameya_todo_app.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * data class to create entity for todo
 *
 * @property id primary key id
 * @property title title of todo
 * @property todo body part of todo
 * @property date date of todo added
 */
@Entity(tableName = "todos_table")
data class Todo(

    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "title") val title : String?,
    @ColumnInfo(name = "todo") val todo : String?,
    @ColumnInfo(name = "date") val date : String?,




    ) : java.io.Serializable
