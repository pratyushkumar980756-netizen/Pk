package com.example.model

import androidx.annotation.DrawableRes
import com.example.R

data class SchoolInfo(
    val hindiName: String = "जनता इंटर कॉलेज मुबारकपुर कतरौली फूलपुर प्रयागराज",
    val englishName: String = "Janta Inter College Mubarakpur Katrauli Phulpur Prayagraj",
    val taglineHindi: String = "गुणवत्तापूर्ण शिक्षा और उज्ज्वल भविष्य",
    val taglineEnglish: String = "Quality Education & Bright Future",
    val schoolCode: String = "551642",
    val subHeadingHindi: String = "मुबारकपुर कतरौली, फूलपुर, प्रयागराज (विद्यालय कोड: 551642)",
    val subHeadingEnglish: String = "Mubarakpur Katrauli, Phulpur, Prayagraj (School Code: 551642)",
    val establishedYear: String = "1998",
    val managementHindi: String = "निजी गैर-सहायता प्राप्त (ग्रामीण क्षेत्र)",
    val managementEnglish: String = "Pvt. Unaided (Rural Area)",
    val headTeacherHindi: String = "श्री कामलेश कुमार",
    val headTeacherEnglish: String = "Kamlesh Kumar",
    val totalTeachers: Int = 7,
    val phone1: String = "+916393731223",
    val phone1Display: String = "+91 6393731223",
    val phone2: String = "9005520694",
    val phone2Display: String = "9005520694",
    val addressHindi: String = "मुबारकपुर कतरौली, फूलपुर, प्रयागराज, उत्तर प्रदेश (विद्यालय कोड: 551642)",
    val addressEnglish: String = "Mubarakpur Katrauli, Phulpur, Prayagraj, Uttar Pradesh (School Code: 551642)",
    val boardStructureHindi: String = "कक्षा 1 से 8: CBSE बोर्ड • कक्षा 9 से 12: UP बोर्ड (स्टेट बोर्ड)",
    val boardStructureEnglish: String = "Class 1st to 8th: CBSE Board • Class 9th to 12th: UP Board"
)

data class Facility(
    val id: String,
    val titleHindi: String,
    val titleEnglish: String,
    val descHindi: String,
    val descEnglish: String,
    val iconName: String,
    val badge: String = "उपलब्ध"
)

data class FeeTier(
    val gradesHindi: String,
    val gradesEnglish: String,
    val boardHindi: String,
    val boardEnglish: String,
    val amount: String,
    val frequencyHindi: String,
    val frequencyEnglish: String,
    val features: List<String>
)

data class GalleryPhoto(
    val id: String,
    @DrawableRes val resId: Int,
    val titleHindi: String,
    val titleEnglish: String,
    val descriptionHindi: String,
    val descriptionEnglish: String
)

data class SchoolNotice(
    val id: String,
    val date: String,
    val titleHindi: String,
    val titleEnglish: String,
    val descHindi: String,
    val descEnglish: String,
    val isUrgent: Boolean = false
)

data class AcademicStage(
    val id: String,
    val grades: String,
    val levelHindi: String,
    val levelEnglish: String,
    val descriptionHindi: String,
    val descriptionEnglish: String,
    val subjects: List<String>
)

data class SchoolContact(
    val titleHindi: String,
    val titleEnglish: String,
    val phoneDisplay: String,
    val cleanPhone: String,
    val roleHindi: String,
    val roleEnglish: String
)

object SchoolDataSource {
    val schoolInfo = SchoolInfo()

    val feeStructure = listOf(
        FeeTier(
            gradesHindi = "कक्षा 1 से 8 (Class 1st to 8th)",
            gradesEnglish = "Class 1st to 8th (Primary & Middle)",
            boardHindi = "सीबीएसई बोर्ड (CBSE Board)",
            boardEnglish = "CBSE Board Curriculum",
            amount = "₹300",
            frequencyHindi = "प्रति माह (Per Month)",
            frequencyEnglish = "Per Month",
            features = listOf(
                "सीबीएसई पैटर्न पर आधारित आधुनिक शिक्षण",
                "अंग्रेजी, गणित, विज्ञान एवं सामान्य ज्ञान",
                "खेलकूद एवं नैतिक संस्कार",
                "निशुल्क पुस्तकालय अध्ययन सुविधा"
            )
        ),
        FeeTier(
            gradesHindi = "कक्षा 9 से 12 (Class 9th to 12th)",
            gradesEnglish = "Class 9th to 12th (High School & Inter)",
            boardHindi = "यूपी बोर्ड (UP Board - State Board)",
            boardEnglish = "UP Board (State Board) Curriculum",
            amount = "₹300",
            frequencyHindi = "प्रति माह (Per Month)",
            frequencyEnglish = "Per Month",
            features = listOf(
                "यूपी माध्यमिक शिक्षा परिषद बोर्ड परीक्षा तैयारी",
                "कला एवं विज्ञान संकाय (भौतिकी, रसायन, जीव विज्ञान)",
                "विज्ञान प्रयोगशाला एवं प्रैक्टिकल शिक्षण",
                "छात्रवृत्ति (UP Scholarship) में पूर्ण सहायता"
            )
        )
    )

    val facilities = listOf(
        Facility(
            id = "fac_classrooms",
            titleHindi = "5 सुसज्जित कक्षाएं",
            titleEnglish = "5 Good Classrooms",
            descHindi = "छात्रों के बैठने हेतु हवादार एवं प्रकाशयुक्त पक्के कक्ष।",
            descEnglish = "Spacious, well-ventilated, and clean classrooms.",
            iconName = "class"
        ),
        Facility(
            id = "fac_labs",
            titleHindi = "विज्ञान एवं शिक्षण लैब",
            titleEnglish = "Science & Learning Labs",
            descHindi = "विज्ञान प्रयोगों एवं प्रायोगिक ज्ञान हेतु आवश्यक उपकरण।",
            descEnglish = "Hands-on learning labs for scientific discovery.",
            iconName = "science"
        ),
        Facility(
            id = "fac_power",
            titleHindi = "विद्युत आपूर्ति",
            titleEnglish = "Electricity",
            descHindi = "पंखों एवं प्रकाश की निरंतर विद्युत व्यवस्था।",
            descEnglish = "Reliable electric supply with fans and lighting.",
            iconName = "bolt"
        ),
        Facility(
            id = "fac_water",
            titleHindi = "शुद्ध पेयजल (हैंडपंप)",
            titleEnglish = "Functional Drinking Water (Hand Pumps)",
            descHindi = "स्वच्छ व शीतल भूमिगत जल आपूर्ति हेतु चालू हैंडपंप।",
            descEnglish = "Clean, safe, functional hand pump water supply.",
            iconName = "water_drop"
        ),
        Facility(
            id = "fac_toilets",
            titleHindi = "पृथक स्वच्छ शौचालय",
            titleEnglish = "Separate Clean Toilets",
            descHindi = "बालक एवं बालिकाओं के लिए अलग-अलग नियमित स्वच्छ शौचालय।",
            descEnglish = "Separate sanitized toilets for boys and girls.",
            iconName = "wc"
        ),
        Facility(
            id = "fac_boundary",
            titleHindi = "पक्की सुरक्षा चहारदीवारी",
            titleEnglish = "Pucca Boundary Wall",
            descHindi = "परिसर की सुरक्षा एवं अनुशासित माहौल हेतु पक्की बाउंड्री।",
            descEnglish = "Strong pucca brick wall securing school perimeter.",
            iconName = "fence"
        )
    )

    val galleryPhotos = listOf(
        GalleryPhoto(
            id = "photo_building",
            resId = R.drawable.school_front_building,
            titleHindi = "विद्यालय मुख्य भवन एवं प्रशासनिक परिसर",
            titleEnglish = "Main School Building & Campus Facade",
            descriptionHindi = "जनता इंटर कॉलेज मुबारकपुर कतरौली, फूलपुर, प्रयागराज (कोड: 551642)",
            descriptionEnglish = "School main facade with official banner and classroom wings"
        ),
        GalleryPhoto(
            id = "photo_flag",
            resId = R.drawable.school_flag_ceremony,
            titleHindi = "राष्ट्रीय ध्वजारोहण एवं खेल प्रांगण",
            titleEnglish = "National Flag Hoisting & School Ground",
            descriptionHindi = "राष्ट्रीय पर्व एवं दैनिक प्रार्थना सभा का विशाल प्रांगण",
            descriptionEnglish = "Flag post and assembly ground with students and teachers"
        ),
        GalleryPhoto(
            id = "photo_assembly",
            resId = R.drawable.school_assembly_students,
            titleHindi = "दैनिक प्रार्थना सभा एवं अनुशासन",
            titleEnglish = "Morning Prayer Assembly & Discipline",
            descriptionHindi = "विद्यार्थी गणवेश में प्रार्थना व संस्कार सत्र में भाग लेते हुए",
            descriptionEnglish = "Students assembled in uniform for moral education and prayers"
        )
    )

    val notices = listOf(
        SchoolNotice(
            id = "n1",
            date = "सत्र 2026-27",
            titleHindi = "प्रवेश प्रारंभ: कक्षा 1 से 12वीं तक",
            titleEnglish = "Admissions Open: Classes 1 to 12",
            descHindi = "कक्षा 1 से 8 (CBSE) एवं कक्षा 9 से 12 (UP Board) हेतु ऑनलाइन व ऑफलाइन फॉर्म उपलब्ध हैं।",
            descEnglish = "Admissions open for CBSE (1st-8th) and UP Board (9th-12th) streams.",
            isUrgent = true
        ),
        SchoolNotice(
            id = "n2",
            date = "किफायती शुल्क",
            titleHindi = "न्यूनतम शुल्क दर: मात्र ₹300 प्रति माह",
            titleEnglish = "Affordable Monthly Fee: ₹300 Only",
            descHindi = "कक्षा 1 से 8 (CBSE) और कक्षा 9 से 12 (UP Board) दोनों के लिए मात्र ₹300/माह शुल्क।",
            descEnglish = "Uniform affordable fee of ₹300 per month for both CBSE and UP Board wings.",
            isUrgent = false
        ),
        SchoolNotice(
            id = "n3",
            date = "संपर्क सूत्र",
            titleHindi = "सीधे संपर्क हेतु मोबाइल नंबर जारी",
            titleEnglish = "Helpline Numbers for Inquiries",
            descHindi = "प्रवेश एवं अन्य पूछताछ हेतु +91 6393731223 अथवा 9005520694 पर कॉल या व्हाट्सएप करें।",
            descEnglish = "Call or WhatsApp +91 6393731223 or 9005520694 for quick assistance.",
            isUrgent = false
        )
    )

    val schoolContacts = listOf(
        SchoolContact(
            titleHindi = "हेल्पलाइन 1 (प्रवेश एवं पूछताछ)",
            titleEnglish = "Helpline 1 (Admissions & Info)",
            phoneDisplay = "+91 6393731223",
            cleanPhone = "+916393731223",
            roleHindi = "प्रधानाचार्य एवं प्रशासन",
            roleEnglish = "Principal & Admin Desk"
        ),
        SchoolContact(
            titleHindi = "हेल्पलाइन 2 (प्रशासन एवं कार्यालय)",
            titleEnglish = "Helpline 2 (Office Administration)",
            phoneDisplay = "9005520694",
            cleanPhone = "+919005520694",
            roleHindi = "विद्यालय कार्यालय",
            roleEnglish = "School Office Coordinator"
        )
    )

    val academicStages = listOf(
        AcademicStage(
            id = "stage_primary",
            grades = "कक्षा 1 से 5",
            levelHindi = "प्राथमिक स्तर (CBSE पैटर्न)",
            levelEnglish = "Primary Wing (CBSE Pattern)",
            descriptionHindi = "मजबूत बुनियादी शिक्षा, हिंदी, अंग्रेजी, गणित, पर्यावरण एवं नैतिक संस्कार।",
            descriptionEnglish = "Foundational literacy, numeracy, environmental studies, and ethics.",
            subjects = listOf("हिंदी (Hindi)", "English", "गणित (Math)", "EVS / पर्यावरण", "सामान्य ज्ञान (GK)", "कला एवं संगीत")
        ),
        AcademicStage(
            id = "stage_middle",
            grades = "कक्षा 6 से 8",
            levelHindi = "उच्च प्राथमिक स्तर (CBSE पैटर्न)",
            levelEnglish = "Upper Primary / Middle Wing (CBSE)",
            descriptionHindi = "विज्ञान प्रयोगशाला, कंप्यूटर ज्ञान, संस्कृत एवं तार्किक विकास।",
            descriptionEnglish = "Hands-on science, mathematics, language mastery, and reasoning.",
            subjects = listOf("विज्ञान (Science)", "गणित (Math)", "सामाजिक विज्ञान (SST)", "English", "हिंदी", "संस्कृत")
        ),
        AcademicStage(
            id = "stage_highschool",
            grades = "कक्षा 9 व 10",
            levelHindi = "हाईस्कूल (यूपी बोर्ड)",
            levelEnglish = "High School (UP Board)",
            descriptionHindi = "यूपी माध्यमिक शिक्षा परिषद पाठ्यक्रम एवं बोर्ड परीक्षा की उत्कृष्ट तैयारी।",
            descriptionEnglish = "UP State Board curriculum with disciplined examination preparation.",
            subjects = listOf("हिंदी", "English", "गणित / गृह विज्ञान", "विज्ञान (प्रैक्टिकल सहित)", "सामाजिक विज्ञान", "चित्रकला")
        ),
        AcademicStage(
            id = "stage_inter",
            grades = "कक्षा 11 व 12",
            levelHindi = "इंटरमीडिएट (यूपी बोर्ड)",
            levelEnglish = "Intermediate (UP Board)",
            descriptionHindi = "विज्ञान एवं कला संकाय, सुसज्जित लैब में प्रैक्टिकल तथा कैरियर मार्गदर्शन।",
            descriptionEnglish = "Science & Humanities streams with laboratory practicals and career guidance.",
            subjects = listOf("भौतिक विज्ञान (Physics)", "रसायन विज्ञान (Chemistry)", "जीव विज्ञान (Biology)", "गणित (Maths)", "सामान्य हिंदी", "नागरिक शास्त्र / भूगोल")
        )
    )
}
