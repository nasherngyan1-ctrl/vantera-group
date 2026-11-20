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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.i18n.VanteraCopy
import com.example.model.WhyChoosePillar
import com.example.ui.theme.Graphite
import com.example.ui.theme.GraphiteMuted
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.MidnightNavySoft
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Terracotta
import com.example.ui.theme.WarmStone
import com.example.ui.theme.WarmStoneBorder
import com.example.ui.theme.WarmStoneLight

@Composable
fun WhyChooseSection(
    copy: VanteraCopy,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MidnightNavy)
            .padding(horizontal = 24.dp, vertical = 56.dp)
            .testTag("section_why_choose"),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 1160.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SectionEyebrow(
                    text = copy.whyEyebrow,
                    textColor = Terracotta,
                    lineColor = Terracotta,
                )
                Text(
                    text = copy.whyHeadline,
                    style = MaterialTheme.typography.displayMedium,
                    color = PureWhite,
                )
                Text(
                    text = copy.whySubtitle,
                    style = MaterialTheme.typography.bodyLarge,
                    color = WarmStone.copy(alpha = 0.85f),
                    modifier = Modifier.widthIn(max = 680.dp),
                )
            }

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val twoColumns = maxWidth >= 680.dp
                if (twoColumns) {
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        copy.whyPillars.chunked(2).forEach { pair ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(20.dp),
                            ) {
                                pair.forEach { pillar ->
                                    WhyPillarBlock(
                                        pillar = pillar,
                                        modifier = Modifier.weight(1f),
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        copy.whyPillars.forEach { pillar ->
                            WhyPillarBlock(
                                pillar = pillar,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WhyPillarBlock(
    pillar: WhyChoosePillar,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MidnightNavySoft.copy(alpha = 0.65f))
            .border(BorderStroke(1.dp, PureWhite.copy(alpha = 0.12f)), RoundedCornerShape(4.dp))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = pillar.indexLabel,
                style = MaterialTheme.typography.headlineMedium,
                color = Terracotta,
            )
            Box(
                modifier = Modifier
                    .width(36.dp)
                    .height(1.dp)
                    .background(Terracotta.copy(alpha = 0.6f)),
            )
        }
        Text(
            text = pillar.title,
            style = MaterialTheme.typography.titleLarge,
            color = PureWhite,
        )
        Text(
            text = pillar.description,
            style = MaterialTheme.typography.bodyMedium,
            color = WarmStone.copy(alpha = 0.85f),
        )
    }
}

@Composable
fun ExpertiseSection(
    copy: VanteraCopy,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(PureWhite)
            .padding(horizontal = 24.dp, vertical = 56.dp)
            .testTag("section_expertise"),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 1160.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(28.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SectionEyebrow(text = copy.expertiseEyebrow)
                Text(
                    text = copy.expertiseHeadline,
                    style = MaterialTheme.typography.displayMedium,
                    color = MidnightNavy,
                    modifier = Modifier.widthIn(max = 760.dp),
                )
                Text(
                    text = copy.expertiseBody,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Graphite,
                    modifier = Modifier.widthIn(max = 720.dp),
                )
            }

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val sideBySide = maxWidth >= 720.dp
                if (sideBySide) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                    ) {
                        ExpertisePhotoFrame(
                            imageRes = R.drawable.img_engineering_team,
                            caption = copy.expertiseCaptionEngineering,
                            aspectRatio = 16f / 10f,
                            modifier = Modifier.weight(1.1f),
                        )
                        ExpertisePhotoFrame(
                            imageRes = R.drawable.img_corporate_office,
                            caption = copy.expertiseCaptionOffice,
                            aspectRatio = 16f / 10f,
                            modifier = Modifier.weight(0.9f),
                        )
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        ExpertisePhotoFrame(
                            imageRes = R.drawable.img_engineering_team,
                            caption = copy.expertiseCaptionEngineering,
                            aspectRatio = 16f / 10f,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        ExpertisePhotoFrame(
                            imageRes = R.drawable.img_corporate_office,
                            caption = copy.expertiseCaptionOffice,
                            aspectRatio = 16f / 10f,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ExpertisePhotoFrame(
    imageRes: Int,
    caption: String,
    aspectRatio: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(aspectRatio)
                .clip(RoundedCornerShape(4.dp))
                .border(BorderStroke(1.dp, WarmStoneBorder), RoundedCornerShape(4.dp)),
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = caption,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .width(12.dp)
                    .height(2.dp)
                    .background(Terracotta),
            )
            Text(
                text = caption,
                style = MaterialTheme.typography.titleMedium,
                color = MidnightNavy,
            )
        }
    }
}

@Composable
fun PartnershipsSection(
    copy: VanteraCopy,
    onDiscussPartnership: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(WarmStoneLight)
            .padding(horizontal = 24.dp, vertical = 56.dp)
            .testTag("section_partnerships"),
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
                    PartnershipImage(
                        caption = copy.partnershipsHeadline,
                        modifier = Modifier.weight(1f),
                    )
                    PartnershipContent(
                        copy = copy,
                        onDiscussPartnership = onDiscussPartnership,
                        modifier = Modifier.weight(1f),
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    PartnershipImage(caption = copy.partnershipsHeadline)
                    PartnershipContent(
                        copy = copy,
                        onDiscussPartnership = onDiscussPartnership,
                    )
                }
            }
        }
    }
}

@Composable
private fun PartnershipImage(
    caption: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 10f)
            .clip(RoundedCornerShape(4.dp))
            .border(BorderStroke(1.dp, WarmStoneBorder), RoundedCornerShape(4.dp)),
    ) {
        // 10. Business meeting — Partnerships
        Image(
            painter = painterResource(id = R.drawable.img_business_meeting),
            contentDescription = caption,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun PartnershipContent(
    copy: VanteraCopy,
    onDiscussPartnership: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SectionEyebrow(text = copy.partnershipsEyebrow)
        Text(
            text = copy.partnershipsHeadline,
            style = MaterialTheme.typography.displayMedium,
            color = MidnightNavy,
        )
        Text(
            text = copy.partnershipsBody,
            style = MaterialTheme.typography.bodyLarge,
            color = GraphiteMuted,
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp),
            color = WarmStoneBorder,
        )
        Button(
            onClick = onDiscussPartnership,
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MidnightNavy,
                contentColor = PureWhite,
            ),
            contentPadding = PaddingValues(horizontal = 22.dp, vertical = 14.dp),
            modifier = Modifier
                .minimumInteractiveComponentSize()
                .testTag("partnerships_cta_button"),
        ) {
            Text(
                text = copy.partnershipsCta,
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
