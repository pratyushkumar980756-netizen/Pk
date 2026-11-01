package com.example.ui.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SchoolInfo
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.RoyalBlueContainer
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.RubyLight
import com.example.ui.theme.RubyRed
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate900

@Composable
fun ContactSection(
    schoolInfo: SchoolInfo,
    isHindi: Boolean,
    onCallSchool: () -> Unit,
    onOpenMap: () -> Unit,
    onShareApp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var userName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var queryMessage by remember { mutableStateOf("") }
    var validationError by remember { mutableStateOf<String?>(null) }
    var isSubmitted by remember { mutableStateOf(false) }

    fun callNumber(rawNumber: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$rawNumber"))
            context.startActivity(intent)
        } catch (e: Exception) {
            // fallback
        }
    }

    fun openWhatsAppChat(rawNumber: String) {
        try {
            val clean = rawNumber.replace("+", "").replace(" ", "").trim()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$clean?text=${Uri.encode("नमस्ते, मुझे जनता इंटर कॉलेज मुबारकपुर कतरौली में प्रवेश / जानकारी चाहिए।")}"))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Opening WhatsApp...", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("contact_section"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Section Header
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
                        imageVector = Icons.Default.ContactPhone,
                        contentDescription = null,
                        tint = RoyalBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = if (isHindi) "संपर्क एवं लोकेशन (Contact & Location)" else "Contact Numbers & Location",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Text(
                    text = if (isHindi) "सीधे कॉल एवं व्हाट्सएप संदेश हेतु आधिकारिक हेल्पलाइन" else "Direct Helpline & WhatsApp Messaging",
                    fontSize = 12.sp,
                    color = Slate600
                )
            }
        }

        // Direct Helpline Numbers Card (+91 6393731223 & 9005520694)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("direct_helpline_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.5.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Slate200)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = if (isHindi) "सीधे संपर्क एवं व्हाट्सएप नंबर (Direct Helpline):" else "Official Contact & Messaging Numbers:",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = RoyalBlueDark
                )
                Spacer(modifier = Modifier.height(12.dp))

                // Number 1: +91 6393731223
                HelplineRow(
                    displayNumber = "+91 6393731223",
                    label = if (isHindi) "हेल्पलाइन 1 (प्रवेश एवं पूछताछ)" else "Helpline 1 (Admissions & Info)",
                    onCall = { callNumber("+916393731223") },
                    onWhatsApp = { openWhatsAppChat("916393731223") },
                    isHindi = isHindi
                )

                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = Slate200, thickness = 0.8.dp)
                Spacer(modifier = Modifier.height(10.dp))

                // Number 2: 9005520694
                HelplineRow(
                    displayNumber = "9005520694",
                    label = if (isHindi) "हेल्पलाइन 2 (प्रशासन एवं कार्यालय)" else "Helpline 2 (Office Administration)",
                    onCall = { callNumber("9005520694") },
                    onWhatsApp = { openWhatsAppChat("919005520694") },
                    isHindi = isHindi
                )
            }
        }

        // Live Location Card: Mubarakpur Katrauli, Phoolpur, Prayagraj (Code: 551642)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("live_location_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.5.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Slate200)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.Top) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = RoyalBlueContainer,
                        modifier = Modifier.size(42.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = RoyalBlue,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isHindi) "विद्यालय पता एवं लाइव लोकेशन" else "School Location & Address",
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Slate600
                        )
                        Text(
                            text = if (isHindi)
                                "मुबारकपुर कतरौली, फूलपुर, प्रयागराज, उत्तर प्रदेश (विद्यालय कोड: 551642)"
                            else
                                "Mubarakpur Katrauli, Phoolpur, Prayagraj, Uttar Pradesh (School Code: 551642)",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900,
                            lineHeight = 20.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (isHindi)
                                "प्रयागराज - जौनपुर राजमार्ग निकट, फूलपुर ब्लॉक"
                            else
                                "Near Phulpur, District Prayagraj, Uttar Pradesh",
                            fontSize = 11.5.sp,
                            color = Slate600
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Directions Button
                Button(
                    onClick = onOpenMap,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .testTag("google_map_directions_button"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue)
                ) {
                    Icon(
                        imageVector = Icons.Default.Map,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isHindi) "Google Map पर देखें (Get Directions)" else "View on Google Maps",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        // Contact Us Query Form
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("contact_form_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Slate200)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = CircleShape,
                        color = GoldLight,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Message,
                                contentDescription = null,
                                tint = GoldDark,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = if (isHindi) "संदेश भेजें (Send a Message)" else "Send an Inquiry",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )
                        Text(
                            text = if (isHindi) "पूछताछ दर्ज करें, हम शीघ्र आपसे संपर्क करेंगे" else "We will respond promptly to your queries",
                            fontSize = 11.5.sp,
                            color = Slate600
                        )
                    }
                }

                if (isSubmitted) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = EmeraldLight,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("contact_thank_you_banner")
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Success",
                                tint = EmeraldSuccess,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (isHindi) "धन्यवाद! संदेश प्राप्त हुआ।" else "Thank you! Message Received.",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isHindi)
                                    "आपका संदेश विद्यालय प्रशासन (श्री कामलेश कुमार) को प्राप्त हो गया है।"
                                else
                                    "Your message has been received by school administration.",
                                fontSize = 12.sp,
                                color = Slate700
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            OutlinedButton(
                                onClick = {
                                    userName = ""
                                    phoneNumber = ""
                                    queryMessage = ""
                                    isSubmitted = false
                                },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(if (isHindi) "अन्य संदेश भेजें" else "Send Another", fontSize = 12.sp)
                            }
                        }
                    }
                } else {
                    OutlinedTextField(
                        value = userName,
                        onValueChange = {
                            userName = it
                            validationError = null
                        },
                        label = { Text(if (isHindi) "आपका नाम (User Name) *" else "Your Name *") },
                        placeholder = { Text(if (isHindi) "उदा. रमेश कुमार" else "e.g. Ramesh Kumar") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = RoyalBlue) },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("contact_user_name_input")
                    )

                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { input ->
                            if (input.length <= 10) phoneNumber = input.filter { it.isDigit() }
                            validationError = null
                        },
                        label = { Text(if (isHindi) "फोन नंबर (Phone Number) *" else "Phone Number *") },
                        placeholder = { Text(if (isHindi) "10 अंकों का मोबाइल नंबर" else "10-digit mobile number") },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = RoyalBlue) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("contact_phone_number_input")
                    )

                    OutlinedTextField(
                        value = queryMessage,
                        onValueChange = {
                            queryMessage = it
                            validationError = null
                        },
                        label = { Text(if (isHindi) "पूछताछ / संदेश (Query / Message) *" else "Query / Message *") },
                        leadingIcon = { Icon(Icons.Default.Message, contentDescription = null, tint = RoyalBlue) },
                        minLines = 3,
                        maxLines = 5,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("contact_message_input")
                    )

                    if (validationError != null) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = RubyLight,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = validationError ?: "",
                                color = RubyRed,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            when {
                                userName.trim().isEmpty() -> {
                                    validationError = if (isHindi) "कृपया अपना नाम दर्ज करें।" else "Please enter your name."
                                }
                                phoneNumber.trim().length < 10 -> {
                                    validationError = if (isHindi) "कृपया 10 अंकों का फोन नंबर दर्ज करें।" else "Please enter valid 10-digit number."
                                }
                                queryMessage.trim().isEmpty() -> {
                                    validationError = if (isHindi) "कृपया संदेश दर्ज करें।" else "Please enter query message."
                                }
                                else -> {
                                    validationError = null
                                    isSubmitted = true
                                    Toast.makeText(
                                        context,
                                        if (isHindi) "धन्यवाद! आपका संदेश सफलतापूर्वक भेज दिया गया है।" else "Thank you! Message submitted successfully.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp)
                            .testTag("contact_submit_button"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary)
                    ) {
                        Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isHindi) "संदेश भेजें (Submit)" else "Submit",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HelplineRow(
    displayNumber: String,
    label: String,
    onCall: () -> Unit,
    onWhatsApp: () -> Unit,
    isHindi: Boolean
) {
    Column {
        Text(
            text = label,
            fontSize = 11.5.sp,
            color = Slate600
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = displayNumber,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Slate900
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // Call Button
                Button(
                    onClick = onCall,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call",
                        modifier = Modifier.size(14.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = if (isHindi) "कॉल" else "Call", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                // WhatsApp Button
                Button(
                    onClick = onWhatsApp,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Message,
                        contentDescription = "WhatsApp",
                        modifier = Modifier.size(14.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "WhatsApp", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
