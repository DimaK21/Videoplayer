package ru.kryu.videoplayer.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey val id: String,
    val title: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val duration: Int,
)
