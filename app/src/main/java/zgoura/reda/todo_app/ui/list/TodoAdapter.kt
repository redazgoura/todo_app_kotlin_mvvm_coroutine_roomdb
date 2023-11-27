package zgoura.reda.todo_app.ui.list

import ColorProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import convertLongToDateString
import zgoura.reda.todo_app.R
import zgoura.reda.todo_app.database.Todo
import zgoura.reda.todo_app.databinding.RecyclerViewListItemBinding
import zgoura.reda.todo_app.generated.callback.OnClickListener

class TodoAdapter(private val clickListener: (idTodo : Long) -> Unit) : ListAdapter<Todo, TodoAdapter.TodoItemViewHolder>(DiffCallback){
    class TodoItemViewHolder(private var binding: RecyclerViewListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Todo,
                    clickListener: (idTodo: Long) -> Unit)
            {
                binding.titleTxt.text = item.titleTodo
                binding.timestampTxt.text = convertLongToDateString(item.timeStampTodo)

                binding.root.setOnClickListener {
                    clickListener(item.idTodo)
                }

                val cardItems = binding.itemsCard
                cardItems.setCardBackgroundColor(ColorProvider.getColorRessourceId(item.idTodo.toInt()))

                binding.executePendingBindings()
            }
    }

    /** companion obj = we want it in the name space of our class */
    companion object DiffCallback: DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
           return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
           return oldItem.idTodo == newItem.idTodo
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoAdapter.TodoItemViewHolder {

        return TodoItemViewHolder(RecyclerViewListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {

        val todo = getItem(position)
        holder.bind(todo, clickListener)

    }
}

