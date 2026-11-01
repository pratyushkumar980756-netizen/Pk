package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate900

@Composable
fun QuickFabMenu(
    isExpanded: Boolean,
    isHindi: Boolean,
    onToggleExpand: () -> Unit,
    onCallPrincipal: () -> Unit,
    onDownloadSyllabus: () -> Unit,
    onViewFeeStructure: () -> Unit,
    onJoinWhatsApp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 135f else 0f,
        label = "fab_rotation"
    )

    Column(
        modifier = modifier.testTag("quick_fab_menu_container"),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Expandable speed-dial items
        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 })
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // 1. WhatsApp Channel Quick Link
                FabMenuItem(
                    label = if (isHindi) "व्हाट्सएप चैनल से जुड़ें" else "Join WhatsApp Channel",
                    sublabel = if (isHindi) "दैनिक अपडेट व सूचनाएं" else "Daily School Notices",
                    icon = Icons.Default.Call,
                    backgroundColor = Color(0xFF25D366),
                    contentColor = Color.White,
                    testTag = "fab_whatsapp_channel",
                    onClick = onJoinWhatsApp
                )

                // 2. Call Principal Quick Link
                FabMenuItem(
                    label = if (isHindi) "प्रधानाचार्य को कॉल करें" else "Call Principal",
                    sublabel = if (isHindi) "श्री कामलेश कुमार" else "Kamlesh Kumar",
                    icon = Icons.Default.PhoneInTalk,
                    backgroundColor = RoyalBlue,
                    contentColor = Color.White,
                    testTag = "fab_call_principal",
                    onClick = onCallPrincipal
                )

                // 3. Download Syllabus Quick Link
                FabMenuItem(
                    label = if (isHindi) "पाठ्यक्रम डाउनलोड करें" else "Download Syllabus",
                    sublabel = if (isHindi) "यूपी बोर्ड 1 - 12" else "UP Board Classes 1-12",
                    icon = Icons.Default.Download,
                    backgroundColor = Color(0xFF0284C7),
                    contentColor = Color.White,
                    testTag = "fab_download_syllabus",
                    onClick = onDownloadSyllabus
                )

                // 4. View Fee Structure Quick Link
                FabMenuItem(
                    label = if (isHindi) "शुल्क विवरण देखें" else "View Fee Structure",
                    sublabel = if (isHindi) "सत्र 2026-27" else "Session 2026-27",
                    icon = Icons.Default.CurrencyRupee,
                    backgroundColor = GoldPrimary,
                    contentColor = Color.White,
                    testTag = "fab_view_fee_structure",
                    onClick = onViewFeeStructure
                )
            }
        }

        // Main FAB button
        FloatingActionButton(
            onClick = onToggleExpand,
            modifier = Modifier
                .size(56.dp)
                .testTag("fab_main_toggle"),
            shape = CircleShape,
            containerColor = if (isExpanded) Slate900 else GoldPrimary,
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = if (isExpanded) "Close Quick Menu" else "Open Quick Menu",
                modifier = Modifier
                    .size(26.dp)
                    .rotate(rotation)
            )
        }
    }
}

@Composable
private fun FabMenuItem(
    label: String,
    sublabel: String,
    icon: ImageVector,
    backgroundColor: Color,
    contentColor: Color,
    testTag: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(end = 4.dp)
    ) {
        // Label pill
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Slate900.copy(alpha = 0.92f),
            shadowElevation = 4.dp,
            modifier = Modifier.padding(end = 10.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = label,
                    fontSize = 12.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = sublabel,
                    fontSize = 10.sp,
                    color = GoldLight
                )
            }
        }

        // Action Circle button (min 48dp for accessibility)
        Surface(
            shape = CircleShape,
            color = backgroundColor,
            shadowElevation = 5.dp,
            modifier = Modifier
                .size(48.dp)
                .testTag(testTag)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = contentColor,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}
