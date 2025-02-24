package ru.kryu.videoplayer.presentation.videolist

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.kryu.videoplayer.R
import ru.kryu.videoplayer.domain.model.Video

@Composable
fun VideoListScreen(viewModel: VideoViewModel = hiltViewModel(), navController: NavController) {
    val state by viewModel.state.collectAsState()
    val isRefreshing by remember { mutableStateOf(false) }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.loadVideos() }
    ) {
        when (state) {
            is VideoListState.Loading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            is VideoListState.Success -> {
                val videos = (state as VideoListState.Success).videos
                LazyColumn {
                    items(videos) { video ->
                        VideoItem(video) { navController.navigate("player/${Uri.encode(video.videoUrl)}") }
                    }
                }
            }

            is VideoListState.Error -> {
                val error = (state as VideoListState.Error).throwable
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            stringResource(R.string.error_load, error.localizedMessage ?: ""),
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.loadVideos() }) {
                            Text(stringResource(R.string.try_again))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = stringResource(R.string.vpn), fontSize = 8.sp)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VideoItem(video: Video, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                onClick = onClick,
                indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() }
            ),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            GlideImage(
                model = video.thumbnailUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(video.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, maxLines = 2)
                Text(
                    stringResource(R.string.seconds, video.duration),
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}