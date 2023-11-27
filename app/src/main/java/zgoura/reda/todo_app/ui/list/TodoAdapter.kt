package zgoura.reda.todo_app.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import convertLongToDateString
import zgoura.reda.todo_app.database.Todo
import zgoura.reda.todo_app.databinding.RecyclerViewListItemBinding

class TodoAdapter :ListAdapter<Todo, TodoAdapter.TodoItemViewHolder>(DiffCallBack){

    class TodoItemViewHolder (private var binding: RecyclerViewListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(todo: Todo){
            binding.titleTxt.text = todo.titleTodo
            binding.timestampTxt.text = convertLongToDateString(todo.timeStampTodo)
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Todo>() {

        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.idTodo == newItem.idTodo
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoAdapter.TodoItemViewHolder {
       return TodoItemViewHolder(RecyclerViewListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TodoAdapter.TodoItemViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
    }
}