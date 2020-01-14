package com.polish.studynote.collection


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment

import com.polish.studynote.R
import com.polish.studynote.database.TaskDatabase
import com.polish.studynote.database.TaskDatabaseDao
import com.polish.studynote.databinding.FragmentCollectionBinding

/**
 * A simple [Fragment] subclass.
 */
class Collection : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // get a reference to the binding object and inflate the fragment views
        val binding:FragmentCollectionBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_collection, container, false)

        // a reference to the application context
        val application = requireNotNull(this.activity).application

        // a reference to the database source
        val dataSource = TaskDatabase.getInstance(application).TaskDatabaseDao

        // here is an instance of the factory model where I will pass the datasource and the application
        val viewModelFactory = CollectionViewModelFactory(dataSource, application)

        // get a reference to the CollectionViewModel
        val collectionViewModel = ViewModelProviders.of(this, viewModelFactory).get(CollectionViewModel::class.java)

        // add lifecycleOwner to the binding
        binding.setLifecycleOwner(this)


        // add binding to the collectionViewModel
        binding.collectionViewModel = collectionViewModel

        // create an adapter to tell the recycler view about the adapter
        val adapter = CollectionAdapter()
        // connect the adapter to the recyclerview
        binding.collectionRecyclerViewId.adapter = adapter
        // set an observer on the "tasks" data and set viewLifecycleOwner
        collectionViewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
                Toast.makeText(context, "$it", Toast.LENGTH_LONG).show()
            }
        })

        // I need to navigate from the floating-action-button to the addNote fragment
        binding.collectionFabId.setOnClickListener {
            val action = CollectionDirections.actionCollectionToAddNote()
            NavHostFragment.findNavController(this).navigate(action)
        }


        return binding.root
    }


}
