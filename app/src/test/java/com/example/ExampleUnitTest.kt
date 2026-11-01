package com.example

import com.example.model.SchoolDataSource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun verifySchoolInfoDetails() {
        val info = SchoolDataSource.schoolInfo
        assertEquals("551642", info.schoolCode)
        assertEquals("1998", info.establishedYear)
        assertEquals(7, info.totalTeachers)
        assertEquals("श्री कामलेश कुमार", info.headTeacherHindi)
        assertEquals("Kamlesh Kumar", info.headTeacherEnglish)
        assertTrue(info.hindiName.contains("कतरौली"))
        assertEquals("गुणवत्तापूर्ण शिक्षा और उज्ज्वल भविष्य", info.taglineHindi)
        assertTrue(info.managementEnglish.contains("Pvt. Unaided"))
    }

    @Test
    fun verifyFeeStructure() {
        val fees = SchoolDataSource.feeStructure
        assertEquals(2, fees.size)
        // Class 1st to 8th CBSE: ₹300
        val cbse = fees.find { it.gradesEnglish.contains("1st to 8th") }
        assertNotNull(cbse)
        assertEquals("₹300", cbse?.amount)
        assertTrue(cbse?.boardEnglish?.contains("CBSE") == true)

        // Class 9th to 12th UP Board: ₹300
        val upBoard = fees.find { it.gradesEnglish.contains("9th to 12th") }
        assertNotNull(upBoard)
        assertEquals("₹300", upBoard?.amount)
        assertTrue(upBoard?.boardEnglish?.contains("UP Board") == true || upBoard?.boardEnglish?.contains("UP State Board") == true)
    }

    @Test
    fun verifyHelplineNumbers() {
        val contacts = SchoolDataSource.schoolContacts
        assertTrue(contacts.any { it.cleanPhone == "+916393731223" })
        assertTrue(contacts.any { it.cleanPhone == "+919005520694" || it.cleanPhone.contains("9005520694") })
    }

    @Test
    fun verifySchoolFacilities() {
        val facilities = SchoolDataSource.facilities
        assertEquals(6, facilities.size)
        assertTrue(facilities.any { it.titleEnglish.contains("5 Good Classrooms") })
        assertTrue(facilities.any { it.titleEnglish.contains("Science & Learning Labs") })
        assertTrue(facilities.any { it.titleEnglish.contains("Electricity") })
        assertTrue(facilities.any { it.titleEnglish.contains("Drinking Water") })
        assertTrue(facilities.any { it.titleEnglish.contains("Separate Clean Toilets") })
        assertTrue(facilities.any { it.titleEnglish.contains("Pucca Boundary Wall") })
    }

    @Test
    fun verifySchoolPhotos() {
        val photos = SchoolDataSource.galleryPhotos
        assertEquals(3, photos.size)
        photos.forEach { photo ->
            assertTrue(photo.resId != 0)
            assertNotNull(photo.titleEnglish)
        }
    }
}
