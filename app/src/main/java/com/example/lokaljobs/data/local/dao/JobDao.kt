package com.example.lokaljobs.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lokaljobs.data.local.entity.JobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkJob(job: JobEntity)

    @Query("SELECT * FROM bookmarked_jobs")
    fun getBookmarkedJobs(): Flow<List<JobEntity>>

    @Query("DELETE FROM bookmarked_jobs WHERE id = :jobId")
    suspend fun removeBookmark(jobId: Int)
}
