package com.polish.studynote.collection

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polish.studynote.database.TaskDatabaseDao
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class CollectionViewModelFactory(
    private val dataSource:TaskDatabaseDao,
    private val application:Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CollectionViewModel::class.java)){
            return CollectionViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}