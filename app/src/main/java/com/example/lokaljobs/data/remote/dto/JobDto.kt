package com.example.lokaljobs.data.remote.dto

import com.google.gson.annotations.SerializedName

data class JobDto(
    val id: Int?,
    val title: String?,
    val primary_details: PrimaryDetailsDto?,
    @SerializedName("custom_link")
    val customLink: String?,
    @SerializedName("button_text")
    val buttonText: String?,
    val content: String?
)
