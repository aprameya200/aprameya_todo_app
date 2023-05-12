package com.example.aprameya_todo_app.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todos_table")
data class Todo(

    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "title") val title : String?,
    @ColumnInfo(name = "todo") val todo : String?,
    @ColumnInfo(name = "date") val date : String?,




    ) : java.io.Serializable
