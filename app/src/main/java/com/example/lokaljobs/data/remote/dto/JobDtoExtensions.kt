package com.example.lokaljobs.data.remote.dto

import android.os.Parcelable
import com.example.lokaljobs.domain.model.Job

fun Result.toJob(): Job = Job(
    id = id,
    title = title,
    location = primary_details?.Place?:"Unknown",
    salary = primary_details?.Salary ?: "",

    phone = this.custom_link,
    content = this.content,
    companyName= this.company_name  ,
            vacancies = this.job_tags?.get(0)?.value?.let { link ->
                link
            },
            whatsappUrl= this.contact_preference?.whatsapp_link?.let { link ->
               link
            } ,

        experience = this.primary_details?.Experience
    ,

            qalifications= this.primary_details?.Qualification ,

            gender= this.contentV3?.V3?.get(1)?.field_value.let { link ->
                link
            }  ,

            shiftTiming=  this.contentV3?.V3?.get(2)?.field_name.let { link ->
                link
            }
)




