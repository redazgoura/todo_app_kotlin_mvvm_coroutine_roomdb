package zgoura.reda.todo_app.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import zgoura.reda.todo_app.database.TodoDao

class AddTodoViewModelFactory(
    private val datasource : TodoDao
    ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // we check if the view model is available
        if (modelClass.isAssignableFrom(AddTodoViewModel::class.java)){

            // if there is we return an instance of it
            return AddTodoViewModel(datasource) as T
        }
        // if not we throw an exception
        throw IllegalArgumentException("UNKnOWN viewModel Class")
    }
}