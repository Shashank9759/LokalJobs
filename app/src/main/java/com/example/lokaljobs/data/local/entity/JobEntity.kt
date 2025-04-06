package com.example.lokaljobs.data.local.entity



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_jobs")
data class JobEntity(
    @PrimaryKey val id: Int,
    var isBookMarked: Boolean=false,

    val title: String?,
    val location: String?,
    val salary: String?,
    val phone: String?,
    val content: String?,
    val companyName: String?,
    val vacancies: String?,
    val whatsappUrl: String?,

    val experience:String?,
    val qalifications: String?,
    val gender: String?,
    val shiftTiming:String?
)
