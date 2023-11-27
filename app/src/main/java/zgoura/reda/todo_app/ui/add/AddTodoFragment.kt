package zgoura.reda.todo_app.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import zgoura.reda.todo_app.R
import zgoura.reda.todo_app.database.TodoDao
import zgoura.reda.todo_app.database.TodoDatabase
import zgoura.reda.todo_app.databinding.FragmentAddTodoBinding

class AddTodoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding : FragmentAddTodoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_todo, container, false)

        val application = requireNotNull(this.activity).application

        val datasource = TodoDatabase.getDatabase(application).todoDao

        val viewModelFactory = AddTodoViewModelFactory(datasource)

        val addTodoViewModel = ViewModelProvider(this, viewModelFactory)[AddTodoViewModel::class.java]

        binding.addTodoViewModel = addTodoViewModel


        /** nav back to todo list */
        addTodoViewModel.navigateToTodoList.observe(viewLifecycleOwner, Observer {
            if (it==true){
                this.findNavController().navigate(AddTodoFragmentDirections.actionAddTodoFragment2ToListTodoFragment2())
            }
        })


        return binding.root
    }

}