package com.example

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.example.model.AppLanguage
import com.example.model.ServiceInterest
import com.example.viewmodel.VanteraViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun `read app name string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("VANTERA", appName)
    }

    @Test
    fun `main activity renders vantera hero and wordmark without crashing`() {
        composeRule.onNodeWithTag("vantera_wordmark").assertIsDisplayed()
        composeRule.onNodeWithTag("section_hero").assertIsDisplayed()
        composeRule.onNodeWithText("Building Opportunities. Creating Tomorrow.").assertIsDisplayed()
        composeRule.onNodeWithTag("hero_explore_button").assertExists()
    }

    @Test
    fun `language switching in UI updates to Kurdish RTL and Arabic RTL`() {
        composeRule.onNodeWithTag("language_selector_button").performClick()
        composeRule.onNodeWithTag("lang_option_ku").performClick()
        composeRule.onNodeWithText("دەرفەتەکان بونیاد دەنێین. سبەینێ دەخولقێنین.").assertIsDisplayed()

        composeRule.onNodeWithTag("language_selector_button").performClick()
        composeRule.onNodeWithTag("lang_option_ar").performClick()
        composeRule.onNodeWithText("نبني الفرص. نصنع الغد.").assertIsDisplayed()
    }

    @Test
    fun `language switching updates RTL and localized strings`() {
        val vm = VanteraViewModel()
        assertEquals(AppLanguage.EN, vm.uiState.value.language)
        assertFalse(vm.uiState.value.language.isRtl)

        vm.setLanguage(AppLanguage.KU)
        assertEquals(AppLanguage.KU, vm.uiState.value.language)
        assertTrue(vm.uiState.value.language.isRtl)
        assertEquals(3, vm.uiState.value.copy.divisions.size)
        assertEquals(4, vm.uiState.value.copy.projects.size)

        vm.setLanguage(AppLanguage.AR)
        assertEquals(AppLanguage.AR, vm.uiState.value.language)
        assertTrue(vm.uiState.value.language.isRtl)
        assertEquals(4, vm.uiState.value.copy.whyPillars.size)
    }

    @Test
    fun `contact form validates invalid inputs and records demo inquiry when valid`() {
        val vm = VanteraViewModel()
        vm.submitContactForm()
        assertTrue(vm.uiState.value.formErrors.hasErrors)
        assertNull(vm.uiState.value.submittedInquiry)

        vm.onFullNameChanged("Karwan Barzani")
        vm.onEmailChanged("karwan@company.example")
        vm.onPhoneChanged("+964 750 123 4567")
        vm.onCompanyNameChanged("Erbil Logistics")
        vm.onServiceInterestChanged(ServiceInterest.GENERAL_TRADING)
        vm.onMessageChanged("Interested in exploring regional logistics coordination.")
        vm.submitContactForm()

        assertFalse(vm.uiState.value.formErrors.hasErrors)
        assertNotNull(vm.uiState.value.submittedInquiry)
        assertEquals("Karwan Barzani", vm.uiState.value.submittedInquiry?.fullName)
    }
}
