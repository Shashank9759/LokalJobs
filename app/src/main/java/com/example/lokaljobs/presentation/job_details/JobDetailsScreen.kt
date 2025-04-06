@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lokaljobs.presentation.job_details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lokaljobs.domain.model.Job
import com.example.lokaljobs.utils.parseAndTranslateContent

@Composable
fun JobDetailsScreen(
    job: Job,
    onBookmarkClick: (Job) -> Unit,
    onBookmarkunClick: (Job) -> Unit,
    onBack: () -> Unit
) {
    // Local mutable state for bookmark toggle
    var isBookmarked by remember { mutableStateOf(job.isBookMarked) }
    // Decode and (optionally) translate the content
    val translatedContent = parseAndTranslateContent(job.content)
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Use a Row to reserve space for the bookmark icon.
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = job.title ?: "",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
//                        IconButton(onClick = {
//                            // Toggle the bookmark state
//                            isBookmarked = !isBookmarked
//                            // Update job property if needed
//                            job.isBookMarked = isBookmarked
//                            // Call appropriate callback
//                            if (isBookmarked) {
//                                onBookmarkClick(job)
//                            } else {
//                                onBookmarkunClick(job)
//                            }
//                        }) {
//                            Icon(
//                                imageVector = if (isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
//                                contentDescription = "Bookmark",
//                                tint = if (isBookmarked) Color.Red else Color.Gray
//                            )
//                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                // Customize colors if needed.
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        // Make content scrollable
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFF7F7F7))
                .padding(16.dp)
        ) {
            // Job Title and Salary
            Text(
                text = job.title ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Salary: ₹${job.salary}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4CAF50)
            )
            Spacer(Modifier.height(8.dp))

            // Company, Location, Vacancies
            Text(
                text = job.companyName ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = job.location ?: "",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "${job.vacancies} ",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(Modifier.height(16.dp))

            // Job Highlights
            Text("Job Highlights", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(Modifier.height(8.dp))
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Experience: ${job.experience ?: "N/A"}")
                    Text("Qualification: ${job.qalifications ?: "N/A"}")
                    Text("Gender: ${job.gender ?: "N/A"}")
                    Text("Shift Timing: ${job.shiftTiming ?: "N/A"}")
                }
            }

            Spacer(Modifier.height(16.dp))

            // Job Description
            Text("Job Description", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(Modifier.height(8.dp))
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(translatedContent)
                }
            }

            Spacer(Modifier.height(16.dp))

            // WhatsApp Button
            job.whatsappUrl?.let {
                Button(
                    onClick = {
                        val uri = Uri.parse(it)
                        context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF25D366),
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        Icons.Default.ChatBubble,
                        contentDescription = "WhatsApp",
                        tint = Color.White
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Chat on WhatsApp")
                }
            }

            Spacer(Modifier.height(8.dp))

            // Call Button
            job.phone?.let {
                Button(
                    onClick = {
                        val phoneUri = Uri.parse("tel:${it.filter { c -> c.isDigit() }}")
                        context.startActivity(Intent(Intent.ACTION_DIAL, phoneUri))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFC107),
                        contentColor = Color.Black
                    )
                ) {
                    Icon(Icons.Default.Call, contentDescription = "Call")
                    Spacer(Modifier.width(8.dp))
                    Text("Call HR")
                }
            }

            Spacer(Modifier.height(16.dp))
            // Additional disclaimers, etc.
            Text(
                text = "Disclaimer: Recruiters should not charge any fee...",
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(8.dp))

        }
    }
}
