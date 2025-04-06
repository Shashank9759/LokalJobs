package com.example.lokaljobs.data.local.entity



import com.example.lokaljobs.domain.model.Job
import kotlin.Boolean

fun JobEntity.toJob(): Job = Job(

 id=id,
 title=title ,
 location=location,
 salary=salary,
 phone=phone,
 content=content,
 companyName=companyName,
 vacancies=vacancies,
 whatsappUrl=whatsappUrl,

 experience=experience,
 qalifications=qalifications,
 gender=gender,
 shiftTiming=shiftTiming
)

fun Job.toEntity(): JobEntity = JobEntity(
    id=id!!.toInt(),
    title=title ,
    location=location,
    salary=salary,
    phone=phone,
    content=content,
    companyName=companyName,
    vacancies=vacancies,
    whatsappUrl=whatsappUrl,

    experience=experience,
    qalifications=qalifications,
    gender=gender,
    shiftTiming=shiftTiming
)
