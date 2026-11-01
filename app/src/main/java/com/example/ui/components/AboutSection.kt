package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SchoolInfo
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.RoyalBlueContainer
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900

@Composable
fun AboutSection(
    schoolInfo: SchoolInfo,
    isHindi: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("about_us_section"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Section Title Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = RoyalBlueContainer,
                modifier = Modifier.size(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = RoyalBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = if (isHindi) "विद्यालय परिचय (About Us)" else "About Our School",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Text(
                    text = if (isHindi) "संक्षिप्त विवरण एवं शैक्षणिक बोर्ड संरचना" else "Institutional Profile & Educational Boards",
                    fontSize = 12.sp,
                    color = Slate600
                )
            }
        }

        // Exact Profile Details Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Slate200)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // 1. Established Detail Item
                AboutDetailRow(
                    icon = Icons.Default.DateRange,
                    iconTint = RoyalBlue,
                    label = if (isHindi) "स्थापना वर्ष (Established)" else "Established",
                    value = schoolInfo.establishedYear,
                    subtext = if (isHindi) "28+ वर्षों का गौरवशाली शैक्षणिक अनुभव" else "28+ years of dedicated service in rural education"
                )

                HorizontalDivider(color = Slate200, thickness = 0.8.dp)

                // 2. Management Detail Item
                AboutDetailRow(
                    icon = Icons.Default.AccountBalance,
                    iconTint = GoldDark,
                    label = if (isHindi) "प्रबंधन (Management)" else "Management",
                    value = if (isHindi) schoolInfo.managementHindi else schoolInfo.managementEnglish,
                    subtext = if (isHindi) "ग्रामीण परिवेश के छात्र-छात्राओं के हित में समर्पित" else "Dedicated to rural student empowerment"
                )

                HorizontalDivider(color = Slate200, thickness = 0.8.dp)

                // 3. Educational Boards Structure (CBSE 1st-8th & UP Board 9th-12th)
                AboutDetailRow(
                    icon = Icons.Default.School,
                    iconTint = RoyalBlue,
                    label = if (isHindi) "शैक्षणिक बोर्ड संरचना (Educational Boards)" else "Educational Boards Structure",
                    value = if (isHindi)
                        "कक्षा 1 से 8: CBSE बोर्ड\nकक्षा 9 से 12: UP बोर्ड (स्टेट बोर्ड)"
                    else
                        "Class 1st to 8th: CBSE Board\nClass 9th to 12th: UP Board (State Board)",
                    subtext = if (isHindi)
                        "प्राथमिक व मिडिल में केंद्रीय पाठ्यक्रम, हाईस्कूल व इंटरमीडिएट में यूपी बोर्ड"
                    else
                        "CBSE curriculum for foundation years and UP State Board for secondary/senior secondary"
                )

                HorizontalDivider(color = Slate200, thickness = 0.8.dp)

                // 4. Head Teacher Detail Item
                AboutDetailRow(
                    icon = Icons.Default.Person,
                    iconTint = GoldPrimary,
                    label = if (isHindi) "प्रधानाध्यापक (Head Teacher)" else "Head Teacher",
                    value = if (isHindi) schoolInfo.headTeacherHindi else schoolInfo.headTeacherEnglish,
                    subtext = if (isHindi) "अनुभवी नेतृत्व एवं कुशल मार्गदर्शन" else "Experienced academic administration & leadership"
                )

                HorizontalDivider(color = Slate200, thickness = 0.8.dp)

                // 5. Total Teachers Detail Item
                AboutDetailRow(
                    icon = Icons.Default.Group,
                    iconTint = EmeraldSuccess,
                    label = if (isHindi) "कुल शिक्षक (Total Teachers)" else "Total Teachers",
                    value = "${schoolInfo.totalTeachers} Qualified Teachers (7 शिक्षक)",
                    subtext = if (isHindi) "समर्पित एवं योग्य शिक्षण संकाय" else "7 devoted full-time subject faculty members"
                )
            }
        }

        // Head Teacher Message Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = RoyalBlueContainer),
            border = androidx.compose.foundation.BorderStroke(1.dp, RoyalBlue.copy(alpha = 0.2f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(RoyalBlueDark),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Head Teacher",
                            tint = GoldAccent,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isHindi) schoolInfo.headTeacherHindi else schoolInfo.headTeacherEnglish,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = RoyalBlueDark
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = GoldLight
                            ) {
                                Text(
                                    text = if (isHindi) "प्रधानाचार्य" else "Principal",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = GoldDark,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = if (isHindi) "जनता इंटर कॉलेज मुबारकपुर कतरौली (कोड: 551642)" else "Janta Inter College (Code: 551642)",
                            fontSize = 12.sp,
                            color = Slate700
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row {
                    Icon(
                        imageVector = Icons.Default.FormatQuote,
                        contentDescription = null,
                        tint = GoldPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isHindi)
                            "\"हमारा ध्येय कक्षा 1 से 8 तक सीबीएसई पैटर्न और कक्षा 9 से 12 तक यूपी बोर्ड के माध्यम से प्रत्येक छात्र को मजबूत नींव और उच्च सफलता दिलाना है। मात्र ₹300 मासिक शुल्क में गुणवत्तापूर्ण शिक्षा हमारा संकल्प है।\""
                        else
                            "\"Our mission is providing CBSE standard foundation for Classes 1-8 and UP Board excellence for Classes 9-12 at an affordable monthly fee of ₹300 only.\"",
                        fontSize = 13.sp,
                        fontStyle = FontStyle.Italic,
                        lineHeight = 19.sp,
                        color = Slate800
                    )
                }
            }
        }

        // Quick Stats Strip
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatCard(
                modifier = Modifier.weight(1f),
                title = if (isHindi) "स्थापना" else "Est.",
                stat = "1998",
                subtitle = if (isHindi) "वर्ष" else "Year",
                accent = RoyalBlue
            )
            StatCard(
                modifier = Modifier.weight(1f),
                title = if (isHindi) "सीबीएसई" else "CBSE",
                stat = "1st-8th",
                subtitle = if (isHindi) "बोर्ड" else "Wing",
                accent = GoldPrimary
            )
            StatCard(
                modifier = Modifier.weight(1f),
                title = if (isHindi) "यूपी बोर्ड" else "UP Board",
                stat = "9th-12th",
                subtitle = if (isHindi) "बोर्ड" else "Wing",
                accent = RoyalBlueDark
            )
            StatCard(
                modifier = Modifier.weight(1f),
                title = if (isHindi) "शिक्षक" else "Teachers",
                stat = "7",
                subtitle = if (isHindi) "शिक्षक" else "Staff",
                accent = EmeraldSuccess
            )
        }
    }
}

@Composable
private fun AboutDetailRow(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    subtext: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = iconTint.copy(alpha = 0.12f),
            modifier = Modifier.size(40.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Slate600
            )
            Text(
                text = value,
                fontSize = 14.5.sp,
                fontWeight = FontWeight.Bold,
                color = Slate900,
                lineHeight = 20.sp
            )
            Text(
                text = subtext,
                fontSize = 12.sp,
                color = Slate600,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    title: String,
    stat: String,
    subtitle: String,
    accent: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 4.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stat,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                color = accent
            )
            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Slate900
            )
            Text(
                text = subtitle,
                fontSize = 10.sp,
                color = Slate600
            )
        }
    }
}
