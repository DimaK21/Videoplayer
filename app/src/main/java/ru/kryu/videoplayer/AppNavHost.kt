package ru.kryu.videoplayer

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.kryu.videoplayer.presentation.videolist.VideoListScreen
import ru.kryu.videoplayer.presentation.videoplayer.VideoPlayerScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "list") {

        composable(
            "list",
            arguments = listOf(navArgument("videoUrl") { type = NavType.StringType })
        ) { VideoListScreen(navController = navController) }

        composable("player/{videoUrl}") { backStackEntry ->
            VideoPlayerScreen(
                videoUrl = Uri.decode(backStackEntry.arguments?.getString("videoUrl") ?: ""),
                navController
            )
        }
    }
}
