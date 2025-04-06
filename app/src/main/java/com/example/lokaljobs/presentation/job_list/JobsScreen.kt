@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lokaljobs.presentation.job_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.lokaljobs.domain.model.Job
import com.example.lokaljobs.presentation.ui.components.JobCard

@Composable
fun JobsScreen(
    viewModel: JobsViewModel = hiltViewModel(),
    onJobClick: (Job) -> Unit,
    onBookmarkClick: (Job) -> Unit,
    onBookmarkUnClick: (Job) -> Unit
) {
    val lazyJobs = viewModel.jobs.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            // A center-aligned TopAppBar for a professional look
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lokal Jobs",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = { /* TODO: Handle Search Action */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /* TODO: Handle Filter Action */ }) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter",
                            tint = Color.White
                        )
                    }
                },
                // Example custom color scheme for the top bar
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF1565C0), // Blue shade
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // LazyColumn for job listings
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp)
            ) {
                // Items
                items(lazyJobs.itemCount) { index ->
                    val job = lazyJobs[index]
                    if (job != null && !job.title.isNullOrEmpty()) {
                        JobCard(
                            job = job,
                            onClick = { onJobClick(job) },
                            onBookmarkClick = onBookmarkClick,
                            onBookmarkUnClick = onBookmarkUnClick
                        )
                    }
                }

                // LoadState Handling
                lazyJobs.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                        loadState.append is LoadState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                        loadState.refresh is LoadState.Error -> {
                            item {
                                Text(
                                    "Error loading jobs",
                                    modifier = Modifier.padding(16.dp),
                                    color = Color.Red
                                )
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            item {
                                Text(
                                    "Error loading more jobs",
                                    modifier = Modifier.padding(16.dp),
                                    color = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
