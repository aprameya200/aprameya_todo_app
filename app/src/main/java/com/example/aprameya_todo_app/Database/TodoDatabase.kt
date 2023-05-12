package com.example.aprameya_todo_app.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aprameya_todo_app.Models.Todo
import com.example.aprameya_todo_app.utilities.DATABASE_NAME

@Database(entities = arrayOf(Todo::class), version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() { //implements class here

    //Singleton pattern used here

    abstract fun getTodoDB() : TodoDB

    companion object{

        @Volatile
        private var INSTANCE : TodoDatabase? = null

        fun getDatabase(context : Context) : TodoDatabase{

            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance

                instance
            }


        }
    }
}