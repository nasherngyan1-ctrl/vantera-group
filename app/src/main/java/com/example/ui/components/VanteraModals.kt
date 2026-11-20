package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.i18n.VanteraCopy
import com.example.model.DivisionItem
import com.example.model.ProjectConcept
import com.example.model.ServiceInterest
import com.example.ui.theme.Graphite
import com.example.ui.theme.GraphiteMuted
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Terracotta
import com.example.ui.theme.TerracottaTint
import com.example.ui.theme.WarmStone
import com.example.ui.theme.WarmStoneBorder

@Composable
fun DivisionDetailDialog(
    copy: VanteraCopy,
    division: DivisionItem,
    onDismiss: () -> Unit,
    onInquire: (ServiceInterest) -> Unit,
) {
    BackHandler { onDismiss() }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .widthIn(max = 640.dp)
                .fillMaxWidth()
                .testTag("division_detail_dialog"),
            shape = RoundedCornerShape(6.dp),
            color = PureWhite,
            shadowElevation = 12.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f),
                ) {
                    Image(
                        painter = painterResource(id = division.imageRes),
                        contentDescription = division.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(10.dp)
                            .minimumInteractiveComponentSize()
                            .background(MidnightNavy.copy(alpha = 0.85f), RoundedCornerShape(4.dp))
                            .testTag("division_dialog_close"),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = copy.closeMenuLabel,
                            tint = PureWhite,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(14.dp)
                            .background(MidnightNavy.copy(alpha = 0.9f), RoundedCornerShape(2.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = "${division.numberLabel} • ${division.subtitle}",
                            style = MaterialTheme.typography.labelMedium,
                            color = Terracotta,
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(
                        text = division.title,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MidnightNavy,
                    )
                    Text(
                        text = division.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Graphite,
                    )

                    HorizontalDivider(color = WarmStoneBorder)

                    Text(
                        text = copy.divisionFocusTitle,
                        style = MaterialTheme.typography.labelLarge,
                        color = Terracotta,
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        division.focusAreas.forEach { area ->
                            Row(
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .size(6.dp)
                                        .background(Terracotta, RoundedCornerShape(1.dp)),
                                )
                                Text(
                                    text = area,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MidnightNavy,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Button(
                        onClick = { onInquire(division.serviceInterest) },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MidnightNavy,
                            contentColor = PureWhite,
                        ),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .minimumInteractiveComponentSize()
                            .testTag("division_dialog_inquire"),
                    ) {
                        Text(
                            text = copy.divisionInquireCta,
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
fun ProjectConceptDialog(
    copy: VanteraCopy,
    project: ProjectConcept,
    onDismiss: () -> Unit,
) {
    BackHandler { onDismiss() }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .widthIn(max = 680.dp)
                .fillMaxWidth()
                .testTag("project_concept_dialog"),
            shape = RoundedCornerShape(6.dp),
            color = PureWhite,
            shadowElevation = 12.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
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
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(10.dp)
                            .minimumInteractiveComponentSize()
                            .background(MidnightNavy.copy(alpha = 0.85f), RoundedCornerShape(4.dp))
                            .testTag("project_dialog_close"),
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = copy.closeMenuLabel,
                            tint = PureWhite,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(14.dp)
                            .background(TerracottaTint, RoundedCornerShape(2.dp))
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                    ) {
                        Text(
                            text = copy.projectConceptBadge,
                            style = MaterialTheme.typography.labelSmall,
                            color = MidnightNavy,
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = "${project.code} • ${project.divisionLabel}",
                        style = MaterialTheme.typography.labelMedium,
                        color = Terracotta,
                    )
                    Text(
                        text = project.title,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MidnightNavy,
                    )
                    Text(
                        text = project.summary,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Graphite,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp))
                            .background(WarmStone.copy(alpha = 0.55f))
                            .border(BorderStroke(1.dp, WarmStoneBorder), RoundedCornerShape(4.dp))
                            .padding(14.dp),
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = copy.projectNotesLabel,
                                style = MaterialTheme.typography.labelMedium,
                                color = MidnightNavy,
                            )
                            Text(
                                text = project.architecturalNotes,
                                style = MaterialTheme.typography.bodyMedium,
                                color = GraphiteMuted,
                            )
                        }
                    }
                    Text(
                        text = copy.projectsDisclaimerBanner,
                        style = MaterialTheme.typography.bodySmall,
                        color = GraphiteMuted,
                    )
                }
            }
        }
    }
}
