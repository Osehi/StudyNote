package com.polish.studynote.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "task_table")
@Parcelize
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId:Int = 0,

    @ColumnInfo(name = "topic")
    var topic:String ,

    @ColumnInfo(name = "date")
    var date:String,

    @ColumnInfo(name = "location")
    var location:String
):Parcelable
