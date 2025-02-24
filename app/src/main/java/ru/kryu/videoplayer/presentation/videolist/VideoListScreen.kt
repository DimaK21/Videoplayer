package ru.kryu.videoplayer.presentation.videolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.kryu.videoplayer.domain.model.Video

@Composable
fun VideoListScreen(viewModel: VideoViewModel = hiltViewModel(), navController: NavController) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is VideoListState.Loading -> CircularProgressIndicator()
        is VideoListState.Success -> {
            val videos = (state as VideoListState.Success).videos
            LazyColumn {
                items(videos) { video ->
                    VideoItem(video) { navController.navigate("player/${video.videoUrl}") }
                }
            }
        }

        is VideoListState.Error -> Text("Ошибка загрузки")
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VideoItem(video: Video, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)
        .padding(8.dp)) {
        Row {
            GlideImage(
                model = video.thumbnailUrl,
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Column {
                Text(video.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(video.duration.toString(), fontSize = 14.sp)
            }
        }
    }
}