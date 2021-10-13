package com.cold0.crier.social

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cold0.crier.social.data.DummyData.getRandomCri
import com.cold0.crier.social.model.Cri
import com.cold0.crier.social.theme.ColorUtils.grayed
import com.cold0.crier.social.theme.CrierSocialTheme
import java.io.File

@Composable
fun CriItem(cri: Cri) {
    Row(
        modifier = Modifier
            .padding(all = 10.dp)
            .background(MaterialTheme.colors.surface)
    ) {
        CriUserAvatar(cri)
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            CriUserInfo(cri)
            CriContent(cri)
            Spacer(modifier = Modifier.size(4.dp))
            CriButtons(cri)
        }
    }
}

@Composable
private fun CriUserAvatar(cri: Cri) {
    val user = cri.getUser()
    Box(Modifier.clickable {

    }) {
        Image(
            painter = rememberImagePainter(
                data = if (user.avatar.local != null) File(user.avatar.local.toString()) else user.avatar.online,
                builder = {
                    crossfade(true)
                }
            ),
            "",
            modifier = Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(25.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CriUserInfo(cri: Cri) {
    val user = cri.getUser()
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = user.name,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
        )
        if (user.verified) {
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                Icons.Filled.VerifiedUser,
                "",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(16.dp)
            )
        }
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = "@${user.handle}", color = MaterialTheme.colors.onSurface.grayed(), maxLines = 1)
    }
}

@Composable
private fun CriContent(cri: Cri) {
    Text(
        text = cri.content,
        style = TextStyle(fontSize = 14.sp)
    )
    val image = cri.image
    if (image != null) {
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = rememberImagePainter(
                data = if (image.local != null) File(image.local.toString()) else image.online,
                builder = {
                    crossfade(true)
                }
            ),
            "",
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(image.colorPlaceHolder),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CriButtons(cri: Cri) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            IconButton(
                onClick = { },
            ) {
                Icon(
                    Icons.Outlined.Comment,
                    contentDescription = null//TODO
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = cri.commentsCount.toString(),
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            )
        }
        Row {
            IconButton(
                onClick = { },
            ) {
                Icon(
                    painterResource(R.drawable.ic_reblog),
                    tint = if (cri.rebloged) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface,
                    contentDescription = null, //TODO,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = cri.reblogCount.toString(),
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            )
        }
        Row {
            IconButton(
                onClick = { },
            ) {
                Icon(
                    if (cri.liked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    tint = if (cri.liked) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface,
                    contentDescription = null//TODO
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = cri.likeCount.toString(),
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            )
        }
        Row {
            IconButton(
                onClick = { },
            ) {
                Icon(
                    Icons.Outlined.Share,
                    contentDescription = null//TODO
                )
            }
        }
    }
}

// ---------------------------------------------------------------
// COMPOSE PREVIEW
// ---------------------------------------------------------------
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    CrierSocialTheme {
        CriItem(getRandomCri())
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreviewDark() {
    CrierSocialTheme(true) {
        CriItem(getRandomCri())
    }
}