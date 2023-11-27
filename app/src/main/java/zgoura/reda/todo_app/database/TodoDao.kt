package zgoura.reda.todo_app.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Insert
    fun insertTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo_table ORDER BY timeStampTodo DESC")
    fun getAllTodos() : LiveData<List<Todo>>

    @Query("SELECT * FROM todo_table where idTodo= :key")
    fun getTodo(key : Long):LiveData<Todo>
}