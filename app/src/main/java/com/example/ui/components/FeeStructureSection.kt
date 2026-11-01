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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.FeeTier
import com.example.model.SchoolDataSource
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
fun FeeStructureSection(
    isHindi: Boolean,
    onCallInquiry: () -> Unit,
    modifier: Modifier = Modifier
) {
    val feeTiers = SchoolDataSource.feeStructure

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("fee_structure_section"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Section Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = GoldLight,
                modifier = Modifier.size(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.CurrencyRupee,
                        contentDescription = null,
                        tint = GoldDark,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = if (isHindi) "शुल्क संरचना (Fee Structure)" else "Fee Structure",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Text(
                    text = if (isHindi) "सत्र 2026-27 • किफायती ग्रामीण मासिक शुल्क" else "Session 2026-27 • Transparent Monthly Fees",
                    fontSize = 12.sp,
                    color = Slate600
                )
            }
        }

        // Two Pricing Cards (CBSE 1-8 and UP Board 9-12)
        feeTiers.forEach { tier ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("fee_card_${tier.amount}"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.5.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Slate200)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = RoyalBlueContainer
                            ) {
                                Text(
                                    text = if (isHindi) tier.boardHindi else tier.boardEnglish,
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = RoyalBlueDark,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isHindi) tier.gradesHindi else tier.gradesEnglish,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Slate900
                            )
                        }

                        // Price Tag
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = GoldLight,
                            border = androidx.compose.foundation.BorderStroke(1.dp, GoldAccent)
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = tier.amount,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Black,
                                    color = GoldDark
                                )
                                Text(
                                    text = if (isHindi) "प्रति माह" else "/ month",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Slate700
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = Slate200, thickness = 0.8.dp)
                    Spacer(modifier = Modifier.height(10.dp))

                    // Features list
                    tier.features.forEach { feat ->
                        Row(
                            modifier = Modifier.padding(vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = EmeraldSuccess,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = feat,
                                fontSize = 12.5.sp,
                                color = Slate800,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        // Scholarship & Policy Note
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = EmeraldLight,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = null,
                    tint = EmeraldSuccess,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = if (isHindi) "पारदर्शी शुल्क एवं छात्रवृत्ति सुविधा" else "Scholarship Support & Affordable Policy",
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = Slate900
                    )
                    Text(
                        text = if (isHindi)
                            "सभी वर्गों के लिए मात्र ₹300 प्रति माह। यूपी सरकार छात्रवृत्ति में विद्यालय द्वारा पूर्ण सहयोग।"
                        else
                            "Only ₹300/month for all grades. Full assistance for government scholarship schemes.",
                        fontSize = 11.5.sp,
                        color = Slate700
                    )
                }
            }
        }
    }
}
