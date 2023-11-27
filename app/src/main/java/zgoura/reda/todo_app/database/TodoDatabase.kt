package zgoura.reda.todo_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract val todoDao : TodoDao
    companion object {

        @Volatile
        private var INSTANCE : TodoDatabase? = null

        fun getDatabase(context: Context) : TodoDatabase{

            var instance  = INSTANCE
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todos_db")
                    .fallbackToDestructiveMigration()
                    .build()

                // Assign INSTANCE to the newly created database.
                INSTANCE = instance
            }

            // Return instance; smart cast to be non-null.
            return instance
        }
    }
}