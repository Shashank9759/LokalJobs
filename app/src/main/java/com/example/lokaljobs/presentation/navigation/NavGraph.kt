package com.example.lokaljobs.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lokaljobs.domain.model.Job
import com.example.lokaljobs.presentation.bookmarks.BookmarksScreen
import com.example.lokaljobs.presentation.bookmarks.BookmarksViewModel
import com.example.lokaljobs.presentation.job_details.JobDetailsScreen
import com.example.lokaljobs.presentation.job_list.JobsScreen
import com.example.lokaljobs.presentation.job_list.JobsViewModel
import com.google.gson.Gson
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    object Jobs : Screen("jobs")
    object Bookmarks : Screen("bookmarks")
    object JobDetails : Screen("job_details?job={job}") {
        fun createRoute(jobJson: String) = "job_details?job=$jobJson"
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    val bookmarksViewModel: BookmarksViewModel = viewModel()
    val jobsViewModel: JobsViewModel = viewModel()
    val gson = Gson()
    NavHost(navController = navController, startDestination = Screen.Jobs.route) {
        composable(Screen.Jobs.route) {
            JobsScreen(onJobClick = { job ->
                val jobJson = gson.toJson(job)
                // URL-encode the JSON string
                val encodedJob = Uri.encode(jobJson)
                navController.navigate(Screen.JobDetails.createRoute(encodedJob))
            }

            , onBookmarkClick = {job-> bookmarksViewModel.setBookMark(job) }
            , onBookmarkUnClick = {job-> bookmarksViewModel.deleteBookMark(job.id!!) })
        }
        composable(Screen.Bookmarks.route) {
            BookmarksScreen(onJobClick = { job ->
                val jobJson = gson.toJson(job)
                val encodedJob = Uri.encode(jobJson)
                navController.navigate(Screen.JobDetails.createRoute(encodedJob))
            }
            , onBookmarkUnClick = {job-> bookmarksViewModel.deleteBookMark(job.id!!) },
                onBookmarkClick = {job-> bookmarksViewModel.setBookMark(job)})
        }
        composable(
            route = Screen.JobDetails.route
        ) { backStackEntry ->
            val encodedJob = backStackEntry.arguments?.getString("job")
            val jobJson = encodedJob?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) }
            val job = jobJson?.let { gson.fromJson(it, Job::class.java) } ?: Job(false,0, "No Job Found", "", "", "", "","","","","","","","")
            JobDetailsScreen(
                job = job ,
                onBookmarkClick = { bookmarksViewModel.setBookMark(job) },
                onBookmarkunClick = { bookmarksViewModel.deleteBookMark(job.id!!) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
