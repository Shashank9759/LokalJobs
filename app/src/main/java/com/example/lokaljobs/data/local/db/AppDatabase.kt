package com.example.lokaljobs.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lokaljobs.data.local.dao.JobDao
import com.example.lokaljobs.data.local.entity.JobEntity

@Database(entities = [JobEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao
}
