package ru.kryu.videoplayer

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.kryu.videoplayer.presentation.videolist.VideoListScreen
import ru.kryu.videoplayer.presentation.videoplayer.VideoPlayerScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "list") {
        composable("list") { VideoListScreen(navController = navController) }
        composable("player/{videoUrl}") { backStackEntry ->
            VideoPlayerScreen(
                videoUrl = backStackEntry.arguments?.getString("videoUrl") ?: "",
                navController
            )
        }
    }
}
