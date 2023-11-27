package zgoura.reda.todo_app.ui.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import zgoura.reda.todo_app.database.TodoDao
import zgoura.reda.todo_app.database.TodoDatabase
import zgoura.reda.todo_app.ui.add.AddTodoViewModel

class UpdateTodoViewModelFactory(private val todoId : Long, private val datasource : TodoDao)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // we check if the view model is available
        if (modelClass.isAssignableFrom(UpdateTodoViewModel::class.java)){

            // if there is we return an instance of it
            return UpdateTodoViewModel(todoId, datasource) as T
        }
        // if not we throw an exception
        throw IllegalArgumentException("UNKnOWN viewModel Class")
    }
}