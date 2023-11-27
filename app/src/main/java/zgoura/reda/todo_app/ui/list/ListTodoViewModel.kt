package zgoura.reda.todo_app.ui.list

import androidx.lifecycle.ViewModel
import zgoura.reda.todo_app.database.TodoDao

class ListTodoViewModel (val database : TodoDao) : ViewModel() {

    val todos = database.getAllTodos()

}