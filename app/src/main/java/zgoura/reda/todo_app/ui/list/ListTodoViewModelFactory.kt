package zgoura.reda.todo_app.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import zgoura.reda.todo_app.database.TodoDao
import zgoura.reda.todo_app.ui.add.AddTodoViewModel

class ListTodoViewModelFactory(
    private val datasource : TodoDao
                    ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // we check if the view model is available
        if (modelClass.isAssignableFrom(ListTodoViewModel::class.java)){

            // if there is we return an instance of it
            return ListTodoViewModel(datasource) as T
        }
        // if not we throw an exception
        throw IllegalArgumentException("UNKnOWN viewModel Class")
    }
}