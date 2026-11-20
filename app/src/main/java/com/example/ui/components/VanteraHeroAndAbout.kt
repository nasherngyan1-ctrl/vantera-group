package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.i18n.VanteraCopy
import com.example.ui.theme.Graphite
import com.example.ui.theme.GraphiteMuted
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.MidnightNavyDeep
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Terracotta
import com.example.ui.theme.WarmStone
import com.example.ui.theme.WarmStoneBorder
import com.example.ui.theme.WarmStoneLight

@Composable
fun HeroSection(
    copy: VanteraCopy,
    onExploreBusiness: () -> Unit,
    onContactTeam: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 520.dp, max = 640.dp)
            .background(MidnightNavyDeep)
            .testTag("section_hero"),
    ) {
        // 1. Corporate headquarters image
        Image(
            painter = painterResource(id = R.drawable.img_corporate_headquarters),
            contentDescription = copy.brandWordmark,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
        )

        // Editorial dark Midnight Navy scrim for legible contrast in LTR and RTL
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MidnightNavyDeep.copy(alpha = 0.68f),
                            MidnightNavy.copy(alpha = 0.72f),
                            MidnightNavyDeep.copy(alpha = 0.92f),
                        ),
                    ),
                ),
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 48.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 1160.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                // Eyebrow + Conceptual Showcase tag
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .width(28.dp)
                            .height(2.dp)
                            .background(Terracotta),
                    )
                    Text(
                        text = copy.heroEyebrow,
                        style = MaterialTheme.typography.labelMedium,
                        color = WarmStone,
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .border(BorderStroke(1.dp, Terracotta.copy(alpha = 0.6f)), RoundedCornerShape(3.dp))
                        .background(MidnightNavyDeep.copy(alpha = 0.65f))
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                ) {
                    Text(
                        text = copy.heroDemoChip,
                        style = MaterialTheme.typography.labelSmall,
                        color = WarmStone,
                    )
                }

                Text(
                    text = copy.heroHeadline,
                    style = MaterialTheme.typography.displayLarge,
                    color = PureWhite,
                    modifier = Modifier.widthIn(max = 760.dp),
                )

                Text(
                    text = copy.heroSubtitle,
                    style = MaterialTheme.typography.bodyLarge,
                    color = WarmStone.copy(alpha = 0.92f),
                    modifier = Modifier.widthIn(max = 640.dp),
                )

                Spacer(modifier = Modifier.height(4.dp))

                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    val stackButtons = maxWidth < 460.dp
                    if (stackButtons) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            HeroActionButtons(
                                copy = copy,
                                onExploreBusiness = onExploreBusiness,
                                onContactTeam = onContactTeam,
                                fullWidth = true,
                            )
                        }
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            HeroActionButtons(
                                copy = copy,
                                onExploreBusiness = onExploreBusiness,
                                onContactTeam = onContactTeam,
                                fullWidth = false,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroActionButtons(
    copy: VanteraCopy,
    onExploreBusiness: () -> Unit,
    onContactTeam: () -> Unit,
    fullWidth: Boolean,
) {
    val widthMod = if (fullWidth) Modifier.fillMaxWidth() else Modifier

    Button(
        onClick = onExploreBusiness,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Terracotta,
            contentColor = PureWhite,
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
        modifier = widthMod
            .minimumInteractiveComponentSize()
            .testTag("hero_explore_button"),
    ) {
        Text(
            text = copy.heroPrimaryCta,
            style = MaterialTheme.typography.labelLarge,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
        )
    }

    OutlinedButton(
        onClick = onContactTeam,
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.2.dp, WarmStone.copy(alpha = 0.85f)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = PureWhite,
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
        modifier = widthMod
            .minimumInteractiveComponentSize()
            .testTag("hero_contact_button"),
    ) {
        Text(
            text = copy.heroSecondaryCta,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Composable
fun AboutSection(
    copy: VanteraCopy,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(WarmStoneLight)
            .padding(horizontal = 24.dp, vertical = 56.dp)
            .testTag("section_about"),
        contentAlignment = Alignment.Center,
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .widthIn(max = 1160.dp)
                .fillMaxWidth(),
        ) {
            val isSplit = maxWidth >= 760.dp

            if (isSplit) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AboutTextBlock(
                        copy = copy,
                        modifier = Modifier.weight(1.05f),
                    )
                    AboutSkylineImage(
                        caption = copy.aboutImageCaption,
                        modifier = Modifier.weight(0.95f),
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(28.dp),
                ) {
                    AboutTextBlock(copy = copy)
                    AboutSkylineImage(caption = copy.aboutImageCaption)
                }
            }
        }
    }
}

@Composable
private fun AboutTextBlock(
    copy: VanteraCopy,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SectionEyebrow(text = copy.aboutEyebrow)

        Text(
            text = copy.aboutHeadline,
            style = MaterialTheme.typography.displayMedium,
            color = MidnightNavy,
        )

        Text(
            text = copy.aboutParagraph1,
            style = MaterialTheme.typography.bodyLarge,
            color = Graphite,
        )

        Text(
            text = copy.aboutParagraph2,
            style = MaterialTheme.typography.bodyLarge,
            color = GraphiteMuted,
        )

        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp),
            color = WarmStoneBorder,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint = Terracotta,
                modifier = Modifier.size(20.dp),
            )
            Text(
                text = copy.aboutLocationBadge,
                style = MaterialTheme.typography.titleMedium,
                color = MidnightNavy,
            )
        }
    }
}

@Composable
private fun AboutSkylineImage(
    caption: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 10.5f)
                .clip(RoundedCornerShape(4.dp))
                .border(BorderStroke(1.dp, WarmStoneBorder), RoundedCornerShape(4.dp)),
        ) {
            // 2. City skyline — Company overview
            Image(
                painter = painterResource(id = R.drawable.img_city_skyline),
                contentDescription = caption,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Terracotta),
            )
        }
        Text(
            text = caption,
            style = MaterialTheme.typography.bodySmall,
            color = GraphiteMuted,
        )
    }
}

@Composable
fun SectionEyebrow(
    text: String,
    textColor: Color = Terracotta,
    lineColor: Color = Terracotta,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Box(
            modifier = Modifier
                .width(22.dp)
                .height(2.dp)
                .background(lineColor),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = textColor,
        )
    }
}
