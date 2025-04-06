package com.example.lokaljobs.data.remote.api

import com.example.lokaljobs.data.remote.dto.JobModel
import com.example.lokaljobs.data.remote.dto.JobsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface JobApiService {
    @GET("common/jobs")
    suspend fun getJobs(
        @Query("page") page: Int
    ): JobModel
}
