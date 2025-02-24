package ru.kryu.videoplayer.presentation.videolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.kryu.videoplayer.domain.VideoRepository
import ru.kryu.videoplayer.domain.model.Video
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repository: VideoRepository
) : ViewModel() {

    private val _state = MutableStateFlow<VideoListState>(VideoListState.Loading)
    val state: StateFlow<VideoListState> = _state

    init {
        loadVideos()
    }

    fun loadVideos() {
        _state.value = VideoListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val videos = repository.getVideos()
                _state.value = VideoListState.Success(videos)
            } catch (e: Exception) {
                _state.value = VideoListState.Error(e)
            }
        }
    }
}

sealed class VideoListState {
    object Loading : VideoListState()
    data class Success(val videos: List<Video>) : VideoListState()
    data class Error(val throwable: Throwable) : VideoListState()
}
