package zgoura.reda.todo_app.ui.list

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
import zgoura.reda.todo_app.database.TodoDatabase
import zgoura.reda.todo_app.databinding.FragmentAddTodoBinding
import zgoura.reda.todo_app.databinding.FragmentListTodoBinding


class ListTodoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding : FragmentListTodoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_todo, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = TodoDatabase.getDatabase(application).todoDao

        val viewModelFactory = ListTodoViewModelFactory(dataSource)

        val listTodoViewModel = ViewModelProvider(this, viewModelFactory)[ListTodoViewModel::class.java]

        binding.listTodoViewModel = listTodoViewModel

        val adapter = TodoAdapter()

        binding.listTodosRv.adapter = adapter

        listTodoViewModel.todos.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.floatingActionButton.setOnClickListener {
            this.findNavController().navigate(ListTodoFragmentDirections.actionListTodoFragment2ToAddTodoFragment2())
        }

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}