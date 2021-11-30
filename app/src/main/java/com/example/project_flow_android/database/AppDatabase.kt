package com.example.project_flow_android.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OldMessage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun oldMessageDao() : OldMessageDao
}