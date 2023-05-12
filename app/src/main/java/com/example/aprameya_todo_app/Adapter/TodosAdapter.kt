package com.example.aprameya_todo_app.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.aprameya_todo_app.AboutFragment
import com.example.aprameya_todo_app.Models.Todo
import com.example.aprameya_todo_app.R
import kotlin.random.Random

/**
 * Class that works as intermediate class to get all todo items
 * from database
 *
 * @property context of application
 * @property listener instance of class implementing interface
 */
class TodosAdapter(private val context: Context, val listener: TodosClickListener) :
    RecyclerView.Adapter<TodosAdapter.TodoViewHolder>() {

    private val TodosList = ArrayList<Todo>()
    private val fulList = ArrayList<Todo>()

    /**
     * onCreate method to create view holder
     *
     * @param parent
     * @param viewType
     * @return the size of todo list
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {


        return TodoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }

    /**
     * Method to get the item count
     *
     * @return size of todo list
     */
    override fun getItemCount(): Int {
        return TodosList.size
    }

    /**
     * Method to update list
     *
     * @param newList updated list of todo
     */
    fun updateList(newList: List<Todo>){
        fulList.clear()
        fulList.addAll(newList)

        TodosList.clear()
        TodosList.addAll(fulList)
        notifyDataSetChanged()
    }

    /**
     * filter the search items
     *
     * @param search string input in search bar
     */
    fun filterList(search: String){
        TodosList.clear()

        for (item in fulList){

            if (item.title?.lowercase()?.contains(search.lowercase()) == true || item.todo?.lowercase()?.contains(search.lowercase()) == true ){
                TodosList.add(item)
            }
        }

        notifyDataSetChanged()
    }

    /**
     *
     *  Method having onClick and onLongClick listeners for todo card items
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        val currentTodo = TodosList[position]
        holder.title.text = currentTodo.title
        holder.title.isSelected = true

        holder.Todo_tv.text = currentTodo.todo
        holder.date.text = currentTodo.date
        holder.date.isSelected = true

        holder.todos_layout.setOnClickListener{
            listener.onItemClicked(TodosList[holder.adapterPosition])
        }

        holder.todos_layout.setOnLongClickListener{
            listener.onLongItemClicked(TodosList[holder.adapterPosition],holder.todos_layout)
            true
        }

        holder.done.setOnClickListener{
            listener.onLongItemClicked(TodosList[holder.adapterPosition],holder.todos_layout)
            true
        }


    }


    /**
     * inner class inside TodoAdapter
     * to get id of all components inside one todo card
     *
     * @constructor
     * View instnace
     *
     * @param itemView instance of item view
     */
    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val todos_layout = itemView.findViewById<CardView>(R.id.card_layout) //card layout instance
        val title = itemView.findViewById<TextView>(R.id.tv_title) //todo title
        val Todo_tv = itemView.findViewById<TextView>(R.id.tv_todo) //todo bodu
        val date = itemView.findViewById<TextView>(R.id.tv_date) //todo date

        val done = itemView.findViewById<ImageButton>(R.id.done) //done button

    }

    /**
     * interface that defines two methods
     * for click and long click functionality
     */
    interface TodosClickListener{
        fun onItemClicked(todo: Todo)

        fun onLongItemClicked(todo: Todo, cardView: CardView)
    }

}