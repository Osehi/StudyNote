package com.polish.studynote.collection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.polish.studynote.database.TaskDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CollectionViewModel(
    val database:TaskDatabaseDao,
    application: Application): AndroidViewModel(application) {

    // create a job for the coroutine
    private var viewModelJob = Job()
    // get a scope for the coroutine
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    // get all tasks from database
     val tasks = database.getAllTasks()


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}