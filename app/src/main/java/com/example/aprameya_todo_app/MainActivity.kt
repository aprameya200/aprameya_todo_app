package com.example.aprameya_todo_app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.aprameya_todo_app.Adapter.TodosAdapter
import com.example.aprameya_todo_app.Database.TodoDatabase
import com.example.aprameya_todo_app.Models.Todo
import com.example.aprameya_todo_app.Models.TodoViewModel
import com.example.aprameya_todo_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),TodosAdapter.TodosClickListener, PopupMenu.OnMenuItemClickListener  {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: TodoDatabase
    lateinit var viewModel: TodoViewModel
    lateinit var adapter: TodosAdapter
    lateinit var selectedTodo : Todo


    private val updateTodo = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->

        if (result.resultCode == RESULT_OK){

            val todo = result.data?.getSerializableExtra("todo") as? Todo
            if (todo != null) {
                viewModel.updateTodo(todo)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gotoBtn = findViewById<Button>(R.id.goto_about)


        gotoBtn.setOnClickListener{

            val fragment = AboutFragment()

            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.card_layout,fragment).commit()
        }


        initUI()

        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TodoViewModel::class.java)


        viewModel.alltodos.observe(this){list ->
            list?.let{
                adapter.updateList(list)
            }

        }

        database = TodoDatabase.getDatabase(this)
    }

    private fun initUI(){
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(1, LinearLayout.VERTICAL)
        adapter = TodosAdapter(this,this)
        binding.recyclerView.adapter = adapter

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

            if (result.resultCode == RESULT_OK){
                val todo = result.data?.getSerializableExtra("todo") as? Todo

                if (todo != null){
                    viewModel.insertTodo(todo)
                }
            }


        }


        binding.fbAddTodo.setOnClickListener{

            val intent = Intent(this,AddTodo::class.java)
            getContent.launch(intent)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    adapter.filterList(newText)
                }

                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

        })

    }

    override fun onItemClicked(todo: Todo) {

        val intent = Intent(this@MainActivity,AddTodo::class.java)
        intent.putExtra("current_todo",todo)
        updateTodo.launch(intent)
    }

    override fun onLongItemClicked(todo: Todo, cardView: CardView) {
        selectedTodo = todo
        popUpDisplay(cardView)
    }

    private fun popUpDisplay(cardView: CardView){
        val popup = PopupMenu(this,cardView)
        popup.setOnMenuItemClickListener(this@MainActivity)
        popup.inflate(R.menu.pop_up_menu)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
       if (item?.itemId == R.id.delete_todo){
           viewModel.deleteTodo((selectedTodo))
           return true
       }
        return false
    }



}