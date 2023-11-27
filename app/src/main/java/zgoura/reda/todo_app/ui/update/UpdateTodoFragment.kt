package zgoura.reda.todo_app.ui.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.snackbar.Snackbar
import zgoura.reda.todo_app.R
import zgoura.reda.todo_app.database.TodoDatabase
import zgoura.reda.todo_app.databinding.FragmentAddTodoBinding
import zgoura.reda.todo_app.databinding.FragmentUpdateTodoBinding


class UpdateTodoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding : FragmentUpdateTodoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_todo, container, false)

        // get todo ID using safeArgs
        val idTodo = UpdateTodoFragmentArgs.fromBundle(requireArguments()).idTodo

        val application = requireNotNull(this.activity).application

        val datasource = TodoDatabase.getDatabase(application).todoDao

        val viewModelFactory = UpdateTodoViewModelFactory(idTodo, datasource)

        val updateViewModel = ViewModelProvider(this, viewModelFactory)[UpdateTodoViewModel::class.java]

        binding.updateViewModel = updateViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        updateViewModel.navigateToListTodo.observe(viewLifecycleOwner, Observer {
            if(it == true){
                this.findNavController().navigate(UpdateTodoFragmentDirections.actionUpdateTodoFragmentToListTodoFragment2())
                updateViewModel.onNavigateToListTodo()
            }
        })

        updateViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {

            if (it == false){

                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "The Filed Is Empty ",
                    Snackbar.LENGTH_LONG
                ).show()

            }else{

                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "Todo Is Updated",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })

        return binding.root
    }

}