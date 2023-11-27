package zgoura.reda.todo_app.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import zgoura.reda.todo_app.database.Todo
import zgoura.reda.todo_app.database.TodoDao

class AddTodoViewModel(val database : TodoDao) : ViewModel() {

    var title : String = ""

    val viewModelJob = Job()

    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /** navigate to */
    private var _navigateToTodoList = MutableLiveData<Boolean>()
    val navigateToTodoList : LiveData<Boolean> get() = _navigateToTodoList

    fun doneNavigatingToTodoList() = _navigateToTodoList.value == false

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackBar(){
        _showSnackbarEvent.value = false
    }

    private suspend fun addTodo(todo : Todo){
        withContext(Dispatchers.IO){
            database.insertTodo(todo)
        }
    }

    fun onAddTodo(){
        uiScope.launch {

            val newTodo = Todo()

            if (title.isEmpty()){

                _showSnackbarEvent.value = false
                _navigateToTodoList.value = false

            } else {

                newTodo.titleTodo = title

                addTodo(newTodo)

                _showSnackbarEvent.value = true
                _navigateToTodoList.value = true
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}