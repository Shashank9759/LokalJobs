package com.example.lokaljobs.data.repository
import androidx.paging.map

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.lokaljobs.data.local.dao.JobDao
import com.example.lokaljobs.data.local.entity.toEntity
import com.example.lokaljobs.data.local.entity.toJob
import com.example.lokaljobs.data.remote.api.JobApiService
import com.example.lokaljobs.data.remote.paging.JobPagingSource
import com.example.lokaljobs.data.remote.dto.toJob
import com.example.lokaljobs.domain.model.Job
import com.example.lokaljobs.domain.repository.JobRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(
    private val apiService: JobApiService,
    private val jobDao: JobDao
) : JobRepository {

    override fun getJobsStream(): Flow<PagingData<Job>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { JobPagingSource(apiService) }
        ).flow.map { pagingData ->
            pagingData.map { dto -> dto.toJob() }
        }
    }

    override fun getBookmarkedJobs(): Flow<List<Job>> =
        jobDao.getBookmarkedJobs().map { list -> list.map { it.toJob() } }

    override suspend fun bookmarkJob(job: Job) {
        jobDao.bookmarkJob(job.toEntity())
    }

    override suspend fun removeBookmark(jobId: Int) {
        jobDao.removeBookmark(jobId)
    }
}
