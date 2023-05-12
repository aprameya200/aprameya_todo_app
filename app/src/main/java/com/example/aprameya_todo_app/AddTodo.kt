package com.example.aprameya_todo_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.aprameya_todo_app.Models.Todo
import com.example.aprameya_todo_app.databinding.ActivityAddTodoBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * AddTodo activity page for when add todo button is pressed
 *
 */
class AddTodo : AppCompatActivity() {


    private lateinit var binding : ActivityAddTodoBinding

    private lateinit var todo : Todo
    private lateinit var old_todo: Todo
    var isUpdate = false

    /**
     * onCreate method is called when activity loads
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            old_todo = intent.getSerializableExtra("current_todo") as Todo
            binding.etTitle.setText(old_todo.title)
            binding.etTodo.setText(old_todo.todo)
            isUpdate = true
        }catch (e: Exception){
            e.printStackTrace()
        }

        /**
         * click listener for update icon to update or add todo
         */
        binding.imgCheck.setOnClickListener{
            val title = binding.etTitle.text.toString()
            val todo_desc = binding.etTodo.text.toString()

            if (title.isNotEmpty() || todo_desc.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")

                if (isUpdate){
                    todo = Todo(
                        old_todo.id,title,todo_desc,formatter.format(Date())
                    )
                }else{
                    todo = Todo(
                        null,title,todo_desc,formatter.format(Date())

                    )
                }


                val intent = Intent()
                intent.putExtra("todo",todo)
                Toast.makeText(this@AddTodo, "Todo List Updated",Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK,intent)
                finish()

            }else{
                Toast.makeText(this@AddTodo, "Please enter some data",Toast.LENGTH_SHORT).show()
            }
        }

        /**
         * back button
         */
        binding.imgBackArrow.setOnClickListener{
            onBackPressed()

        }
    }
}