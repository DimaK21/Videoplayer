package ru.kryu.videoplayer.presentation.videoplayer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun VideoPlayerScreen(videoUrl: String) {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }

    var playbackPosition by rememberSaveable { mutableLongStateOf(0L) }
    var isPlaying by rememberSaveable { mutableStateOf(false) }

    DisposableEffect(Unit) {
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()
        player.seekTo(playbackPosition)
        player.play()
        onDispose {
            playbackPosition = player.currentPosition
            isPlaying = player.isPlaying
            player.release()
        }
    }

    AndroidView(
        factory = { PlayerView(context).apply { this.player = player } },
        modifier = Modifier.fillMaxSize()
    )
}
