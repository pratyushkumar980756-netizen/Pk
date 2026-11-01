package com.example.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.RoyalBlueContainer
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900

@Composable
fun SyllabusDialog(
    isHindi: Boolean,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    val syllabusList = listOf(
        Triple(
            if (isHindi) "प्राथमिक स्तर (कक्षा 1 - 5)" else "Primary Wing (Class 1 - 5)",
            "UP Basic Shiksha Parishad",
            listOf(
                if (isHindi) "कलरव (हिंदी), गिनतारा (गणित)" else "Kalrav (Hindi), Gintara (Maths)",
                if (isHindi) "रेनबो (अंग्रेजी), हमारा परिवेश (EVS)" else "Rainbow (English), Environment (EVS)",
                if (isHindi) "संस्कृत पीयूषम्, कला एवं संगीत" else "Sanskrit Piyusham, Art & Music"
            )
        ),
        Triple(
            if (isHindi) "उच्च प्राथमिक स्तर (कक्षा 6 - 8)" else "Upper Primary (Class 6 - 8)",
            "UP State Board Curriculum",
            listOf(
                if (isHindi) "मंजरी (हिंदी), रेनबो (अंग्रेजी)" else "Manjari (Hindi), Rainbow (English)",
                if (isHindi) "विज्ञान सीखें (Science), गणित (Maths)" else "General Science, Mathematics",
                if (isHindi) "इतिहास, नागरिक शास्त्र, भूगोल" else "History, Civics, Geography",
                if (isHindi) "संस्कृत मन्दाकिनी, कंप्यूटर शिक्षण" else "Sanskrit Mandakini, Computer Skills"
            )
        ),
        Triple(
            if (isHindi) "माध्यमिक स्तर (हाईस्कूल कक्षा 9 - 10)" else "Secondary / High School (Class 9 - 10)",
            "UPMSP Board Examination Pattern",
            listOf(
                if (isHindi) "हिंदी / प्रारंभिक हिंदी (अनिवार्य)" else "Hindi (Compulsory)",
                if (isHindi) "गणित (Mathematics) / गृह विज्ञान (लड़कियों हेतु)" else "Mathematics / Home Science (Girls)",
                if (isHindi) "विज्ञान (भौतिकी, रसायन, जीव विज्ञान)" else "Science (Physics, Chemistry, Biology)",
                if (isHindi) "सामाजिक विज्ञान (इतिहास, भूगोल, नागरिक, अर्थशास्त्र)" else "Social Sciences (History, Geo, Civics, Eco)",
                if (isHindi) "अंग्रेजी (English) एवं संस्कृत (वैकल्पिक)" else "English & Sanskrit (Elective)"
            )
        ),
        Triple(
            if (isHindi) "इंटरमीडिएट (कक्षा 11 - 12)" else "Intermediate (Class 11 - 12)",
            "Arts & Science Streams (UPMSP)",
            listOf(
                if (isHindi) "विज्ञान संकाय: सामान्य हिंदी, अंग्रेजी, भौतिकी, रसायन, गणित / जीव विज्ञान" else "Science Stream: Gen. Hindi, English, Physics, Chem, Maths / Biology",
                if (isHindi) "कला संकाय: सामान्य हिंदी, अंग्रेजी, इतिहास, भूगोल, नागरिक शास्त्र, समाजशास्त्र" else "Arts Stream: Gen. Hindi, English, History, Geo, Civics, Sociology"
            )
        )
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .testTag("syllabus_dialog_card"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = RoyalBlueContainer,
                            modifier = Modifier.size(38.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = null,
                                    tint = RoyalBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = if (isHindi) "पाठ्यक्रम विवरण (Syllabus)" else "Academic Syllabus",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900
                            )
                            Text(
                                text = if (isHindi) "कक्षा 1 से 12 • यूपी बोर्ड 2026-27" else "Classes 1 to 12 • UP Board 2026-27",
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

                // Download banner
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = RoyalBlueContainer,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.PictureAsPdf,
                            contentDescription = null,
                            tint = RoyalBlue,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isHindi) "आधिकारिक यूपी बोर्ड पाठ्यक्रम (PDF)" else "Official UP Board Syllabus (PDF)",
                                fontSize = 12.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = RoyalBlueDark
                            )
                            Text(
                                text = if (isHindi) "नवीनतम शैक्षणिक सत्र हेतु संपूर्ण विषय सूची" else "Complete subject guide for all classes",
                                fontSize = 11.sp,
                                color = Slate700
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Grades list
                syllabusList.forEach { (title, board, subjects) ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Slate100),
                        border = androidx.compose.foundation.BorderStroke(0.8.dp, Slate200)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = title,
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Slate900
                                )
                            }
                            Text(
                                text = board,
                                fontSize = 10.5.sp,
                                color = RoyalBlue,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            subjects.forEach { subject ->
                                Row(
                                    modifier = Modifier.padding(vertical = 2.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = EmeraldSuccess,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = subject,
                                        fontSize = 12.sp,
                                        color = Slate800,
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Download Button
                Button(
                    onClick = {
                        val toastMsg = if (isHindi) {
                            "पाठ्यक्रम (PDF) सफलतापूर्वक डाउनलोड हो गया है।"
                        } else {
                            "Syllabus (PDF) downloaded successfully."
                        }
                        Toast.makeText(context, toastMsg, Toast.LENGTH_LONG).show()
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .testTag("download_syllabus_confirm_button"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue)
                ) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isHindi) "पाठ्यक्रम डाउनलोड करें (Download PDF)" else "Download Syllabus (PDF)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = if (isHindi) "बंद करें" else "Close",
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
