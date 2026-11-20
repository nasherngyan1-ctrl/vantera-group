package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.ZoomOutMap
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.i18n.VanteraCopy
import com.example.model.DivisionItem
import com.example.model.ProjectConcept
import com.example.ui.theme.Graphite
import com.example.ui.theme.GraphiteMuted
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.MidnightNavyDeep
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Terracotta
import com.example.ui.theme.TerracottaTint
import com.example.ui.theme.WarmStone
import com.example.ui.theme.WarmStoneBorder

@Composable
fun DivisionsSection(
    copy: VanteraCopy,
    onSelectDivision: (DivisionItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(PureWhite)
            .padding(horizontal = 24.dp, vertical = 56.dp)
            .testTag("section_services"),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 1160.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(28.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SectionEyebrow(text = copy.divisionsEyebrow)
                Text(
                    text = copy.divisionsHeadline,
                    style = MaterialTheme.typography.displayMedium,
                    color = MidnightNavy,
                )
                Text(
                    text = copy.divisionsSubtitle,
                    style = MaterialTheme.typography.bodyLarge,
                    color = GraphiteMuted,
                    modifier = Modifier.widthIn(max = 640.dp),
                )
            }

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val threeColumn = maxWidth >= 880.dp
                if (threeColumn) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        copy.divisions.forEach { division ->
                            DivisionCard(
                                division = division,
                                exploreLabel = copy.divisionExploreLabel,
                                onClick = { onSelectDivision(division) },
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        copy.divisions.forEach { division ->
                            DivisionCard(
                                division = division,
                                exploreLabel = copy.divisionExploreLabel,
                                onClick = { onSelectDivision(division) },
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
private fun DivisionCard(
    division: DivisionItem,
    exploreLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .testTag("division_card_${division.id.name.lowercase()}"),
        shape = RoundedCornerShape(4.dp),
        color = PureWhite,
        border = BorderStroke(1.dp, WarmStoneBorder),
        shadowElevation = 1.dp,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f),
            ) {
                Image(
                    painter = painterResource(id = division.imageRes),
                    contentDescription = division.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    MidnightNavyDeep.copy(alpha = 0.25f),
                                    MidnightNavyDeep.copy(alpha = 0.0f),
                                    MidnightNavyDeep.copy(alpha = 0.65f),
                                ),
                            ),
                        ),
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(14.dp)
                        .background(MidnightNavy.copy(alpha = 0.9f), RoundedCornerShape(2.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                ) {
                    Text(
                        text = division.numberLabel,
                        style = MaterialTheme.typography.labelMedium,
                        color = Terracotta,
                    )
                }
                Text(
                    text = division.subtitle,
                    style = MaterialTheme.typography.labelMedium,
                    color = WarmStone,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp),
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = division.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MidnightNavy,
                )
                Text(
                    text = division.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Graphite,
                )

                HorizontalDivider(
                    modifier = Modifier.padding(top = 4.dp),
                    color = WarmStoneBorder.copy(alpha = 0.7f),
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumInteractiveComponentSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = exploreLabel,
                        style = MaterialTheme.typography.labelLarge,
                        color = Terracotta,
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = null,
                        tint = Terracotta,
                        modifier = Modifier.size(18.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun FeaturedProjectsSection(
    copy: VanteraCopy,
    onSelectProject: (ProjectConcept) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(WarmStone)
            .padding(horizontal = 24.dp, vertical = 56.dp)
            .testTag("section_projects"),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 1160.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                SectionEyebrow(text = copy.projectsEyebrow)
                Text(
                    text = copy.projectsHeadline,
                    style = MaterialTheme.typography.displayMedium,
                    color = MidnightNavy,
                )
                Text(
                    text = copy.projectsSubtitle,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Graphite,
                    modifier = Modifier.widthIn(max = 680.dp),
                )
            }

            // Explicit conceptual demonstration disclosure banner
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(PureWhite)
                    .border(BorderStroke(1.dp, MidnightNavy.copy(alpha = 0.25f)), RoundedCornerShape(4.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = Terracotta,
                    modifier = Modifier.size(20.dp),
                )
                Text(
                    text = copy.projectsDisclaimerBanner,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = MidnightNavy,
                )
            }

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val twoColumns = maxWidth >= 680.dp
                if (twoColumns) {
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        copy.projects.chunked(2).forEach { pair ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(20.dp),
                            ) {
                                pair.forEach { project ->
                                    ProjectConceptCard(
                                        project = project,
                                        inspectLabel = copy.projectsInspectLabel,
                                        onClick = { onSelectProject(project) },
                                        modifier = Modifier.weight(1f),
                                    )
                                }
                                if (pair.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        copy.projects.forEach { project ->
                            ProjectConceptCard(
                                project = project,
                                inspectLabel = copy.projectsInspectLabel,
                                onClick = { onSelectProject(project) },
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
private fun ProjectConceptCard(
    project: ProjectConcept,
    inspectLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .testTag("project_card_${project.id}"),
        shape = RoundedCornerShape(4.dp),
        color = PureWhite,
        border = BorderStroke(1.dp, WarmStoneBorder),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f),
            ) {
                Image(
                    painter = painterResource(id = project.imageRes),
                    contentDescription = project.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    MidnightNavyDeep.copy(alpha = 0.4f),
                                    MidnightNavyDeep.copy(alpha = 0.0f),
                                    MidnightNavyDeep.copy(alpha = 0.5f),
                                ),
                            ),
                        ),
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .background(TerracottaTint, RoundedCornerShape(2.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = project.conceptTag,
                            style = MaterialTheme.typography.labelSmall,
                            color = MidnightNavy,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(MidnightNavy.copy(alpha = 0.85f), RoundedCornerShape(2.dp))
                            .padding(6.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ZoomOutMap,
                            contentDescription = inspectLabel,
                            tint = PureWhite,
                            modifier = Modifier.size(16.dp),
                        )
                    }
                }

                Text(
                    text = project.code,
                    style = MaterialTheme.typography.labelSmall,
                    color = WarmStone,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp),
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = project.divisionLabel,
                    style = MaterialTheme.typography.labelMedium,
                    color = Terracotta,
                )
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MidnightNavy,
                )
                Text(
                    text = project.summary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = GraphiteMuted,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.minimumInteractiveComponentSize(),
                ) {
                    Text(
                        text = inspectLabel,
                        style = MaterialTheme.typography.labelLarge,
                        color = MidnightNavy,
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = null,
                        tint = MidnightNavy,
                        modifier = Modifier.size(16.dp),
                    )
                }
            }
        }
    }
}
