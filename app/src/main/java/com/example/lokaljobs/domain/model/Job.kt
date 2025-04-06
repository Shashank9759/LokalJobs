package com.example.lokaljobs.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Job(
    var isBookMarked: Boolean=false,
    val id: Int?,
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

    ): Parcelable
