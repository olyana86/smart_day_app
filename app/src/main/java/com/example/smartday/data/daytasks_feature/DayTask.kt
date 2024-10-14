package com.example.smartday.data.daytasks_feature

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day_tasks")
data class DayTask(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "day_task_id")
    val dayTaskId: Long = 0,

    @ColumnInfo(name = "day_task_name")
    val dayTaskName: String,

    @ColumnInfo(name = "day_task_details")
    val dayTaskDetails: String,

    @ColumnInfo(name = "day_task_date")
    val dayTaskDate: String,

    @ColumnInfo(name = "day_task_start_time")
    val dayTaskStartTime: String,

    @ColumnInfo(name = "day_task_end_time")
    val dayTaskEndTime: String,

    @ColumnInfo(name = "day_task_is_done")
    var dayTaskIsDone: Int = 0,

    @ColumnInfo(name = "day_task_is_a_frog")
    var dayTaskIsAFrog: Int = 0,

    @ColumnInfo(name = "day_task_is_a_priority")
    var dayTaskIsAPriority: Int = 0,

    @ColumnInfo(name = "day_task_eisenhower_tag_id")
    val dayTaskEisenhowerTagId: Int = 0,

    @ColumnInfo(name = "day_task_time_block_color")
    val dayTaskTimeBlockColor: String
)