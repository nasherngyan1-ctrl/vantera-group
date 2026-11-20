package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.i18n.VanteraCopy
import com.example.model.AppLanguage
import com.example.model.NavSection
import com.example.ui.theme.Graphite
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.MidnightNavyDeep
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Terracotta
import com.example.ui.theme.WarmStone
import com.example.ui.theme.WarmStoneBorder

@Composable
fun VanteraHeader(
    copy: VanteraCopy,
    currentLanguage: AppLanguage,
    activeSection: NavSection,
    isMobileMenuOpen: Boolean,
    onSelectSection: (NavSection) -> Unit,
    onSelectLanguage: (AppLanguage) -> Unit,
    onToggleMobileMenu: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = PureWhite.copy(alpha = 0.97f),
        shadowElevation = 2.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars),
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center,
            ) {
                val isWide = maxWidth >= 860.dp
                val isMedium = maxWidth >= 600.dp

                Row(
                    modifier = Modifier
                        .widthIn(max = 1200.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    // Pure typographic VANTERA wordmark (no invented logo symbol)
                    VanteraWordmark(
                        brandWordmark = copy.brandWordmark,
                        brandSubword = copy.brandSubword,
                        onClick = { onSelectSection(NavSection.HOME) },
                    )

                    if (isWide) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                        ) {
                            val navItems = listOf(
                                NavSection.HOME to copy.navHome,
                                NavSection.ABOUT to copy.navAbout,
                                NavSection.SERVICES to copy.navServices,
                                NavSection.PROJECTS to copy.navProjects,
                                NavSection.CONTACT to copy.navContact,
                            )
                            navItems.forEach { (section, label) ->
                                val selected = activeSection == section
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                    ),
                                    color = if (selected) Terracotta else MidnightNavy,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .clickable { onSelectSection(section) }
                                        .padding(horizontal = 8.dp, vertical = 10.dp)
                                        .testTag("nav_${section.name.lowercase()}"),
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            LanguageSelectorButton(
                                currentLanguage = currentLanguage,
                                contentDescription = copy.languageSelectorLabel,
                                onSelectLanguage = onSelectLanguage,
                            )
                            Button(
                                onClick = { onSelectSection(NavSection.CONTACT) },
                                shape = RoundedCornerShape(4.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Terracotta,
                                    contentColor = PureWhite,
                                ),
                                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 10.dp),
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("header_contact_cta"),
                            ) {
                                Text(
                                    text = copy.contactUsCta,
                                    style = MaterialTheme.typography.labelLarge,
                                    maxLines = 1,
                                )
                            }
                        }
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            LanguageSelectorButton(
                                currentLanguage = currentLanguage,
                                contentDescription = copy.languageSelectorLabel,
                                onSelectLanguage = onSelectLanguage,
                            )
                            if (isMedium) {
                                Button(
                                    onClick = { onSelectSection(NavSection.CONTACT) },
                                    shape = RoundedCornerShape(4.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Terracotta,
                                        contentColor = PureWhite,
                                    ),
                                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                                    modifier = Modifier
                                        .minimumInteractiveComponentSize()
                                        .testTag("header_contact_cta"),
                                ) {
                                    Text(
                                        text = copy.contactUsCta,
                                        style = MaterialTheme.typography.labelMedium,
                                        maxLines = 1,
                                    )
                                }
                            }
                            IconButton(
                                onClick = { onToggleMobileMenu(!isMobileMenuOpen) },
                                modifier = Modifier
                                    .minimumInteractiveComponentSize()
                                    .testTag("mobile_menu_toggle"),
                            ) {
                                Icon(
                                    imageVector = if (isMobileMenuOpen) Icons.Outlined.Close else Icons.Outlined.Menu,
                                    contentDescription = if (isMobileMenuOpen) copy.closeMenuLabel else copy.openMenuLabel,
                                    tint = MidnightNavy,
                                )
                            }
                        }
                    }
                }
            }

            HorizontalDivider(thickness = 1.dp, color = WarmStoneBorder.copy(alpha = 0.7f))

            AnimatedVisibility(
                visible = isMobileMenuOpen,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut(),
            ) {
                MobileNavigationDrawerPanel(
                    copy = copy,
                    activeSection = activeSection,
                    currentLanguage = currentLanguage,
                    onSelectSection = { section ->
                        onToggleMobileMenu(false)
                        onSelectSection(section)
                    },
                    onSelectLanguage = onSelectLanguage,
                )
            }
        }
    }
}

@Composable
fun VanteraWordmark(
    brandWordmark: String,
    brandSubword: String,
    onClick: (() -> Unit)? = null,
    lightOnDark: Boolean = false,
) {
    val primaryColor = if (lightOnDark) PureWhite else MidnightNavy
    val secondaryColor = if (lightOnDark) Terracotta else Terracotta

    Column(
        modifier = Modifier
            .then(
                if (onClick != null) {
                    Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { onClick() }
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                } else {
                    Modifier
                },
            )
            .testTag("vantera_wordmark"),
    ) {
        Text(
            text = brandWordmark,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.2.sp,
                fontSize = 20.sp,
            ),
            color = primaryColor,
            maxLines = 1,
        )
        Text(
            text = brandSubword,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 3.6.sp,
                fontSize = 9.sp,
            ),
            color = secondaryColor,
            maxLines = 1,
        )
    }
}

@Composable
private fun LanguageSelectorButton(
    currentLanguage: AppLanguage,
    contentDescription: String,
    onSelectLanguage: (AppLanguage) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .minimumInteractiveComponentSize()
                .clip(RoundedCornerShape(4.dp))
                .border(BorderStroke(1.dp, WarmStoneBorder), RoundedCornerShape(4.dp))
                .background(WarmStone.copy(alpha = 0.45f))
                .clickable { expanded = true }
                .padding(horizontal = 10.dp, vertical = 8.dp)
                .testTag("language_selector_button"),
        ) {
            Icon(
                imageVector = Icons.Outlined.Language,
                contentDescription = contentDescription,
                tint = MidnightNavy,
                modifier = Modifier.size(16.dp),
            )
            Text(
                text = currentLanguage.shortCode,
                style = MaterialTheme.typography.labelMedium,
                color = MidnightNavy,
                maxLines = 1,
            )
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = null,
                tint = Graphite,
                modifier = Modifier.size(16.dp),
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(PureWhite),
        ) {
            AppLanguage.entries.forEach { lang ->
                val isSelected = lang == currentLanguage
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = lang.nativeLabel,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                ),
                                color = if (isSelected) Terracotta else MidnightNavy,
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = lang.code.uppercase(),
                                style = MaterialTheme.typography.labelSmall,
                                color = Graphite.copy(alpha = 0.65f),
                            )
                        }
                    },
                    onClick = {
                        expanded = false
                        onSelectLanguage(lang)
                    },
                    modifier = Modifier.testTag("lang_option_${lang.code}"),
                )
            }
        }
    }
}

@Composable
private fun MobileNavigationDrawerPanel(
    copy: VanteraCopy,
    activeSection: NavSection,
    currentLanguage: AppLanguage,
    onSelectSection: (NavSection) -> Unit,
    onSelectLanguage: (AppLanguage) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MidnightNavyDeep)
            .padding(horizontal = 24.dp, vertical = 20.dp)
            .testTag("mobile_nav_drawer"),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        val items = listOf(
            NavSection.HOME to copy.navHome,
            NavSection.ABOUT to copy.navAbout,
            NavSection.SERVICES to copy.navServices,
            NavSection.PROJECTS to copy.navProjects,
            NavSection.CONTACT to copy.navContact,
        )

        items.forEachIndexed { idx, (section, label) ->
            val selected = activeSection == section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumInteractiveComponentSize()
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onSelectSection(section) }
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .testTag("mobile_nav_${section.name.lowercase()}"),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                    ),
                    color = if (selected) Terracotta else PureWhite,
                )
                Text(
                    text = "0${idx + 1}",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (selected) Terracotta else PureWhite.copy(alpha = 0.45f),
                )
            }
            if (idx < items.lastIndex) {
                HorizontalDivider(color = PureWhite.copy(alpha = 0.08f))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Language quick switcher inside mobile drawer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AppLanguage.entries.forEach { lang ->
                val selected = lang == currentLanguage
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (selected) Terracotta else PureWhite.copy(alpha = 0.08f))
                        .clickable { onSelectLanguage(lang) }
                        .padding(vertical = 10.dp)
                        .testTag("drawer_lang_${lang.code}"),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = lang.nativeLabel,
                        style = MaterialTheme.typography.labelMedium,
                        color = PureWhite,
                        maxLines = 1,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { onSelectSection(NavSection.CONTACT) },
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Terracotta,
                contentColor = PureWhite,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("mobile_drawer_contact_cta"),
        ) {
            Text(
                text = copy.contactUsCta,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}
