package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.RoyalBlueContainer
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900

val WhatsAppGreen = Color(0xFF25D366)
val WhatsAppGreenDark = Color(0xFF128C7E)
val WhatsAppGreenContainer = Color(0xFFE8F8F0)

@Composable
fun WhatsAppChannelCard(
    isHindi: Boolean,
    onJoinChannel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("whatsapp_channel_card"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, WhatsAppGreen.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            WhatsAppGreenContainer,
                            Color.White
                        )
                    )
                )
                .padding(16.dp)
        ) {
            // Header Tag
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = WhatsAppGreenDark
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isHindi) "आधिकारिक व्हाट्सएप चैनल" else "Official WhatsApp Channel",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = EmeraldLight
                ) {
                    Text(
                        text = if (isHindi) "मुफ्त एवं सुरक्षित" else "Free & Safe",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = EmeraldSuccess,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Main Title & Intro
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = WhatsAppGreen,
                    modifier = Modifier.size(46.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "WhatsApp",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = if (isHindi) "व्हाट्सएप चैनल से जुड़ें" else "Join Official WhatsApp Channel",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Slate900
                    )
                    Text(
                        text = if (isHindi)
                            "जनता इंटर कॉलेज मुबारकपुर कतरौली (कोड: 551642)"
                        else
                            "Janta Inter College Mubarakpur Katrauli (Code: 551642)",
                        fontSize = 12.sp,
                        color = Slate600
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (isHindi)
                    "छात्र, अभिभावक एवं क्षेत्रीय नागरिक विद्यालय की दैनिक सूचनाएं, परीक्षा समय-सारणी, अवकाश एवं बोर्ड रिजल्ट सीधे अपने फोन पर पाएं।"
                else
                    "Students, parents, and community members: get daily announcements, exam timetables, holiday circulars & board results directly on WhatsApp.",
                fontSize = 12.5.sp,
                color = Slate700,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Bullets
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                ChannelFeatureBullet(
                    text = if (isHindi) "दैनिक विद्यालय सूचनाएं एवं परीक्षा समय-सारणी" else "Daily school notices & examination dates"
                )
                ChannelFeatureBullet(
                    text = if (isHindi) "आपातकालीन अवकाश एवं मौसम अलर्ट की तुरंत जानकारी" else "Instant emergency holiday & weather updates"
                )
                ChannelFeatureBullet(
                    text = if (isHindi) "यूपी बोर्ड हाईस्कूल व इंटरमीडिएट रिजल्ट व मार्गदर्शन" else "UP Board 10th & 12th results and admissions"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // CTA Button
            Button(
                onClick = onJoinChannel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("join_whatsapp_channel_button"),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhatsAppGreenDark,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.OpenInNew,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isHindi) "व्हाट्सएप चैनल से जुड़ें (Join WhatsApp)" else "Join WhatsApp Channel",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun ChannelFeatureBullet(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = WhatsAppGreenDark,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            color = Slate800,
            lineHeight = 16.sp
        )
    }
}

@Composable
fun QuickContactBar(
    isHindi: Boolean,
    onCall: () -> Unit,
    onEmail: () -> Unit,
    onLocation: () -> Unit,
    onShare: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("quick_contact_bar"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Slate900),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ContactActionItem(
                icon = Icons.Default.Call,
                label = if (isHindi) "कॉल करें" else "Call",
                tint = WhatsAppGreen,
                onClick = onCall,
                testTag = "quick_contact_call"
            )

            ContactActionItem(
                icon = Icons.Default.Email,
                label = if (isHindi) "ईमेल" else "Email",
                tint = GoldAccent,
                onClick = onEmail,
                testTag = "quick_contact_email"
            )

            ContactActionItem(
                icon = Icons.Default.LocationOn,
                label = if (isHindi) "स्थान" else "Location",
                tint = Color(0xFF60A5FA),
                onClick = onLocation,
                testTag = "quick_contact_location"
            )

            ContactActionItem(
                icon = Icons.Default.Share,
                label = if (isHindi) "साझा करें" else "Share",
                tint = Color.White,
                onClick = onShare,
                testTag = "quick_contact_share"
            )
        }
    }
}

@Composable
private fun ContactActionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    tint: Color,
    onClick: () -> Unit,
    testTag: String
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}
