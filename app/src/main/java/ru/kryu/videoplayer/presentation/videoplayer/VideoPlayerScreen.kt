package ru.kryu.videoplayer.presentation.videoplayer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun VideoPlayerScreen(videoUrl: String, navController: NavController) {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }

    DisposableEffect(Unit) {
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()
        player.play()
        onDispose { player.release() }
    }

    AndroidView(factory = { PlayerView(context).apply { this.player = player } })
}
