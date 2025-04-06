package com.example.lokaljobs.presentation.ui.components



import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobCard2(
    job: Job,
    onClick: () -> Unit,
    onBookmarkClick: (Job) -> Unit,
    onBookmarkUnClick: (Job) -> Unit
) {
    // Local state for immediate UI response on bookmark
    var isBookmarked by remember { mutableStateOf(job.isBookMarked) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Title + Bookmark
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = job.title ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

            }

            Spacer(Modifier.height(4.dp))

            // Salary
            if (!job.salary.isNullOrEmpty()) {
                Text(
                    text = "${job.salary}",
                    color = Color(0xFF4CAF50), // Green accent
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Company
            if (!job.companyName.isNullOrEmpty()) {
                Text(
                    text = job.companyName!!,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }

            // Location
            if (!job.location.isNullOrEmpty()) {
                Text(
                    text = job.location!!,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            // Vacancies
            Text(
                text = "${job.vacancies ?: "0"} ",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(8.dp))

            // Row for WhatsApp and Call icons
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val context = LocalContext.current

                job.whatsappUrl?.let { whatsappLink ->
                    IconButton(
                        onClick = {
                            val uri = Uri.parse(whatsappLink)
                            context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ChatBubble,
                            contentDescription = "Chat on WhatsApp",
                            tint = Color(0xFF25D366) // WhatsApp green
                        )
                    }
                }
                job.phone?.let { phoneNumber ->
                    IconButton(
                        onClick = {
                            val uri = Uri.parse("tel:${phoneNumber.filter { c -> c.isDigit() }}")
                            context.startActivity(Intent(Intent.ACTION_DIAL, uri))
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Call HR",
                            tint = Color(0xFFFF9800) // Amber
                        )
                    }
                }
            }
        }
    }
}
