@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lokaljobs.presentation.bookmarks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lokaljobs.presentation.ui.components.JobCard
import com.example.lokaljobs.domain.model.Job
import com.example.lokaljobs.presentation.ui.components.JobCard2

@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel = hiltViewModel(),
    onJobClick: (Job) -> Unit,
    onBookmarkClick: (Job) -> Unit,
    onBookmarkUnClick: (Job) -> Unit
) {
    val bookmarkedJobs by viewModel.bookmarkedJobs.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Bookmarked Jobs", color = Color.White) },
                colors = androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF1565C0) // A professional blue shade
                )
            )
        }
    ) { paddingValues ->
        if (bookmarkedJobs.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No Bookmarked Jobs", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 12.dp)
            ) {
                items(bookmarkedJobs) { job ->

                    JobCard2(
                        job = job,
                        onClick = { onJobClick(job) },
                        onBookmarkClick = onBookmarkClick,
                        onBookmarkUnClick = onBookmarkUnClick
                    )
                }
            }
        }
    }
}
