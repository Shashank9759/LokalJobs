package com.example.lokaljobs.presentation

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.lokaljobs.domain.model.Job
import com.example.lokaljobs.presentation.bookmarks.BookmarksScreen
import com.example.lokaljobs.presentation.bookmarks.BookmarksViewModel
import com.example.lokaljobs.presentation.job_details.JobDetailsScreen
import com.example.lokaljobs.presentation.job_list.JobsScreen
import com.example.lokaljobs.presentation.job_list.JobsViewModel
import com.example.lokaljobs.presentation.navigation.NavGraph
import com.example.lokaljobs.presentation.navigation.Screen
import com.example.lokaljobs.presentation.ui.components.BottomNavigationBar
import com.example.lokaljobs.ui.theme.LokalJobsAppTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LokalJobsAppTheme {
                val selectedJob = remember { mutableStateOf<Job?>(null) }
                val bookmarksViewModel: BookmarksViewModel = viewModel()
                val jobsViewModel: JobsViewModel = viewModel()

                Scaffold { paddingValues ->
                    Surface(modifier = Modifier.padding(paddingValues)) {
                        if (selectedJob.value != null) {
                            JobDetailsScreen(
                                job = selectedJob.value!!,
                                onBookmarkClick = { job -> bookmarksViewModel.setBookMark(job) },
                                onBookmarkunClick = { job -> bookmarksViewModel.deleteBookMark(job.id!!) },
                                onBack = { selectedJob.value = null }
                            )
                        } else {
                            BottomNavigationBar { screen ->
                                when (screen) {
                                    Screen.Jobs -> {
                                        JobsScreen(
                                            onJobClick = { job -> selectedJob.value = job },
                                            onBookmarkClick = { job -> bookmarksViewModel.setBookMark(job) },
                                            onBookmarkUnClick = { job -> bookmarksViewModel.deleteBookMark(job.id!!) }
                                        )
                                    }
                                    Screen.Bookmarks -> {
                                        BookmarksScreen(
                                            onJobClick = { job -> selectedJob.value = job },
                                            onBookmarkClick = { job -> bookmarksViewModel.setBookMark(job) },
                                            onBookmarkUnClick = { job -> bookmarksViewModel.deleteBookMark(job.id!!) }
                                        )
                                    }
                                    else -> {}
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
