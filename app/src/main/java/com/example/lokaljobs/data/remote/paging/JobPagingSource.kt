package com.example.lokaljobs.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lokaljobs.data.remote.api.JobApiService
import com.example.lokaljobs.data.remote.dto.JobDto
import com.example.lokaljobs.data.remote.dto.Result
import com.example.lokaljobs.utils.Resource
import jakarta.inject.Inject

class JobPagingSource (
    private val apiService: JobApiService
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getJobs(page)
            val jobs = response.results  ?: emptyList()
            LoadResult.Page(
                data = jobs ,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (jobs.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? =
        state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}
