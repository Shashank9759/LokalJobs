package com.example.lokaljobs.domain.repository

import androidx.paging.PagingData
import com.example.lokaljobs.domain.model.Job
import kotlinx.coroutines.flow.Flow

interface JobRepository {
    fun getJobsStream(): Flow<PagingData<Job>>
    fun getBookmarkedJobs(): Flow<List<Job>>
    suspend fun bookmarkJob(job: Job)
    suspend fun removeBookmark(jobId: Int)
}
