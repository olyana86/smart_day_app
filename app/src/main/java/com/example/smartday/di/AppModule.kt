package com.example.smartday.di

import android.content.Context
import com.example.smartday.data.database.SmartDayDatabase
import com.example.smartday.data.daytasks_feature.DayTasksDao
import com.example.smartday.data.pomodoro_feature.PomodoroDao
import com.example.smartday.data.habits_feature.HabitsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSmartDayDatabase(@ApplicationContext context: Context): SmartDayDatabase {
        return SmartDayDatabase.getInstance(context)
    }

    @Provides
    fun provideDayTasksDao(smartDayDatabase: SmartDayDatabase): DayTasksDao {
        return smartDayDatabase.dayTasksDao()
    }

    @Provides
    fun providePomodoroDao(smartDayDatabase: SmartDayDatabase): PomodoroDao {
        return smartDayDatabase.pomodoroDao()
    }

    @Provides
    fun provideHabitsDao(smartDayDatabase: SmartDayDatabase): HabitsDao {
        return smartDayDatabase.habitsDao()
    }
}