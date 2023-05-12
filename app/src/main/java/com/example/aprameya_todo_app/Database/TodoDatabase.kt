package com.example.aprameya_todo_app.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aprameya_todo_app.Models.Todo
import com.example.aprameya_todo_app.utilities.DATABASE_NAME

/**
 * Database class to get database
 *
 * ROOM database is used
 *
 */
@Database(entities = arrayOf(Todo::class), version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() { //implements class here

    //Singleton pattern used here

    /**
     * abstract class with no body
     * but will be used to get database
     *
     * @return
     */
    abstract fun getTodoDB() : TodoDB

    companion object{

        @Volatile
        private var INSTANCE : TodoDatabase? = null

        /**
         * builds the database and returns an instance
         *
         * @param context
         * @return
         */
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