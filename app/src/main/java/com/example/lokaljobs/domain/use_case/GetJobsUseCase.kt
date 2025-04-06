package com.example.lokaljobs.domain.use_case

import androidx.paging.PagingData
import com.example.lokaljobs.domain.model.Job
import com.example.lokaljobs.domain.repository.JobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJobsUseCase @Inject constructor(
    private val repository: JobRepository
) {
    operator fun invoke(): Flow<PagingData<Job>> = repository.getJobsStream()
}
