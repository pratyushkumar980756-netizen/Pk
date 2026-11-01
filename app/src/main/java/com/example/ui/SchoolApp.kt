package com.example.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.GalleryPhoto
import com.example.model.SchoolDataSource
import com.example.ui.components.AboutSection
import com.example.ui.components.AcademicsSection
import com.example.ui.components.AdmissionDialog
import com.example.ui.components.AdmissionRegistrationSection
import com.example.ui.components.ContactSection
import com.example.ui.components.FacilitiesSection
import com.example.ui.components.FeeStructureDialog
import com.example.ui.components.FeeStructureSection
import com.example.ui.components.GalleryDialog
import com.example.ui.components.GallerySection
import com.example.ui.components.HeroCarousel
import com.example.ui.components.NewsTickerBar
import com.example.ui.components.QuickContactBar
import com.example.ui.components.QuickFabMenu
import com.example.ui.components.SchoolHeader
import com.example.ui.components.SyllabusDialog
import com.example.ui.components.UrgentAnnouncementsDialog
import com.example.ui.components.WhatsAppChannelCard
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
import com.example.ui.theme.Slate900
import kotlinx.coroutines.launch

enum class SchoolTab(val titleHindi: String, val titleEnglish: String) {
    ALL("सभी (All)", "All Sections"),
    ABOUT("परिचय", "About Us"),
    FEES("शुल्क ₹300", "Fee ₹300"),
    ADMISSION("प्रवेश फॉर्म", "Admissions"),
    FACILITIES("सुविधाएं", "Facilities"),
    ACADEMICS("शिक्षा", "Academics"),
    GALLERY("दीर्घा", "Gallery"),
    CONTACT("संपर्क", "Contact")
}

@Composable
fun SchoolApp(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    var isHindi by remember { mutableStateOf(true) }
    var selectedTab by remember { mutableStateOf(SchoolTab.ALL) }
    var searchQuery by remember { mutableStateOf("") }

    // Dialog & menu states
    var isFabMenuExpanded by remember { mutableStateOf(false) }
    var showAdmissionDialog by remember { mutableStateOf(false) }
    var showSyllabusDialog by remember { mutableStateOf(false) }
    var showFeeStructureDialog by remember { mutableStateOf(false) }
    var showUrgentAnnouncementsDialog by remember { mutableStateOf(false) }
    var selectedGalleryPhoto by remember { mutableStateOf<GalleryPhoto?>(null) }

    val schoolInfo = SchoolDataSource.schoolInfo
    val facilities = SchoolDataSource.facilities
    val galleryPhotos = SchoolDataSource.galleryPhotos
    val notices = SchoolDataSource.notices
    val academicStages = SchoolDataSource.academicStages

    // Helper intents
    fun callSchool(number: String = "+916393731223") {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$number")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // fallback
        }
    }

    fun openMap() {
        try {
            val gmmIntentUri = Uri.parse("geo:25.5534,82.0792?q=Mubarakpur+Katrauli+Phulpur+Prayagraj+Uttar+Pradesh")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            context.startActivity(mapIntent)
        } catch (e: Exception) {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=Mubarakpur+Katrauli+Phulpur+Prayagraj"))
            context.startActivity(webIntent)
        }
    }

    fun shareSchoolApp() {
        val shareText = """
            ${schoolInfo.hindiName} (${schoolInfo.englishName})
            ${schoolInfo.subHeadingHindi}
            
            विद्यालय कोड: ${schoolInfo.schoolCode} | स्थापना: ${schoolInfo.establishedYear}
            • कक्षा 1 से 8: सीबीएसई बोर्ड (CBSE) - ₹300/माह
            • कक्षा 9 से 12: यूपी बोर्ड (UP Board) - ₹300/माह
            प्रधानाचार्य: ${schoolInfo.headTeacherHindi}
            कुल शिक्षक: ${schoolInfo.totalTeachers}
            सुविधाएं: 5 सुसज्जित कक्षाएं, विज्ञान लैब, विद्युत, शुद्ध पेयजल (हैंडपंप), पृथक स्वच्छ शौचालय, पक्की सुरक्षा चहारदीवारी।
            
            हेल्पलाइन / व्हाट्सएप: +91 6393731223, 9005520694
            पता: ${schoolInfo.addressHindi}
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, if (isHindi) "विद्यालय विवरण साझा करें" else "Share School Details")
        context.startActivity(shareIntent)
    }

    fun joinWhatsAppChannel() {
        try {
            val channelUrl = "https://whatsapp.com/channel/0029VaJantaInterCollege551642"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(channelUrl))
            context.startActivity(intent)
        } catch (e: Exception) {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://whatsapp.com/channel/0029VaJantaInterCollege551642"))
            context.startActivity(webIntent)
        }
    }

    fun emailSchool() {
        try {
            val mailtoUri = Uri.parse("mailto:pratyushkumar980756@gmail.com?subject=${Uri.encode("Inquiry: जनता इंटर कॉलेज मुबारकपुर")}")
            val emailIntent = Intent(Intent.ACTION_SENDTO, mailtoUri)
            context.startActivity(emailIntent)
        } catch (e: Exception) {
            // fallback
        }
    }

    val showBackToTop by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 1 }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        topBar = {
            NewsTickerBar(
                isHindi = isHindi,
                onOpenAllNotices = { showUrgentAnnouncementsDialog = true }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Back to Top button
                AnimatedVisibility(visible = showBackToTop) {
                    FloatingActionButton(
                        onClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(0)
                            }
                        },
                        modifier = Modifier
                            .size(42.dp)
                            .testTag("scroll_to_top_button"),
                        shape = CircleShape,
                        containerColor = Slate900,
                        contentColor = Color.White,
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Scroll to top"
                        )
                    }
                }

                // Speed-Dial FAB Menu providing quick links: 'WhatsApp Channel', 'Call Principal', 'Download Syllabus', 'View Fee Structure'
                QuickFabMenu(
                    isExpanded = isFabMenuExpanded,
                    isHindi = isHindi,
                    onToggleExpand = { isFabMenuExpanded = !isFabMenuExpanded },
                    onCallPrincipal = {
                        isFabMenuExpanded = false
                        callSchool()
                    },
                    onDownloadSyllabus = {
                        isFabMenuExpanded = false
                        showSyllabusDialog = true
                    },
                    onViewFeeStructure = {
                        isFabMenuExpanded = false
                        showFeeStructureDialog = true
                    },
                    onJoinWhatsApp = {
                        isFabMenuExpanded = false
                        joinWhatsAppChannel()
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 680.dp)
                    .testTag("main_school_feed"),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Item 0: School Header & Branding
                item {
                    SchoolHeader(
                        schoolInfo = schoolInfo,
                        isHindi = isHindi,
                        onToggleLanguage = { isHindi = !isHindi }
                    )
                }

                // Item 1: Navigation Tabs & Search Row
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Horizontal Navigation Filter Chips
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            SchoolTab.values().forEach { tab ->
                                val isSelected = selectedTab == tab
                                Surface(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .clickable { selectedTab = tab }
                                        .testTag("nav_tab_${tab.name.lowercase()}"),
                                    shape = RoundedCornerShape(20.dp),
                                    color = if (isSelected) RoyalBlue else Color.White,
                                    border = androidx.compose.foundation.BorderStroke(
                                        width = 1.dp,
                                        color = if (isSelected) RoyalBlue else Slate200
                                    ),
                                    shadowElevation = if (isSelected) 2.dp else 0.5.dp
                                ) {
                                    Text(
                                        text = if (isHindi) tab.titleHindi else tab.titleEnglish,
                                        fontSize = 12.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else Slate700,
                                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
                                    )
                                }
                            }
                        }

                        // Search & Filter Box
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = {
                                Text(
                                    text = if (isHindi) "खोजें: सुविधाएं, कक्षाएं, शिक्षक, पाठ्यक्रम..." else "Search facilities, classes, faculty, syllabus...",
                                    fontSize = 12.5.sp,
                                    color = Slate600
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    tint = RoyalBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    IconButton(onClick = { searchQuery = "" }) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = "Clear search",
                                            tint = Slate600,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedBorderColor = RoyalBlue,
                                unfocusedBorderColor = Slate200
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("search_filter_input")
                        )
                    }
                }

                // Item 2: Hero Section
                if (selectedTab == SchoolTab.ALL || selectedTab == SchoolTab.ABOUT) {
                    item {
                        Box(modifier = Modifier.padding(horizontal = 14.dp)) {
                            HeroCarousel(
                                photos = galleryPhotos,
                                isHindi = isHindi,
                                onPhotoClick = { photo -> selectedGalleryPhoto = photo },
                                onOpenInquiry = { showAdmissionDialog = true },
                                onCallSchool = { callSchool() },
                                onOpenMap = { openMap() }
                            )
                        }
                    }
                }

                // Item 3: About Us Section
                if (selectedTab == SchoolTab.ALL || selectedTab == SchoolTab.ABOUT) {
                    item {
                        Box(modifier = Modifier.padding(horizontal = 14.dp)) {
                            AboutSection(
                                schoolInfo = schoolInfo,
                                isHindi = isHindi
                            )
                        }
                    }
                }

                // Item 3.5: WhatsApp Channel Promo Card
                if (selectedTab == SchoolTab.ALL || selectedTab == SchoolTab.ABOUT || selectedTab == SchoolTab.CONTACT) {
                    item {
                        Box(modifier = Modifier.padding(horizontal = 14.dp)) {
                            WhatsAppChannelCard(
                                isHindi = isHindi,
                                onJoinChannel = { joinWhatsAppChannel() }
                            )
                        }
                    }
                }

                // Item 3.6: Fee Structure Section (₹300/month flat for CBSE 1st-8th and UP Board 9th-12th)
                if (selectedTab == SchoolTab.ALL || selectedTab == SchoolTab.FEES || selectedTab == SchoolTab.ADMISSION) {
                    item {
                        Box(modifier = Modifier.padding(horizontal = 14.dp)) {
                            FeeStructureSection(
                                isHindi = isHindi,
                                onCallInquiry = { callSchool("+916393731223") }
                            )
                        }
                    }
                }

                // Item 4: Facilities Section
                if (selectedTab == SchoolTab.ALL || selectedTab == SchoolTab.FACILITIES) {
                    val filteredFacilities = if (searchQuery.isBlank()) {
                        facilities
                    } else {
                        facilities.filter {
                            it.titleHindi.contains(searchQuery, ignoreCase = true) ||
                            it.titleEnglish.contains(searchQuery, ignoreCase = true) ||
                            it.descHindi.contains(searchQuery, ignoreCase = true) ||
                            it.descEnglish.contains(searchQuery, ignoreCase = true)
                        }
                    }

                    item {
                        Box(modifier = Modifier.padding(horizontal = 14.dp)) {
                            FacilitiesSection(
                                facilities = filteredFacilities,
                                isHindi = isHindi
                            )
                        }
                    }
                }

                // Item 5: Academics & Circulars Section
                if (selectedTab == SchoolTab.ALL || selectedTab == SchoolTab.ACADEMICS) {
                    item {
                        Box(modifier = Modifier.padding(horizontal = 14.dp)) {
                            AcademicsSection(
                                stages = academicStages,
                                notices = notices,
                                isHindi = isHindi
                            )
                        }
                    }
                }

                // Item 5.5: Interactive Student Admission / Inquiry Registration Form
                if (selectedTab == SchoolTab.ALL || selectedTab == SchoolTab.ADMISSION || selectedTab == SchoolTab.ACADEMICS) {
                    item {
                        Box(modifier = Modifier.padding(horizontal = 14.dp)) {
                            AdmissionRegistrationSection(
                                isHindi = isHindi,
                                onCallPrincipal = { callSchool() }
                            )
                        }
                    }
                }

                // Item 6: Photo Gallery Section
                if (selectedTab == SchoolTab.ALL || selectedTab == SchoolTab.GALLERY) {
                    item {
                        Box(modifier = Modifier.padding(horizontal = 14.dp)) {
                            GallerySection(
                                photos = galleryPhotos,
                                isHindi = isHindi,
                                onPhotoClick = { photo -> selectedGalleryPhoto = photo }
                            )
                        }
                    }
                }

                // Item 7: Contact & Location Section
                if (selectedTab == SchoolTab.ALL || selectedTab == SchoolTab.CONTACT) {
                    item {
                        Box(modifier = Modifier.padding(horizontal = 14.dp)) {
                            ContactSection(
                                schoolInfo = schoolInfo,
                                isHindi = isHindi,
                                onCallSchool = { callSchool() },
                                onOpenMap = { openMap() },
                                onShareApp = { shareSchoolApp() }
                            )
                        }
                    }
                }

                // Item 7.5: Quick Contact Bar (Phone Call, Email, Location, Share)
                item {
                    Box(modifier = Modifier.padding(horizontal = 14.dp)) {
                        QuickContactBar(
                            isHindi = isHindi,
                            onCall = { callSchool() },
                            onEmail = { emailSchool() },
                            onLocation = { openMap() },
                            onShare = { shareSchoolApp() }
                        )
                    }
                }

                // Item 8: Footer
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(RoyalBlueDark)
                            .padding(vertical = 24.dp, horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (isHindi) schoolInfo.hindiName else schoolInfo.englishName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (isHindi) "विद्यालय कोड: 551642 • स्थापना: 1998 • CBSE (1st-8th) & UP Board (9th-12th)" else "School Code: 551642 • Est. 1998 • CBSE (1-8) & UP Board (9-12)",
                            fontSize = 11.sp,
                            color = GoldLight,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (isHindi)
                                "मुबारकपुर कतरौली, फूलपुर, प्रयागराज (उ.प्र.) के बच्चों के उज्ज्वल भविष्य हेतु समर्पित।"
                            else
                                "Dedicated to empowering students of Mubarakpur Katrauli, Phulpur, Prayagraj.",
                            fontSize = 11.sp,
                            color = Color.White.copy(alpha = 0.75f),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color.White.copy(alpha = 0.1f)
                        ) {
                            Text(
                                text = "© 2026 Janta Inter College Mubarakpur Katrauli. All rights reserved.",
                                fontSize = 10.sp,
                                color = Color.White.copy(alpha = 0.6f),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(60.dp)) // padding for FAB
                    }
                }
            }

            // Scrim to close FAB menu when tapping outside
            if (isFabMenuExpanded) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.35f))
                        .clickable { isFabMenuExpanded = false }
                )
            }
        }
    }

    // Quick Action Dialog: Download Syllabus
    if (showSyllabusDialog) {
        SyllabusDialog(
            isHindi = isHindi,
            onDismiss = { showSyllabusDialog = false }
        )
    }

    // Quick Action Dialog: View Fee Structure
    if (showFeeStructureDialog) {
        FeeStructureDialog(
            isHindi = isHindi,
            onDismiss = { showFeeStructureDialog = false },
            onCallPrincipal = {
                showFeeStructureDialog = false
                callSchool()
            }
        )
    }

    // Admission Inquiry Modal Dialog
    if (showAdmissionDialog) {
        AdmissionDialog(
            isHindi = isHindi,
            onDismiss = { showAdmissionDialog = false },
            onCallSchool = {
                showAdmissionDialog = false
                callSchool()
            }
        )
    }

    // Full-screen Gallery Photo Modal Dialog
    selectedGalleryPhoto?.let { photo ->
        GalleryDialog(
            photo = photo,
            isHindi = isHindi,
            onDismiss = { selectedGalleryPhoto = null }
        )
    }

    // Urgent Announcements Modal Dialog
    if (showUrgentAnnouncementsDialog) {
        UrgentAnnouncementsDialog(
            isHindi = isHindi,
            onDismiss = { showUrgentAnnouncementsDialog = false }
        )
    }
}
