package com.example.lokaljobs.presentation.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokaljobs.domain.model.Job
import com.example.lokaljobs.domain.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    val repository: JobRepository
) : ViewModel() {
    val bookmarkedJobs = repository.getBookmarkedJobs().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun setBookMark(job:Job){
        viewModelScope.launch{
            repository.bookmarkJob(job)
        }
    }

    fun deleteBookMark(id:Int){
        viewModelScope.launch{
            repository.removeBookmark(id)
        }
    }
}
