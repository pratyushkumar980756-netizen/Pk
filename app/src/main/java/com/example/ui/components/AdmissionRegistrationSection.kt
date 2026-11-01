package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
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
import com.example.ui.theme.RubyLight
import com.example.ui.theme.RubyRed
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate900
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AdmissionRegistrationSection(
    isHindi: Boolean,
    onCallPrincipal: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Form fields
    var studentFullName by remember { mutableStateOf("") }
    var selectedClass by remember { mutableStateOf("कक्षा 9 (Class 9)") }
    var selectedBoard by remember { mutableStateOf("UP Board (यूपी बोर्ड)") }
    var fatherFullName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var fullAddress by remember { mutableStateOf("") }

    var validationError by remember { mutableStateOf<String?>(null) }
    var showSuccessModal by remember { mutableStateOf(false) }
    var submittedRefNumber by remember { mutableStateOf("") }

    val classOptions = listOf(
        "कक्षा 1 (Class 1)", "कक्षा 2 (Class 2)", "कक्षा 3 (Class 3)",
        "कक्षा 4 (Class 4)", "कक्षा 5 (Class 5)", "कक्षा 6 (Class 6)",
        "कक्षा 7 (Class 7)", "कक्षा 8 (Class 8)", "कक्षा 9 (Class 9)",
        "कक्षा 10 (Class 10)", "कक्षा 11 (Class 11)", "कक्षा 12 (Class 12)"
    )

    fun sendEmailNotification(student: String, grade: String, board: String, father: String, phone: String, addr: String, ref: String) {
        try {
            val recipientEmail = "pratyushkumar980756@gmail.com"
            val subject = "New Admission Registration: $student ($grade - $board) [Ref: $ref]"
            val emailBody = """
                जनता इंटर कॉलेज मुबारकपुर कतरौली फूलपुर प्रयागराज
                विद्यालय कोड: 551642
                -------------------------------------------------
                छात्र प्रवेश पंजीकरण विवरण (Student Admission Form):
                -------------------------------------------------
                संदर्भ संख्या (Reference ID): $ref
                छात्र का नाम (Student Name): $student
                कक्षा (Class Applying For): $grade
                बोर्ड प्रकार (Board Type): $board
                पिता का नाम (Father's Name): $father
                मोबाइल नंबर (Mobile Number): $phone
                पता (Address): $addr
                दिनांक एवं समय: ${SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())}
                -------------------------------------------------
                नोट: यह ईमेल जनता इंटर कॉलेज मोबाइल ऐप द्वारा स्वतः जनरेट किया गया है।
            """.trimIndent()

            val mailtoUri = Uri.parse("mailto:$recipientEmail?subject=${Uri.encode(subject)}&body=${Uri.encode(emailBody)}")
            val emailIntent = Intent(Intent.ACTION_SENDTO, mailtoUri)
            context.startActivity(emailIntent)
        } catch (e: Exception) {
            // fallback
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("admission_registration_section"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Section Title
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
                        imageVector = Icons.Default.AppRegistration,
                        contentDescription = null,
                        tint = RoyalBlue,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = if (isHindi) "छात्र प्रवेश पंजीकरण फॉर्म" else "Student Admission Registration Form",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Slate900
                )
                Text(
                    text = if (isHindi) "सीबीएसई (1-8) एवं यूपी बोर्ड (9-12) • सत्र 2026-27" else "CBSE (1st-8th) & UP Board (9th-12th) • Session 2026-27",
                    fontSize = 12.sp,
                    color = Slate600
                )
            }
        }

        // Form Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("admission_form_card"),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Slate200)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Header Banner
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = RoyalBlueContainer,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = RoyalBlue,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = if (isHindi)
                                "कक्षा 1 से 8 (CBSE) एवं कक्षा 9 से 12 (UP Board) में प्रवेश हेतु ऑनलाइन फॉर्म भरें। शुल्क: मात्र ₹300/माह।"
                            else
                                "Online registration for CBSE (Class 1-8) and UP Board (Class 9-12). Fee: ₹300/month only.",
                            fontSize = 12.sp,
                            color = RoyalBlueDark,
                            lineHeight = 16.sp
                        )
                    }
                }

                // 1. Student Name (छात्र का नाम)
                OutlinedTextField(
                    value = studentFullName,
                    onValueChange = {
                        studentFullName = it
                        validationError = null
                    },
                    label = { Text(if (isHindi) "छात्र का नाम (Student Full Name) *" else "Student Full Name *") },
                    placeholder = { Text(if (isHindi) "उदा. अंकित कुमार यादव" else "e.g. Ankit Kumar Yadav") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = RoyalBlue) },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = RoyalBlue,
                        unfocusedBorderColor = Slate200
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("reg_student_name_input")
                )

                // 2. Class (कक्षा - 1 से 12)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = if (isHindi) "प्रवेश हेतु कक्षा (Class Applying For) *" else "Select Class (1 to 12) *",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Slate700
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        classOptions.forEach { cls ->
                            val isSelected = selectedClass == cls
                            val classNum = cls.filter { it.isDigit() }.toIntOrNull() ?: 1
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) RoyalBlue else Slate100,
                                contentColor = if (isSelected) Color.White else Slate900,
                                border = androidx.compose.foundation.BorderStroke(
                                    width = 1.dp,
                                    color = if (isSelected) RoyalBlue else Slate200
                                ),
                                modifier = Modifier
                                    .clickable {
                                        selectedClass = cls
                                        // Auto-recommend board
                                        selectedBoard = if (classNum <= 8) "CBSE Board (सीबीएसई)" else "UP Board (यूपी बोर्ड)"
                                    }
                                    .testTag("reg_class_pill_$classNum")
                            ) {
                                Text(
                                    text = cls.substringBefore(" ("),
                                    fontSize = 11.5.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }

                // 3. Board Type (बोर्ड प्रकार: CBSE Board / UP Board)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = if (isHindi) "बोर्ड का प्रकार (Board Type) *" else "Board Type (CBSE / UP Board) *",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Slate700
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val isCbse = selectedBoard.contains("CBSE")
                        val isUp = selectedBoard.contains("UP")

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isCbse) RoyalBlue else Slate100,
                            contentColor = if (isCbse) Color.White else Slate900,
                            border = androidx.compose.foundation.BorderStroke(1.dp, if (isCbse) RoyalBlue else Slate200),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedBoard = "CBSE Board (सीबीएसई बोर्ड)" }
                                .testTag("reg_board_cbse")
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = if (isHindi) "CBSE बोर्ड" else "CBSE Board",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = if (isHindi) "कक्षा 1 से 8 हेतु" else "Class 1st to 8th",
                                    fontSize = 10.5.sp,
                                    color = if (isCbse) GoldLight else Slate600
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isUp) RoyalBlue else Slate100,
                            contentColor = if (isUp) Color.White else Slate900,
                            border = androidx.compose.foundation.BorderStroke(1.dp, if (isUp) RoyalBlue else Slate200),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedBoard = "UP Board (यूपी बोर्ड)" }
                                .testTag("reg_board_up")
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = if (isHindi) "UP बोर्ड (स्टेट बोर्ड)" else "UP State Board",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = if (isHindi) "कक्षा 9 से 12 हेतु" else "Class 9th to 12th",
                                    fontSize = 10.5.sp,
                                    color = if (isUp) GoldLight else Slate600
                                )
                            }
                        }
                    }
                }

                // 4. Father's Name (पिता का नाम)
                OutlinedTextField(
                    value = fatherFullName,
                    onValueChange = {
                        fatherFullName = it
                        validationError = null
                    },
                    label = { Text(if (isHindi) "पिता का नाम (Father's Name) *" else "Father's Name *") },
                    placeholder = { Text(if (isHindi) "उदा. श्री राम प्रसाद यादव" else "e.g. Shri Ram Prasad") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Slate600) },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = RoyalBlue,
                        unfocusedBorderColor = Slate200
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("reg_father_name_input")
                )

                // 5. Mobile Number (मोबाइल नंबर)
                OutlinedTextField(
                    value = mobileNumber,
                    onValueChange = { input ->
                        if (input.length <= 10) mobileNumber = input.filter { it.isDigit() }
                        validationError = null
                    },
                    label = { Text(if (isHindi) "मोबाइल नंबर (Mobile Number) *" else "Mobile Number (10 digits) *") },
                    placeholder = { Text(if (isHindi) "10 अंकों का सक्रिय नंबर" else "10-digit mobile number") },
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = RoyalBlue) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = RoyalBlue,
                        unfocusedBorderColor = Slate200
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("reg_phone_number_input")
                )

                // 6. Address (पता)
                OutlinedTextField(
                    value = fullAddress,
                    onValueChange = {
                        fullAddress = it
                        validationError = null
                    },
                    label = { Text(if (isHindi) "पता (Address - ग्राम, पोस्ट, ब्लॉक) *" else "Address (Village, Post, Block) *") },
                    placeholder = { Text(if (isHindi) "उदा. ग्राम मुबारकपुर कतरौली, फूलपुर, प्रयागराज" else "e.g. Mubarakpur Katrauli, Phulpur, Prayagraj") },
                    leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = Slate600) },
                    singleLine = false,
                    maxLines = 3,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = RoyalBlue,
                        unfocusedBorderColor = Slate200
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("reg_address_input")
                )

                if (validationError != null) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = RubyLight,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = validationError ?: "",
                            color = RubyRed,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }

                // Submit Button
                Button(
                    onClick = {
                        when {
                            studentFullName.trim().isEmpty() -> {
                                validationError = if (isHindi) "कृपया छात्र का नाम दर्ज करें।" else "Please enter student's name."
                            }
                            fatherFullName.trim().isEmpty() -> {
                                validationError = if (isHindi) "कृपया पिता का नाम दर्ज करें।" else "Please enter father's name."
                            }
                            mobileNumber.trim().length < 10 -> {
                                validationError = if (isHindi) "कृपया मान्य 10 अंकों का मोबाइल नंबर दर्ज करें।" else "Please enter a valid 10-digit mobile number."
                            }
                            fullAddress.trim().isEmpty() -> {
                                validationError = if (isHindi) "कृपया पूरा निवास पता दर्ज करें।" else "Please enter complete residential address."
                            }
                            else -> {
                                validationError = null
                                val randomId = (1000..9999).random()
                                val refId = "JIC-551642-$randomId"
                                submittedRefNumber = refId

                                // Dispatch email notification to school email
                                sendEmailNotification(
                                    student = studentFullName.trim(),
                                    grade = selectedClass,
                                    board = selectedBoard,
                                    father = fatherFullName.trim(),
                                    phone = mobileNumber.trim(),
                                    addr = fullAddress.trim(),
                                    ref = refId
                                )

                                // Trigger success modal
                                showSuccessModal = true

                                Toast.makeText(
                                    context,
                                    if (isHindi) "आपका रजिस्ट्रेशन सफलतापूर्वक हो गया है!" else "Registration submitted successfully!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("reg_submit_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary)
                ) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isHindi) "रजिस्ट्रेशन फॉर्म जमा करें (Submit Form)" else "Submit Registration Form",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }

    // Success Pop-Up Message Dialog: "आपका रजिस्ट्रेशन सफलतापूर्वक हो गया है!"
    if (showSuccessModal) {
        Dialog(onDismissRequest = { showSuccessModal = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .testTag("registration_success_dialog"),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = CircleShape,
                        color = EmeraldLight,
                        modifier = Modifier.size(70.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Success",
                                tint = EmeraldSuccess,
                                modifier = Modifier.size(46.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = if (isHindi) "आपका रजिस्ट्रेशन सफलतापूर्वक हो गया है!" else "Registration Submitted Successfully!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Slate900,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (isHindi)
                            "धन्यवाद! छात्र $studentFullName ($selectedBoard) का विवरण विद्यालय कार्यालय (कोड: 551642) को सफलतापूर्वक प्राप्त हो गया है।"
                        else
                            "Thank you! Registration details for $studentFullName have been forwarded to the school administration.",
                        fontSize = 12.5.sp,
                        color = Slate700,
                        textAlign = TextAlign.Center,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = RoyalBlueContainer,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = if (isHindi) "संदर्भ संख्या:" else "Ref ID:", fontSize = 11.5.sp, color = Slate600)
                                Text(text = submittedRefNumber, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = RoyalBlueDark)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = if (isHindi) "बोर्ड:" else "Board:", fontSize = 11.5.sp, color = Slate600)
                                Text(text = selectedBoard.substringBefore(" ("), fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Slate900)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = if (isHindi) "कक्षा:" else "Class:", fontSize = 11.5.sp, color = Slate600)
                                Text(text = selectedClass, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Slate900)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                showSuccessModal = false
                                onCallPrincipal()
                            },
                            modifier = Modifier.weight(1.2f),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue)
                        ) {
                            Icon(imageVector = Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = if (isHindi) "कार्यालय कॉल करें" else "Call Office", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = {
                                showSuccessModal = false
                                studentFullName = ""
                                fatherFullName = ""
                                mobileNumber = ""
                                fullAddress = ""
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(if (isHindi) "ठीक है (OK)" else "Close", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}
