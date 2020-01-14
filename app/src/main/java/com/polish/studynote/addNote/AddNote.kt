package com.polish.studynote.addNote


import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.polish.studynote.R
import com.polish.studynote.database.Task
import com.polish.studynote.database.TaskDatabase
import com.polish.studynote.database.TaskDatabaseDao
import com.polish.studynote.databinding.FragmentAddNoteBinding
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class AddNote : Fragment() {

    private lateinit var viewModel: AddNoteViewModel
     lateinit var inputTask: Task

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding:FragmentAddNoteBinding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_add_note, container, false)







//        // add databinding to the viewModel
//        binding.setLifecycleOwner(this)
//        // assign the AddViewModel binding variable to the AddviewModel
//        binding.addNoteViewModel = addNoteViewModel

        binding.enterTaskButtonId.setOnClickListener {
            onSubmitInfo()

        }







        return binding.root
    }


    fun onSubmitInfo(){
        var myTopic = enterTaskTopicId.text.toString()
        var myDate = enterTaskDateId.text.toString()
        var myLocation = enterTaskLocatId.text.toString()

       var inputTask = Task(0, myTopic,myDate,myLocation)

        val dataSource = TaskDatabase.getInstance(Application()).TaskDatabaseDao

        val instanceOfAdd = AddNoteViewModel(inputTask, dataSource)
        CoroutineScope(IO).launch {
            instanceOfAdd.insert(inputTask)
        }


    }


}
