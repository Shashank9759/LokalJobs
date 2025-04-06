package com.example.lokaljobs.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import com.example.lokaljobs.presentation.navigation.Screen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigationBar(
    pages: List<Screen> = listOf(Screen.Jobs, Screen.Bookmarks),
    content: @Composable (Screen) -> Unit
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            content(pages[page])
        }
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color(0xFF1565C0),
            contentColor = Color.White
        ) {
            pages.forEachIndexed { index, screen ->
                Tab(
                    text = { Text(screen.route.replaceFirstChar { it.uppercase() }) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
    }
}

