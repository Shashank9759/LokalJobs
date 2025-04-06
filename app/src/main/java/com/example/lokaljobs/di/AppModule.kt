package com.example.lokaljobs.di



import android.app.Application
import androidx.room.Room
import com.example.lokaljobs.data.local.db.AppDatabase
import com.example.lokaljobs.data.remote.api.JobApiService
import com.example.lokaljobs.data.repository.JobRepositoryImpl
import com.example.lokaljobs.domain.repository.JobRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://testapi.getlokalapp.com/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideJobApiService(retrofit: Retrofit): JobApiService =
        retrofit.create(JobApiService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "lokal_jobs_db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideJobRepository(
        apiService: JobApiService,
        db: AppDatabase
    ): JobRepository = JobRepositoryImpl(apiService, db.jobDao())
}
