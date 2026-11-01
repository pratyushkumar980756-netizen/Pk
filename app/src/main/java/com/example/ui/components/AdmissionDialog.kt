package com.example.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.RoyalBlue
import com.example.ui.theme.RoyalBlueContainer
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate900

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AdmissionDialog(
    isHindi: Boolean,
    onDismiss: () -> Unit,
    onCallSchool: () -> Unit
) {
    var studentName by remember { mutableStateOf("") }
    var selectedClass by remember { mutableStateOf("कक्षा 9 (9th)") }
    var parentName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var villageAddress by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val classList = listOf(
        "कक्षा 1 (1st)", "कक्षा 2 (2nd)", "कक्षा 3 (3rd)", "कक्षा 4 (4th)", "कक्षा 5 (5th)",
        "कक्षा 6 (6th)", "कक्षा 7 (7th)", "कक्षा 8 (8th)", "कक्षा 9 (9th)", "कक्षा 10 (10th)",
        "कक्षा 11 (11th)", "कक्षा 12 (12th)"
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 16.dp)
                .testTag("admission_dialog_card"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Dialog Header
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
                                    imageVector = Icons.Default.EditCalendar,
                                    contentDescription = null,
                                    tint = RoyalBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = if (isHindi) "प्रवेश पूछताछ फॉर्म" else "Admission Inquiry",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Slate900
                            )
                            Text(
                                text = if (isHindi) "सत्र 2026-27 • कक्षा 1 से 12वीं" else "Session 2026-27 • Class 1 to 12",
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

                if (isSubmitted) {
                    // Success View
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = EmeraldLight,
                            modifier = Modifier.size(64.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = EmeraldSuccess,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = if (isHindi) "पूछताछ सफलतापूर्वक दर्ज!" else "Inquiry Submitted Successfully!",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Slate900
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = if (isHindi)
                                "धन्यवाद! छात्र $studentName का प्रवेश विवरण प्राप्त हो गया है। विद्यालय कार्यालय (प्रधानाचार्य: श्री कामलेश कुमार) शीघ्र आपसे संपर्क करेगा।"
                            else
                                "Thank you! Inquiry received for $studentName. School administration will reach out shortly.",
                            fontSize = 13.sp,
                            color = Slate700,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = RoyalBlueContainer,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = if (isHindi) "पूछताछ संदर्भ संख्या: JIC/2026/042" else "Reference No: JIC/2026/042",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = RoyalBlueDark
                                )
                                Text(
                                    text = if (isHindi) "चयनित कक्षा: $selectedClass" else "Selected Grade: $selectedClass",
                                    fontSize = 12.sp,
                                    color = Slate700
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = onCallSchool,
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = RoyalBlue)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Phone,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(if (isHindi) "कार्यालय कॉल करें" else "Call Office", fontSize = 12.sp)
                            }

                            OutlinedButton(
                                onClick = onDismiss,
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text(if (isHindi) "बंद करें" else "Close", fontSize = 12.sp)
                            }
                        }
                    }
                } else {
                    // Form View
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        // Student Name
                        OutlinedTextField(
                            value = studentName,
                            onValueChange = {
                                studentName = it
                                errorMessage = null
                            },
                            label = { Text(if (isHindi) "विद्यार्थी का नाम (Student Name)" else "Student Name") },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = RoyalBlue) },
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("inquiry_student_name_input")
                        )

                        // Class Selector
                        Text(
                            text = if (isHindi) "प्रवेश हेतु कक्षा चुनें (Select Class):" else "Select Target Class:",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Slate700
                        )

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            classList.take(8).forEach { cls ->
                                val isSelected = selectedClass == cls
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isSelected) RoyalBlue else Slate100,
                                    contentColor = if (isSelected) Color.White else Slate900,
                                    modifier = Modifier.clickable { selectedClass = cls }
                                ) {
                                    Text(
                                        text = cls.substringBefore(" "),
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                    )
                                }
                            }
                        }

                        // Parent Name
                        OutlinedTextField(
                            value = parentName,
                            onValueChange = { parentName = it },
                            label = { Text(if (isHindi) "अभिभावक का नाम (Parent/Guardian)" else "Parent/Guardian Name") },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Slate600) },
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Mobile Number
                        OutlinedTextField(
                            value = mobileNumber,
                            onValueChange = {
                                if (it.length <= 10) mobileNumber = it.filter { char -> char.isDigit() }
                                errorMessage = null
                            },
                            label = { Text(if (isHindi) "मोबाइल नंबर (10 अंक)" else "Mobile Number (10 digits)") },
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = RoyalBlue) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("inquiry_phone_input")
                        )

                        // Village Address
                        OutlinedTextField(
                            value = villageAddress,
                            onValueChange = { villageAddress = it },
                            label = { Text(if (isHindi) "गांव / पता (Village/Address)" else "Village / Address") },
                            leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = Slate600) },
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (errorMessage != null) {
                            Text(
                                text = errorMessage ?: "",
                                color = Color.Red,
                                fontSize = 11.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Button(
                            onClick = {
                                if (studentName.trim().isEmpty()) {
                                    errorMessage = if (isHindi) "कृपया विद्यार्थी का नाम दर्ज करें" else "Please enter student name"
                                } else if (mobileNumber.trim().length < 10) {
                                    errorMessage = if (isHindi) "कृपया मान्य 10 अंकों का मोबाइल नंबर दर्ज करें" else "Please enter valid 10-digit mobile number"
                                } else {
                                    isSubmitted = true
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("inquiry_submit_button"),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary)
                        ) {
                            Text(
                                text = if (isHindi) "पूछताछ जमा करें (Submit Inquiry)" else "Submit Inquiry",
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
}
