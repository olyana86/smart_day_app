package com.example.smartday.data.daytasks_feature

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eisenhower_tags")
data class EisenhowerTag(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "eisenhower_tag_id")
    val eisenhowerTagId: Int = 0,

    @ColumnInfo(name = "eisenhower_tag_name")
    val eisenhowerTagName: String
)