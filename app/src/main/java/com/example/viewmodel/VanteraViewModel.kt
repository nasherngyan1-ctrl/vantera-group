package com.example.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.i18n.VanteraCopy
import com.example.i18n.VanteraStrings
import com.example.model.AppLanguage
import com.example.model.ContactFormErrors
import com.example.model.ContactFormState
import com.example.model.DivisionItem
import com.example.model.NavSection
import com.example.model.ProjectConcept
import com.example.model.ServiceInterest
import com.example.model.SubmittedInquiry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class VanteraUiState(
    val language: AppLanguage = AppLanguage.EN,
    val activeSection: NavSection = NavSection.HOME,
    val isMobileMenuOpen: Boolean = false,
    val selectedDivision: DivisionItem? = null,
    val selectedProject: ProjectConcept? = null,
    val contactForm: ContactFormState = ContactFormState(),
    val formErrors: ContactFormErrors = ContactFormErrors(),
    val hasAttemptedSubmit: Boolean = false,
    val submittedInquiry: SubmittedInquiry? = null,
) {
    val copy: VanteraCopy
        get() = VanteraStrings.forLanguage(language)
}

class VanteraViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VanteraUiState())
    val uiState: StateFlow<VanteraUiState> = _uiState.asStateFlow()

    fun setLanguage(language: AppLanguage) {
        _uiState.update { state ->
            val newCopy = VanteraStrings.forLanguage(language)
            val refreshedDivision = state.selectedDivision?.let { currentDiv ->
                newCopy.divisions.find { it.id == currentDiv.id }
            }
            val refreshedProject = state.selectedProject?.let { currentProj ->
                newCopy.projects.find { it.id == currentProj.id }
            }
            val updatedErrors = if (state.hasAttemptedSubmit) {
                validate(state.contactForm, newCopy)
            } else {
                ContactFormErrors()
            }
            state.copy(
                language = language,
                selectedDivision = refreshedDivision,
                selectedProject = refreshedProject,
                formErrors = updatedErrors,
            )
        }
    }

    fun setActiveSection(section: NavSection) {
        _uiState.update { it.copy(activeSection = section) }
    }

    fun setMobileMenuOpen(open: Boolean) {
        _uiState.update { it.copy(isMobileMenuOpen = open) }
    }

    fun openDivision(division: DivisionItem) {
        _uiState.update { it.copy(selectedDivision = division) }
    }

    fun closeDivision() {
        _uiState.update { it.copy(selectedDivision = null) }
    }

    fun openProject(project: ProjectConcept) {
        _uiState.update { it.copy(selectedProject = project) }
    }

    fun closeProject() {
        _uiState.update { it.copy(selectedProject = null) }
    }

    fun preselectServiceAndCloseModals(interest: ServiceInterest) {
        _uiState.update { state ->
            state.copy(
                selectedDivision = null,
                selectedProject = null,
                isMobileMenuOpen = false,
                submittedInquiry = null,
                contactForm = state.contactForm.copy(serviceInterest = interest),
            )
        }
    }

    fun onFullNameChanged(value: String) = updateForm { it.copy(fullName = value) }
    fun onEmailChanged(value: String) = updateForm { it.copy(email = value) }
    fun onPhoneChanged(value: String) = updateForm { it.copy(phone = value) }
    fun onCompanyNameChanged(value: String) = updateForm { it.copy(companyName = value) }
    fun onServiceInterestChanged(value: ServiceInterest) = updateForm { it.copy(serviceInterest = value) }
    fun onMessageChanged(value: String) = updateForm { it.copy(message = value) }

    private fun updateForm(transform: (ContactFormState) -> ContactFormState) {
        _uiState.update { state ->
            val updatedForm = transform(state.contactForm)
            val errors = if (state.hasAttemptedSubmit) {
                validate(updatedForm, state.copy)
            } else {
                state.formErrors
            }
            state.copy(contactForm = updatedForm, formErrors = errors)
        }
    }

    fun submitContactForm() {
        _uiState.update { state ->
            val copy = state.copy
            val errors = validate(state.contactForm, copy)
            if (errors.hasErrors) {
                state.copy(
                    hasAttemptedSubmit = true,
                    formErrors = errors,
                )
            } else {
                val form = state.contactForm
                val serviceLabel = copy.serviceOptions[form.serviceInterest].orEmpty()
                state.copy(
                    hasAttemptedSubmit = false,
                    formErrors = ContactFormErrors(),
                    submittedInquiry = SubmittedInquiry(
                        fullName = form.fullName.trim(),
                        email = form.email.trim(),
                        phone = form.phone.trim(),
                        companyName = form.companyName.trim(),
                        serviceLabel = serviceLabel,
                        message = form.message.trim(),
                    ),
                )
            }
        }
    }

    fun resetContactForm() {
        _uiState.update { state ->
            state.copy(
                contactForm = ContactFormState(serviceInterest = state.contactForm.serviceInterest),
                formErrors = ContactFormErrors(),
                hasAttemptedSubmit = false,
                submittedInquiry = null,
            )
        }
    }

    private fun validate(form: ContactFormState, copy: VanteraCopy): ContactFormErrors {
        val nameTrimmed = form.fullName.trim()
        val emailTrimmed = form.email.trim()
        val phoneTrimmed = form.phone.trim()
        val messageTrimmed = form.message.trim()

        val fullNameError = if (nameTrimmed.length < 2) copy.errorFullNameRequired else null

        val emailError = when {
            emailTrimmed.isEmpty() -> copy.errorEmailRequired
            !isValidEmail(emailTrimmed) -> copy.errorEmailInvalid
            else -> null
        }

        val digitCount = phoneTrimmed.count { it.isDigit() }
        val phoneError = when {
            phoneTrimmed.isEmpty() -> copy.errorPhoneRequired
            digitCount < 7 -> copy.errorPhoneInvalid
            else -> null
        }

        val messageError = if (messageTrimmed.length < 10) copy.errorMessageRequired else null

        return ContactFormErrors(
            fullNameError = fullNameError,
            emailError = emailError,
            phoneError = phoneError,
            messageError = messageError,
        )
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS?.matcher(email)?.matches()
            ?: Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$").matches(email)
    }
}
