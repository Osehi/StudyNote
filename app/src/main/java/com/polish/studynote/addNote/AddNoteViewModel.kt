package com.polish.studynote.addNote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polish.studynote.database.Task
import com.polish.studynote.database.TaskDatabaseDao
import kotlinx.coroutines.*

class AddNoteViewModel(
     val inputTask: Task,
    val database: TaskDatabaseDao): ViewModel() {



    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // define a variable "theTask" to hold current task // MutableLiveData<Task?>()
    private var theTask = MutableLiveData<Task>()

    // initialize "theTask"
    init {
        initializeTheTask()
    }

    private fun initializeTheTask(){
        uiScope.launch {
            theTask.value = getTheTaskFromDatabase()
        }
    }

    private suspend fun getTheTaskFromDatabase():Task?{
        return withContext(Dispatchers.IO){
            var task1 = database.getTask()
            task1
        }
    }


    // add a click listener for the start button
    fun onSubmitTask(){
        uiScope.launch {
            val newTask = Task(0, "topic", "date", "lagos")
            insert(newTask)
        }
    }

   suspend fun insert(task:Task){
        withContext(Dispatchers.IO){
            database.insert(task)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}