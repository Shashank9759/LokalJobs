package com.example.lokaljobs.utils

import com.example.lokaljobs.domain.model.JobContent
import com.google.gson.Gson
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun parseAndTranslateContent(rawJson: String?): String {
    if (rawJson.isNullOrEmpty()) return "No description available."

    return try {
        val decoded = URLDecoder.decode(rawJson, StandardCharsets.UTF_8.toString())

        val blocks = Gson().fromJson(decoded, JobContent::class.java)

        val block1English = blocks.block1 ?: ""
        val block2English = blocks.block1 ?: ""
        val block3English = blocks.block1 ?: ""

        """
            $block1English

            $block2English

            $block3English
        """.trimIndent()

    } catch (e: Exception) {
        e.printStackTrace()
        "No description available."
    }
}

fun translateToEnglish(text: String): String {

    return "Translated: $text"
}
