package com.example.project_flow_android.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = arrayOf("id", "chatRoomId"))
data class OldMessage(
    @PrimaryKey val id: String,
    @PrimaryKey val chatRoomId: String,
    @ColumnInfo val type: String,
    @ColumnInfo val planId: String?,
    @ColumnInfo val planName: String?,
    @ColumnInfo val startDate: String?,
    @ColumnInfo val endDate: String?,
    @ColumnInfo val message: String?,
    @ColumnInfo val senderName: String,
    @ColumnInfo val senderImage: String,
    @ColumnInfo val readerList: ArrayList<String>,
    @ColumnInfo val createdAt: String,
    @ColumnInfo val mine: Boolean
)
