package zgoura.reda.todo_app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    var idTodo : Long = 0L,

    @ColumnInfo(name = "titleTodo")
    var titleTodo : String = "",

    @ColumnInfo(name = "completedTodo")
    var todoCompleted : Boolean = false,

    @ColumnInfo(name = "timeStampTodo")
    var timeStampTodo : Long = System.currentTimeMillis()
)