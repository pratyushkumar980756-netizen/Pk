package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.RubyLight
import com.example.ui.theme.RubyRed
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate900
import kotlinx.coroutines.delay

data class TickerAnnouncement(
    val id: String,
    val categoryHindi: String,
    val categoryEnglish: String,
    val textHindi: String,
    val textEnglish: String,
    val date: String,
    val isUrgent: Boolean = true
)

object TickerDataSource {
    val announcements = listOf(
        TickerAnnouncement(
            id = "t1",
            categoryHindi = "बोर्ड परीक्षा",
            categoryEnglish = "Board Exam",
            textHindi = "🔴 यूपी बोर्ड 10वीं व 12वीं की प्री-बोर्ड परीक्षाएं जनवरी में एवं मुख्य बोर्ड परीक्षाएं 15 फरवरी से प्रस्तावित हैं।",
            textEnglish = "🔴 UP Board 10th & 12th Pre-board exams scheduled in January; Board exams from Feb 15.",
            date = "सत्र 2026-27",
            isUrgent = true
        ),
        TickerAnnouncement(
            id = "t2",
            categoryHindi = "अवकाश सूचना",
            categoryEnglish = "Holiday Notice",
            textHindi = "📢 दीपावली एवं छठ महापर्व के उपलक्ष्य में विद्यालय 3 दिन के लिए बंद रहेगा। पठन-पाठन सोमवार से पूर्ववत प्रारंभ होगा।",
            textEnglish = "📢 School will remain closed for 3 days on account of festival holidays. Classes resume Monday.",
            date = "अवकाश",
            isUrgent = true
        ),
        TickerAnnouncement(
            id = "t3",
            categoryHindi = "प्रवेश सूचना",
            categoryEnglish = "Admissions",
            textHindi = "⭐ कक्षा 1 से 8 (CBSE बोर्ड) एवं 9 से 12 (UP बोर्ड) हेतु प्रवेश फॉर्म उपलब्ध हैं। शुल्क: मात्र ₹300/माह।",
            textEnglish = "⭐ Admission forms open for CBSE (1st-8th) & UP Board (9th-12th). Monthly fee: ₹300 only.",
            date = "सत्र 2026-27",
            isUrgent = false
        ),
        TickerAnnouncement(
            id = "t4",
            categoryHindi = "हेल्पलाइन",
            categoryEnglish = "Helpline",
            textHindi = "📞 सीधे कॉल व व्हाट्सएप संपर्क: +91 6393731223, 9005520694 (मुबारकपुर कतरौली कोड: 551642)",
            textEnglish = "📞 Direct Helpline & WhatsApp: +91 6393731223, 9005520694 (Code: 551642)",
            date = "संपर्क सूत्र",
            isUrgent = false
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsTickerBar(
    isHindi: Boolean,
    onOpenAllNotices: () -> Unit,
    modifier: Modifier = Modifier
) {
    val items = TickerDataSource.announcements
    val fullTickerText = remember(isHindi) {
        items.joinToString("   •••   ") { if (isHindi) it.textHindi else it.textEnglish }
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .testTag("news_ticker_bar"),
        color = RubyRed,
        shadowElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .clickable(onClick = onOpenAllNotices),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Urgency Badge
            Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color.White,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Campaign,
                        contentDescription = "Urgent Notice",
                        tint = RubyRed,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (isHindi) "नवीनतम अपडेट" else "Latest Updates",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = RubyRed
                    )
                }
            }

            // Scrolling Marquee Announcement Text
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = fullTickerText,
                    color = Color.White,
                    fontSize = 12.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    modifier = Modifier
                        .basicMarquee(
                            iterations = Int.MAX_VALUE,
                            repeatDelayMillis = 800,
                            velocity = 45.dp
                        )
                        .testTag("ticker_scrolling_text")
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            // Expand / View details indicator
            Surface(
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.2f),
                modifier = Modifier.size(24.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "View Details",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun UrgentAnnouncementsDialog(
    isHindi: Boolean,
    onDismiss: () -> Unit
) {
    val items = TickerDataSource.announcements

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .testTag("urgent_announcements_dialog"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = RubyLight,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.NotificationsActive,
                                    contentDescription = null,
                                    tint = RubyRed,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = if (isHindi) "महत्वपूर्ण सूचनाएं एवं अलर्ट" else "Urgent Announcements",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900
                            )
                            Text(
                                text = if (isHindi) "परीक्षा तिथि, अवकाश एवं आधिकारिक घोषणाएं" else "Exam dates, holidays & circulars",
                                fontSize = 11.sp,
                                color = Slate600
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Slate600
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // List of announcements
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items.forEach { item ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (item.isUrgent) Color(0xFFFEF2F2) else Color(0xFFF8FAFC),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (item.isUrgent) RubyRed.copy(alpha = 0.3f) else Slate200
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = if (item.isUrgent) RubyRed else RoyalBlue
                                    ) {
                                        Text(
                                            text = if (isHindi) item.categoryHindi else item.categoryEnglish,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }

                                    Text(
                                        text = item.date,
                                        fontSize = 11.sp,
                                        color = Slate600,
                                        fontWeight = FontWeight.Medium
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = if (isHindi) item.textHindi else item.textEnglish,
                                    fontSize = 12.5.sp,
                                    color = Slate900,
                                    lineHeight = 17.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
