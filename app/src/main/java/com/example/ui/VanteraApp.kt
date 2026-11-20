package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.NavSection
import com.example.model.ServiceInterest
import com.example.ui.components.AboutSection
import com.example.ui.components.ContactSection
import com.example.ui.components.DivisionDetailDialog
import com.example.ui.components.DivisionsSection
import com.example.ui.components.ExpertiseSection
import com.example.ui.components.FeaturedProjectsSection
import com.example.ui.components.FooterSection
import com.example.ui.components.HeroSection
import com.example.ui.components.PartnershipsSection
import com.example.ui.components.ProjectConceptDialog
import com.example.ui.components.VanteraHeader
import com.example.ui.components.WhyChooseSection
import com.example.ui.theme.PureWhite
import com.example.ui.theme.VanteraTheme
import com.example.viewmodel.VanteraViewModel
import kotlinx.coroutines.launch

@Composable
fun VanteraApp(
    viewModel: VanteraViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val copy = uiState.copy
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val currentSection by remember {
        derivedStateOf {
            when (listState.firstVisibleItemIndex) {
                0 -> NavSection.HOME
                1 -> NavSection.ABOUT
                2 -> NavSection.SERVICES
                3, 4, 5, 6 -> NavSection.PROJECTS
                else -> NavSection.CONTACT
            }
        }
    }

    LaunchedEffect(currentSection) {
        viewModel.setActiveSection(currentSection)
    }

    fun scrollToSection(section: NavSection) {
        viewModel.setMobileMenuOpen(false)
        viewModel.setActiveSection(section)
        coroutineScope.launch {
            listState.animateScrollToItem(section.listIndex)
        }
    }

    BackHandler(enabled = uiState.isMobileMenuOpen) {
        viewModel.setMobileMenuOpen(false)
    }

    VanteraTheme(language = uiState.language) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(PureWhite),
            containerColor = PureWhite,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
        ) { _ ->
            Column(modifier = Modifier.fillMaxSize()) {
                VanteraHeader(
                    copy = copy,
                    currentLanguage = uiState.language,
                    activeSection = uiState.activeSection,
                    isMobileMenuOpen = uiState.isMobileMenuOpen,
                    onSelectSection = ::scrollToSection,
                    onSelectLanguage = viewModel::setLanguage,
                    onToggleMobileMenu = viewModel::setMobileMenuOpen,
                )

                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    item(key = "hero") {
                        HeroSection(
                            copy = copy,
                            onExploreBusiness = { scrollToSection(NavSection.SERVICES) },
                            onContactTeam = { scrollToSection(NavSection.CONTACT) },
                        )
                    }
                    item(key = "about") {
                        AboutSection(copy = copy)
                    }
                    item(key = "divisions") {
                        DivisionsSection(
                            copy = copy,
                            onSelectDivision = viewModel::openDivision,
                        )
                    }
                    item(key = "projects") {
                        FeaturedProjectsSection(
                            copy = copy,
                            onSelectProject = viewModel::openProject,
                        )
                    }
                    item(key = "why_choose") {
                        WhyChooseSection(copy = copy)
                    }
                    item(key = "expertise") {
                        ExpertiseSection(copy = copy)
                    }
                    item(key = "partnerships") {
                        PartnershipsSection(
                            copy = copy,
                            onDiscussPartnership = {
                                viewModel.preselectServiceAndCloseModals(ServiceInterest.PARTNERSHIP)
                                scrollToSection(NavSection.CONTACT)
                            },
                        )
                    }
                    item(key = "contact") {
                        ContactSection(
                            copy = copy,
                            formState = uiState.contactForm,
                            formErrors = uiState.formErrors,
                            submittedInquiry = uiState.submittedInquiry,
                            onFullNameChanged = viewModel::onFullNameChanged,
                            onEmailChanged = viewModel::onEmailChanged,
                            onPhoneChanged = viewModel::onPhoneChanged,
                            onCompanyNameChanged = viewModel::onCompanyNameChanged,
                            onServiceInterestChanged = viewModel::onServiceInterestChanged,
                            onMessageChanged = viewModel::onMessageChanged,
                            onSubmit = viewModel::submitContactForm,
                            onReset = viewModel::resetContactForm,
                        )
                    }
                    item(key = "footer") {
                        FooterSection(
                            copy = copy,
                            onSelectSection = ::scrollToSection,
                            onSelectDivision = viewModel::openDivision,
                        )
                    }
                }
            }

            uiState.selectedDivision?.let { division ->
                DivisionDetailDialog(
                    copy = copy,
                    division = division,
                    onDismiss = viewModel::closeDivision,
                    onInquire = { serviceInterest ->
                        viewModel.preselectServiceAndCloseModals(serviceInterest)
                        scrollToSection(NavSection.CONTACT)
                    },
                )
            }

            uiState.selectedProject?.let { project ->
                ProjectConceptDialog(
                    copy = copy,
                    project = project,
                    onDismiss = viewModel::closeProject,
                )
            }
        }
    }
}
