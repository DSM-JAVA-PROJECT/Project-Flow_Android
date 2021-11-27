package com.example.project_flow_android.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OldMessageDao {
    @Query("SELECT * FROM oldmessage")
    fun getAll(): List<OldMessage>

    @Insert
    fun insertMessage(oldMessage: OldMessage)
}