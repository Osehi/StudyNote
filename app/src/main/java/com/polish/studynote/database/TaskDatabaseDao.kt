package com.polish.studynote.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDatabaseDao {
    @Insert
    fun insert(task:Task)

    @Update
    fun update(task:Task)

    @Query("SELECT * FROM task_table WHERE taskId = :key")
    fun get(key:Int):Task

    @Query("DELETE FROM task_table")
    fun clear()

    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table ORDER BY taskId DESC LIMIT 1")
    fun getTask(): Task
}