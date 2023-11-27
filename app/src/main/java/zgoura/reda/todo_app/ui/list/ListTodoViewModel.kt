package zgoura.reda.todo_app.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import zgoura.reda.todo_app.database.TodoDao

class ListTodoViewModel (val database : TodoDao) : ViewModel() {

    val todos = database.getAllTodos()

    private var _navigateToTodoItem = MutableLiveData<Long?>()
    val navigateToTodoItem : LiveData<Long?> get() = _navigateToTodoItem

    fun todoItemClicked(todoId : Long){
        _navigateToTodoItem.value = todoId
    }

    fun onTodoItemClicked(){
        _navigateToTodoItem.value = null
    }

}