package ru.kryu.videoplayer.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kryu.videoplayer.data.database.VideoDao
import ru.kryu.videoplayer.data.database.VideoDatabase
import ru.kryu.videoplayer.data.network.VideoApi
import ru.kryu.videoplayer.data.repository.VideoRepositoryImpl
import ru.kryu.videoplayer.domain.VideoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://rutube.ru/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): VideoApi = retrofit.create(VideoApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): VideoDatabase =
        Room.databaseBuilder(context, VideoDatabase::class.java, "videos.db").build()

    @Provides
    @Singleton
    fun provideDao(db: VideoDatabase): VideoDao = db.videoDao()

    @Provides
    @Singleton
    fun provideVideoRepository(
        api: VideoApi,
        dao: VideoDao
    ): VideoRepository = VideoRepositoryImpl(api, dao)
}