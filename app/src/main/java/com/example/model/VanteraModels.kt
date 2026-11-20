package com.example.model

import androidx.annotation.DrawableRes

enum class AppLanguage(
    val code: String,
    val nativeLabel: String,
    val shortCode: String,
    val isRtl: Boolean,
) {
    EN(code = "en", nativeLabel = "English", shortCode = "EN", isRtl = false),
    KU(code = "ku", nativeLabel = "کوردی", shortCode = "کوردی", isRtl = true),
    AR(code = "ar", nativeLabel = "العربية", shortCode = "عربي", isRtl = true),
}

enum class NavSection(val listIndex: Int) {
    HOME(0),
    ABOUT(1),
    SERVICES(2),
    PROJECTS(3),
    CONTACT(7),
}

enum class DivisionId {
    REAL_ESTATE,
    CONSTRUCTION,
    GENERAL_TRADING,
}

enum class ServiceInterest {
    REAL_ESTATE,
    CONSTRUCTION,
    GENERAL_TRADING,
    PARTNERSHIP,
    GENERAL_INQUIRY,
}

data class DivisionItem(
    val id: DivisionId,
    val numberLabel: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val focusAreas: List<String>,
    val serviceInterest: ServiceInterest,
    @DrawableRes val imageRes: Int,
)

data class ProjectConcept(
    val id: String,
    val code: String,
    val title: String,
    val divisionLabel: String,
    val conceptTag: String,
    val summary: String,
    val architecturalNotes: String,
    @DrawableRes val imageRes: Int,
)

data class WhyChoosePillar(
    val indexLabel: String,
    val title: String,
    val description: String,
)

data class ContactFormState(
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val companyName: String = "",
    val serviceInterest: ServiceInterest = ServiceInterest.REAL_ESTATE,
    val message: String = "",
)

data class ContactFormErrors(
    val fullNameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    val messageError: String? = null,
) {
    val hasErrors: Boolean
        get() = fullNameError != null || emailError != null || phoneError != null || messageError != null
}

data class SubmittedInquiry(
    val fullName: String,
    val email: String,
    val phone: String,
    val companyName: String,
    val serviceLabel: String,
    val message: String,
)
