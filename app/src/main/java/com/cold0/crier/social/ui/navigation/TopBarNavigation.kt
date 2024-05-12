package com.cold0.crier.social.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.cold0.crier.social.data.DummyData
import com.cold0.crier.social.theme.CrierSocialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit = {},
    onActionIconPressed: () -> Unit = {}
) {
    Column {
        TopAppBar(
            modifier = modifier.background(MaterialTheme.colorScheme.surface),
            title = @Composable {
                Box(
					Modifier
						.height(32.dp)
						.fillMaxWidth()
                ) {
                    val user = DummyData.currentUser

                    // LEFT NAVIGATION
                    Row(
						Modifier
							.fillMaxHeight()
							.align(Alignment.CenterStart)
                    ) {
                        IconButton(
                            onClick = { onNavIconPressed() },
                            enabled = true,
                        ) {
                            Image(
                                painter = rememberImagePainter(
                                    data = user.avatar.getDataForPainter(),
                                    builder = {
                                        crossfade(true)
                                        transformations(CircleCropTransformation())
                                    }
                                ),
                                "",
                                modifier = Modifier
                                    .size(32.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    // TITLE
                    Row(
						Modifier
							.fillMaxHeight()
							.align(Alignment.Center)
                    ) {
                        Icon(
                            Icons.Filled.Campaign,
                            "TODO",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    // ACTIONS
                    Row(
						Modifier
							.fillMaxHeight()
							.align(Alignment.CenterEnd),
                        content = {
                            IconButton(
                                onClick = { onActionIconPressed() },
                                enabled = true,
                            ) {
                                Icon(
                                    Icons.Outlined.AutoAwesome,
                                    "TODO",
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    )
                }
            })
        HorizontalDivider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarExtra(
    modifier: Modifier = Modifier,
    onNavIconPressed: @Composable () -> Unit = {},
    onActionIconPressed: () -> Unit = {},
    title: String
) {
    Column {
        TopAppBar(
            modifier = modifier.background(MaterialTheme.colorScheme.surface),
            title = @Composable {
                Box(
					Modifier
						.height(32.dp)
						.fillMaxWidth()
                ) {
                    val user = DummyData.currentUser

                    // LEFT NAVIGATION
                    Row(
						Modifier
							.fillMaxHeight()
							.align(Alignment.CenterStart)
                    ) {
                        IconButton(
                            onClick = { onNavIconPressed() },
                            enabled = true,
                        ) {
                            Image(
                                painter = rememberImagePainter(
                                    data = user.avatar.getDataForPainter(),
                                    builder = {
                                        crossfade(true)
                                        transformations(CircleCropTransformation())
                                    }
                                ),
                                "",
                                modifier = Modifier
                                    .size(32.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Text(
                            text = title,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // TITLE
                    Row(
						Modifier
							.fillMaxHeight()
							.align(Alignment.Center)
                    ) {

                    }

                    // ACTIONS
                    Row(
						Modifier
							.fillMaxHeight()
							.align(Alignment.CenterEnd),
                        content = {
                            IconButton(
                                onClick = { onActionIconPressed() },
                                enabled = true,
                            ) {
                                Icon(
                                    Icons.Outlined.AutoAwesome,
                                    "TODO",
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    )
                }
            })
        HorizontalDivider()
    }
}

// ---------------------------------------------------------------
// COMPOSE PREVIEW
// ---------------------------------------------------------------
@Preview
@Composable
fun TopBarPreview() {
    CrierSocialTheme(darkTheme = false) {
        TopBar()
    }
}

@Preview
@Composable
fun TopBarPreviewDark() {
    CrierSocialTheme(darkTheme = true) {
        TopBar()
    }
}