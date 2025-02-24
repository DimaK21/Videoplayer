package ru.kryu.videoplayer.data.repository

import android.util.Log
import ru.kryu.videoplayer.data.database.VideoDao
import ru.kryu.videoplayer.data.mapper.toDomain
import ru.kryu.videoplayer.data.mapper.toEntity
import ru.kryu.videoplayer.data.network.VideoApi
import ru.kryu.videoplayer.domain.VideoRepository
import ru.kryu.videoplayer.domain.model.Video
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val api: VideoApi,
    private val dao: VideoDao
) : VideoRepository {
    override suspend fun getVideos(): List<Video> {
        return try {
            val videos = api
                .getVideos()
                .apply { Log.d("VideoRepositoryImpl", this.results.toString()) }
                .toEntity()
                .apply { Log.d("VideoRepositoryImpl", this.toString()) }
            dao.clearAll()
            dao.insertAll(videos)
            videos.map { it.toDomain() }.apply { Log.d("VideoRepositoryImpl", this.toString()) }
        } catch (e: Exception) {
            Log.d("VideoRepositoryImpl", e.stackTraceToString())
            dao.getAllVideos().map { it.toDomain() }
        }
    }
}