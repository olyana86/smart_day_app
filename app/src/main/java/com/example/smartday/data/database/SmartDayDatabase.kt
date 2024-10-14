package com.example.smartday.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.smartday.data.daytasks_feature.DayTask
import com.example.smartday.data.daytasks_feature.DayTasksDao
import com.example.smartday.data.daytasks_feature.EisenhowerTag
import com.example.smartday.data.pomodoro_feature.PomodoroDao
import com.example.smartday.data.pomodoro_feature.PomodoroResult
import com.example.smartday.data.habits_feature.Habit
import com.example.smartday.data.habits_feature.HabitLog
import com.example.smartday.data.habits_feature.HabitsDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(
    entities = [DayTask::class, EisenhowerTag::class,
        PomodoroResult::class, Habit::class, HabitLog::class],
    version = SmartDayDatabase.DB_VERSION, exportSchema = false
)
abstract class SmartDayDatabase : RoomDatabase() {
    abstract fun dayTasksDao(): DayTasksDao
    abstract fun pomodoroDao(): PomodoroDao
    abstract fun habitsDao(): HabitsDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app.db"

        @Volatile
        private var INSTANCE: SmartDayDatabase? = null

        fun getInstance(context: Context): SmartDayDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context)
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SmartDayDatabase::class.java, DB_NAME
            ).addCallback(dbCreateCallback(context)).build()

        fun dbCreateCallback(context: Context) = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                GlobalScope.launch {
                    getInstance(context).dayTasksDao()
                        .insertAllEisenhowerTags(PrepopulateData.eisenhowerTags)
                    getInstance(context).pomodoroDao()
                        .insertPomodoroResult(PrepopulateData.pomodoroResult)
                }
            }
        }

        object PrepopulateData {
            val eisenhowerTags = listOf(
                EisenhowerTag(
                    eisenhowerTagId = 1,
                    eisenhowerTagName = "Важное, срочное"
                ),
                EisenhowerTag(
                    eisenhowerTagId = 2,
                    eisenhowerTagName = "Важное, не срочное"
                ),
                EisenhowerTag(
                    eisenhowerTagId = 3,
                    eisenhowerTagName = "Срочное, не важное"
                ),
                EisenhowerTag(
                    eisenhowerTagId = 4,
                    eisenhowerTagName = "Не срочное, не важное"
                )
            )
            val pomodoroResult = PomodoroResult(
                pomodoroResultId = 1,
                pomodoroTotal = 0
            )
        }
    }
}