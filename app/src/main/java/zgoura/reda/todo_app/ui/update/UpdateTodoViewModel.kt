package zgoura.reda.todo_app.ui.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import zgoura.reda.todo_app.database.Todo
import zgoura.reda.todo_app.database.TodoDao

class UpdateTodoViewModel(todoId : Long , private val database: TodoDao): ViewModel() {

    val todoById = database.getTodo(todoId)

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _navigateToListTodo = MutableLiveData<Boolean>()
    val navigateToListTodo : LiveData<Boolean> get() = _navigateToListTodo


    fun onNavigateToListTodo(){
        _navigateToListTodo.value = false
    }
    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackBar(){
        _showSnackbarEvent.value = false
    }



    private suspend fun updateTodo(todo: Todo){
       withContext(Dispatchers.IO){
           database.updateTodo(todo)
       }

    }

    fun onUpdateTodo(){
        uiScope.launch {
            todoById.value?.let {

                if(it.titleTodo.isEmpty()){

                    _showSnackbarEvent.value = false
                    _navigateToListTodo.value = false

                } else{

                    updateTodo(it)
                    _showSnackbarEvent.value = true
                    _navigateToListTodo.value = true
                }


            }
        }
    }

    private suspend fun deleteTodo(todo: Todo){
           withContext(Dispatchers.IO){
               database.deleteTodo(todo)
           }

        }

    fun onDeleteTodo(){
        uiScope.launch {
            todoById.value?.let {
                deleteTodo(it)
                _navigateToListTodo.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}