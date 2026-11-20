package com.example.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.i18n.VanteraCopy
import com.example.model.ContactFormErrors
import com.example.model.ContactFormState
import com.example.model.DivisionItem
import com.example.model.NavSection
import com.example.model.ServiceInterest
import com.example.model.SubmittedInquiry
import com.example.ui.theme.ErrorCrimson
import com.example.ui.theme.Graphite
import com.example.ui.theme.GraphiteMuted
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.MidnightNavyDeep
import com.example.ui.theme.MidnightNavySoft
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SuccessForest
import com.example.ui.theme.SuccessTint
import com.example.ui.theme.Terracotta
import com.example.ui.theme.WarmStone
import com.example.ui.theme.WarmStoneBorder
import com.example.ui.theme.WarmStoneLight

@Composable
fun ContactSection(
    copy: VanteraCopy,
    formState: ContactFormState,
    formErrors: ContactFormErrors,
    submittedInquiry: SubmittedInquiry?,
    onFullNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onCompanyNameChanged: (String) -> Unit,
    onServiceInterestChanged: (ServiceInterest) -> Unit,
    onMessageChanged: (String) -> Unit,
    onSubmit: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(PureWhite)
            .padding(horizontal = 24.dp, vertical = 56.dp)
            .testTag("section_contact"),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 1160.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(28.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SectionEyebrow(text = copy.contactEyebrow)
                Text(
                    text = copy.contactHeadline,
                    style = MaterialTheme.typography.displayMedium,
                    color = MidnightNavy,
                )
                Text(
                    text = copy.contactSubtitle,
                    style = MaterialTheme.typography.bodyLarge,
                    color = GraphiteMuted,
                    modifier = Modifier.widthIn(max = 680.dp),
                )
            }

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val isSplit = maxWidth >= 820.dp
                if (isSplit) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        verticalAlignment = Alignment.Top,
                    ) {
                        ContactInfoPanel(
                            copy = copy,
                            modifier = Modifier.weight(0.42f),
                        )
                        ContactFormCard(
                            copy = copy,
                            formState = formState,
                            formErrors = formErrors,
                            submittedInquiry = submittedInquiry,
                            onFullNameChanged = onFullNameChanged,
                            onEmailChanged = onEmailChanged,
                            onPhoneChanged = onPhoneChanged,
                            onCompanyNameChanged = onCompanyNameChanged,
                            onServiceInterestChanged = onServiceInterestChanged,
                            onMessageChanged = onMessageChanged,
                            onSubmit = onSubmit,
                            onReset = onReset,
                            modifier = Modifier.weight(0.58f),
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                    ) {
                        ContactInfoPanel(
                            copy = copy,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        ContactFormCard(
                            copy = copy,
                            formState = formState,
                            formErrors = formErrors,
                            submittedInquiry = submittedInquiry,
                            onFullNameChanged = onFullNameChanged,
                            onEmailChanged = onEmailChanged,
                            onPhoneChanged = onPhoneChanged,
                            onCompanyNameChanged = onCompanyNameChanged,
                            onServiceInterestChanged = onServiceInterestChanged,
                            onMessageChanged = onMessageChanged,
                            onSubmit = onSubmit,
                            onReset = onReset,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactInfoPanel(
    copy: VanteraCopy,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MidnightNavy)
            .padding(28.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp),
    ) {
        VanteraWordmark(
            brandWordmark = copy.brandWordmark,
            brandSubword = copy.brandSubword,
            lightOnDark = true,
        )

        HorizontalDivider(color = PureWhite.copy(alpha = 0.14f))

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = copy.contactLocationTitle,
                tint = Terracotta,
                modifier = Modifier.size(22.dp),
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = copy.contactLocationTitle,
                    style = MaterialTheme.typography.labelMedium,
                    color = WarmStone.copy(alpha = 0.72f),
                )
                Text(
                    text = copy.contactLocationValue,
                    style = MaterialTheme.typography.titleMedium,
                    color = PureWhite,
                )
            }
        }

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = copy.contactEmailTitle,
                tint = Terracotta,
                modifier = Modifier.size(22.dp),
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = copy.contactEmailTitle,
                    style = MaterialTheme.typography.labelMedium,
                    color = WarmStone.copy(alpha = 0.72f),
                )
                Text(
                    text = copy.contactEmailValue,
                    style = MaterialTheme.typography.titleMedium,
                    color = PureWhite,
                )
                Text(
                    text = copy.contactEmailDemoNote,
                    style = MaterialTheme.typography.bodySmall,
                    color = Terracotta,
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(MidnightNavySoft)
                .border(BorderStroke(1.dp, Terracotta.copy(alpha = 0.45f)), RoundedCornerShape(4.dp))
                .padding(14.dp),
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = Terracotta,
                    modifier = Modifier.size(18.dp),
                )
                Text(
                    text = copy.contactDemoNotice,
                    style = MaterialTheme.typography.bodySmall,
                    color = WarmStone,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactFormCard(
    copy: VanteraCopy,
    formState: ContactFormState,
    formErrors: ContactFormErrors,
    submittedInquiry: SubmittedInquiry?,
    onFullNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onCompanyNameChanged: (String) -> Unit,
    onServiceInterestChanged: (ServiceInterest) -> Unit,
    onMessageChanged: (String) -> Unit,
    onSubmit: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(WarmStoneLight)
            .border(BorderStroke(1.dp, WarmStoneBorder), RoundedCornerShape(4.dp))
            .padding(24.dp),
    ) {
        AnimatedContent(
            targetState = submittedInquiry,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "contact_form_transition",
        ) { inquiry ->
            if (inquiry != null) {
                ContactSuccessPanel(
                    copy = copy,
                    inquiry = inquiry,
                    onReset = onReset,
                )
            } else {
                var dropdownExpanded by remember { mutableStateOf(false) }
                val fieldColors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = PureWhite,
                    unfocusedContainerColor = PureWhite,
                    errorContainerColor = PureWhite,
                    focusedBorderColor = MidnightNavy,
                    unfocusedBorderColor = WarmStoneBorder,
                    errorBorderColor = ErrorCrimson,
                    focusedLabelColor = MidnightNavy,
                    unfocusedLabelColor = GraphiteMuted,
                    cursorColor = Terracotta,
                )

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    // Full Name
                    OutlinedTextField(
                        value = formState.fullName,
                        onValueChange = onFullNameChanged,
                        label = { Text(copy.fieldFullName) },
                        singleLine = true,
                        isError = formErrors.fullNameError != null,
                        supportingText = formErrors.fullNameError?.let { err -> { Text(err) } },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        colors = fieldColors,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("contact_full_name_input"),
                    )

                    // Email + Phone row on wider cards
                    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                        val twoCol = maxWidth >= 520.dp
                        if (twoCol) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(14.dp),
                            ) {
                                OutlinedTextField(
                                    value = formState.email,
                                    onValueChange = onEmailChanged,
                                    label = { Text(copy.fieldEmail) },
                                    singleLine = true,
                                    isError = formErrors.emailError != null,
                                    supportingText = formErrors.emailError?.let { err -> { Text(err) } },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Email,
                                        imeAction = ImeAction.Next,
                                    ),
                                    colors = fieldColors,
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("contact_email_input"),
                                )
                                OutlinedTextField(
                                    value = formState.phone,
                                    onValueChange = onPhoneChanged,
                                    label = { Text(copy.fieldPhone) },
                                    singleLine = true,
                                    isError = formErrors.phoneError != null,
                                    supportingText = formErrors.phoneError?.let { err -> { Text(err) } },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Phone,
                                        imeAction = ImeAction.Next,
                                    ),
                                    colors = fieldColors,
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("contact_phone_input"),
                                )
                            }
                        } else {
                            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                OutlinedTextField(
                                    value = formState.email,
                                    onValueChange = onEmailChanged,
                                    label = { Text(copy.fieldEmail) },
                                    singleLine = true,
                                    isError = formErrors.emailError != null,
                                    supportingText = formErrors.emailError?.let { err -> { Text(err) } },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Email,
                                        imeAction = ImeAction.Next,
                                    ),
                                    colors = fieldColors,
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("contact_email_input"),
                                )
                                OutlinedTextField(
                                    value = formState.phone,
                                    onValueChange = onPhoneChanged,
                                    label = { Text(copy.fieldPhone) },
                                    singleLine = true,
                                    isError = formErrors.phoneError != null,
                                    supportingText = formErrors.phoneError?.let { err -> { Text(err) } },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Phone,
                                        imeAction = ImeAction.Next,
                                    ),
                                    colors = fieldColors,
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("contact_phone_input"),
                                )
                            }
                        }
                    }

                    // Company Name (Optional)
                    OutlinedTextField(
                        value = formState.companyName,
                        onValueChange = onCompanyNameChanged,
                        label = { Text("${copy.fieldCompany} (${copy.fieldCompanyOptional})") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        colors = fieldColors,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("contact_company_input"),
                    )

                    // Service of Interest Dropdown
                    ExposedDropdownMenuBox(
                        expanded = dropdownExpanded,
                        onExpandedChange = { dropdownExpanded = !dropdownExpanded },
                    ) {
                        OutlinedTextField(
                            value = copy.serviceOptions[formState.serviceInterest].orEmpty(),
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(copy.fieldServiceInterest) },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownExpanded)
                            },
                            colors = fieldColors,
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth()
                                .testTag("contact_service_dropdown"),
                        )
                        ExposedDropdownMenu(
                            expanded = dropdownExpanded,
                            onDismissRequest = { dropdownExpanded = false },
                            modifier = Modifier.background(PureWhite),
                        ) {
                            ServiceInterest.entries.forEach { interest ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = copy.serviceOptions[interest].orEmpty(),
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MidnightNavy,
                                        )
                                    },
                                    onClick = {
                                        onServiceInterestChanged(interest)
                                        dropdownExpanded = false
                                    },
                                    modifier = Modifier.testTag("service_option_${interest.name.lowercase()}"),
                                )
                            }
                        }
                    }

                    // Message
                    OutlinedTextField(
                        value = formState.message,
                        onValueChange = onMessageChanged,
                        label = { Text(copy.fieldMessage) },
                        minLines = 4,
                        maxLines = 6,
                        isError = formErrors.messageError != null,
                        supportingText = formErrors.messageError?.let { err -> { Text(err) } },
                        colors = fieldColors,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("contact_message_input"),
                    )

                    Button(
                        onClick = onSubmit,
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MidnightNavy,
                            contentColor = PureWhite,
                        ),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .minimumInteractiveComponentSize()
                            .testTag("contact_submit_button"),
                    ) {
                        Text(
                            text = copy.submitInquiryCta,
                            style = MaterialTheme.typography.labelLarge,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactSuccessPanel(
    copy: VanteraCopy,
    inquiry: SubmittedInquiry,
    onReset: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("contact_success_state"),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(SuccessTint)
                .border(BorderStroke(1.dp, SuccessForest.copy(alpha = 0.35f)), RoundedCornerShape(4.dp))
                .padding(16.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = null,
                tint = SuccessForest,
                modifier = Modifier.size(26.dp),
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = copy.successTitle,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = SuccessForest,
                )
                Text(
                    text = copy.successDemoExplanation,
                    style = MaterialTheme.typography.bodySmall,
                    color = Graphite,
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(PureWhite)
                .border(BorderStroke(1.dp, WarmStoneBorder), RoundedCornerShape(4.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = copy.successSummaryLabel,
                style = MaterialTheme.typography.labelMedium,
                color = Terracotta,
            )
            SummaryRow(label = copy.fieldFullName, value = inquiry.fullName)
            SummaryRow(label = copy.fieldEmail, value = inquiry.email)
            SummaryRow(label = copy.fieldPhone, value = inquiry.phone)
            if (inquiry.companyName.isNotBlank()) {
                SummaryRow(label = copy.fieldCompany, value = inquiry.companyName)
            }
            SummaryRow(label = copy.fieldServiceInterest, value = inquiry.serviceLabel)
            SummaryRow(label = copy.fieldMessage, value = inquiry.message)
        }

        OutlinedButton(
            onClick = onReset,
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, MidnightNavy),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MidnightNavy),
            modifier = Modifier
                .fillMaxWidth()
                .minimumInteractiveComponentSize()
                .testTag("contact_reset_button"),
        ) {
            Text(
                text = copy.successResetCta,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Composable
private fun SummaryRow(label: String, value: String) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = GraphiteMuted,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MidnightNavy,
        )
    }
}

@Composable
fun FooterSection(
    copy: VanteraCopy,
    onSelectSection: (NavSection) -> Unit,
    onSelectDivision: (DivisionItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MidnightNavyDeep)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = 24.dp, vertical = 44.dp)
            .testTag("section_footer"),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 1160.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(28.dp),
        ) {
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val multiColumn = maxWidth >= 760.dp
                if (multiColumn) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                    ) {
                        FooterBrandColumn(
                            copy = copy,
                            modifier = Modifier.weight(1.2f),
                        )
                        FooterNavigationColumn(
                            copy = copy,
                            onSelectSection = onSelectSection,
                            modifier = Modifier.weight(0.9f),
                        )
                        FooterDivisionsColumn(
                            copy = copy,
                            onSelectDivision = onSelectDivision,
                            modifier = Modifier.weight(1f),
                        )
                        FooterContactColumn(
                            copy = copy,
                            modifier = Modifier.weight(1.1f),
                        )
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                        FooterBrandColumn(copy = copy)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                        ) {
                            FooterNavigationColumn(
                                copy = copy,
                                onSelectSection = onSelectSection,
                                modifier = Modifier.weight(1f),
                            )
                            FooterDivisionsColumn(
                                copy = copy,
                                onSelectDivision = onSelectDivision,
                                modifier = Modifier.weight(1f),
                            )
                        }
                        FooterContactColumn(copy = copy)
                    }
                }
            }

            HorizontalDivider(color = PureWhite.copy(alpha = 0.14f))

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = copy.footerCopyright,
                    style = MaterialTheme.typography.bodySmall,
                    color = WarmStone.copy(alpha = 0.78f),
                )
                Text(
                    text = copy.footerDemoDisclosure,
                    style = MaterialTheme.typography.bodySmall,
                    color = WarmStone.copy(alpha = 0.65f),
                )
            }
        }
    }
}

@Composable
private fun FooterBrandColumn(
    copy: VanteraCopy,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        VanteraWordmark(
            brandWordmark = copy.brandWordmark,
            brandSubword = copy.brandSubword,
            lightOnDark = true,
        )
        Text(
            text = copy.footerTagline,
            style = MaterialTheme.typography.bodySmall,
            color = WarmStone.copy(alpha = 0.75f),
        )
    }
}

@Composable
private fun FooterNavigationColumn(
    copy: VanteraCopy,
    onSelectSection: (NavSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = copy.footerNavHeading,
            style = MaterialTheme.typography.labelMedium,
            color = Terracotta,
        )
        Spacer(modifier = Modifier.height(2.dp))
        listOf(
            NavSection.HOME to copy.navHome,
            NavSection.ABOUT to copy.navAbout,
            NavSection.SERVICES to copy.navServices,
            NavSection.PROJECTS to copy.navProjects,
            NavSection.CONTACT to copy.navContact,
        ).forEach { (section, label) ->
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = PureWhite.copy(alpha = 0.85f),
                modifier = Modifier
                    .clip(RoundedCornerShape(2.dp))
                    .clickable { onSelectSection(section) }
                    .padding(vertical = 4.dp),
            )
        }
    }
}

@Composable
private fun FooterDivisionsColumn(
    copy: VanteraCopy,
    onSelectDivision: (DivisionItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = copy.footerDivisionsHeading,
            style = MaterialTheme.typography.labelMedium,
            color = Terracotta,
        )
        Spacer(modifier = Modifier.height(2.dp))
        copy.divisions.forEach { division ->
            Text(
                text = division.title,
                style = MaterialTheme.typography.bodyMedium,
                color = PureWhite.copy(alpha = 0.85f),
                modifier = Modifier
                    .clip(RoundedCornerShape(2.dp))
                    .clickable { onSelectDivision(division) }
                    .padding(vertical = 4.dp),
            )
        }
    }
}

@Composable
private fun FooterContactColumn(
    copy: VanteraCopy,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = copy.footerContactHeading,
            style = MaterialTheme.typography.labelMedium,
            color = Terracotta,
        )
        Text(
            text = copy.contactLocationValue,
            style = MaterialTheme.typography.bodyMedium,
            color = PureWhite.copy(alpha = 0.88f),
        )
        Text(
            text = copy.contactEmailValue,
            style = MaterialTheme.typography.bodyMedium,
            color = PureWhite.copy(alpha = 0.88f),
        )
        Text(
            text = copy.contactEmailDemoNote,
            style = MaterialTheme.typography.bodySmall,
            color = Terracotta,
        )
    }
}
