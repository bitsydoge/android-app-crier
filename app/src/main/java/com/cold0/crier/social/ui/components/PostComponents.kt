package com.cold0.crier.social.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cold0.crier.social.MainViewModel
import com.cold0.crier.social.R
import com.cold0.crier.social.model.ImageHolder
import com.cold0.crier.social.model.Post
import com.cold0.crier.social.model.User
import com.cold0.crier.social.theme.ColorUtils.grayed
import com.cold0.crier.social.ui.ScreenNavitationsItems
import com.cold0.crier.social.utils.NotImplementedAlert

@Composable
fun PostLayout(post: Post, user: User, viewModel: MainViewModel) {
	Row(
		modifier = Modifier
			.padding(all = 10.dp)
			.background(MaterialTheme.colorScheme.surface)
	) {
		PostUserAvatar(user, viewModel)
		Spacer(modifier = Modifier.size(12.dp))
		Column {
			PostUserInfo(user)
			PostContent(post)
			Spacer(modifier = Modifier.size(4.dp))
			PostActions(post)
		}
	}
}

@Composable
private fun PostUserAvatar(user: User, viewModel: MainViewModel) {
	Image(
		painter = rememberImagePainter(
			data = user.avatar.getDataForPainter(),
			builder = {
				crossfade(true)
			}
		),
		"",
		modifier = Modifier
			.size(50.dp)
			.clip(shape = CircleShape)
			.clickable(onClick = { viewModel.navigateTo("${ScreenNavitationsItems.Profile.route}/${user.uid}") }),
		contentScale = ContentScale.Crop
	)
}

@Composable
private fun PostUserInfo(user: User) {
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
				tint = MaterialTheme.colorScheme.primary,
				modifier = Modifier.size(16.dp)
			)
		}
		Spacer(modifier = Modifier.size(5.dp))
		Text(text = "@${user.handle}", color = MaterialTheme.colorScheme.onSurface.grayed(), maxLines = 1)
	}
}

@Composable
private fun PostContent(post: Post) {
	var numberClickable = 0
	val textAnnotated = buildAnnotatedString {
		val splits = post.content.split("#") // Split text with # so all odd index start with a #hashtag
		splits.forEachIndexed { i, split ->
			if (i % 2 == 1) {
				val splitWhitespace = split.split("\\s+".toRegex()) // Split text with whitespace
				pushStringAnnotation(
					tag = "$numberClickable",
					annotation = splitWhitespace[0]
				)
				numberClickable++
				withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)) {
					append("#${splitWhitespace[0]}")
				}
				pop()
				if (splitWhitespace.size > 1) { // Even so don't contain #hashtag
					withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
						append(" " + splitWhitespace.subList(1, splitWhitespace.size).joinToString(" "))
					}
				}
			} else {
				withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
					append(split)
				}
			}
		}
	}
	val context = LocalContext.current
	ClickableText(
		textAnnotated,
		onClick = { offset ->
			for (i in 0..numberClickable) {
				textAnnotated.getStringAnnotations(
					tag = "$i",
					start = offset,
					end = offset
				).firstOrNull()?.let { annotation ->
					Toast.makeText(context, "Clicked on : ${annotation.item}", Toast.LENGTH_SHORT).show()
				}
			}
		}
	)
	// Show image if post have Image
	if (post.imageList.isNotEmpty()) {
		Spacer(modifier = Modifier.height(8.dp))
		MultipleImageLayout(imageList = post.imageList)
	}
}

@Composable
private fun MultipleImageLayout(imageList: List<ImageHolder>) {
	if (imageList.isEmpty())
		return

	Box(Modifier.clip(shape = RoundedCornerShape(16.dp))) {
		when {
			imageList.size == 1 -> {
				ImageLayout1(imageList = imageList)
			}
			imageList.size == 2 -> {
				ImageLayout2(imageList = imageList)
			}
			imageList.size == 3 -> {
				ImageLayout3(imageList = imageList)
			}
			imageList.size == 4 -> {
				ImageLayout4(imageList = imageList)
			}
			imageList.size > 4 -> {
				ImageLayoutN(imageList = imageList)
			}
		}
	}
}

@Composable
private fun PostActions(post: Post) {
	var notImplementedAlertShow by remember { mutableStateOf(false) }
	if (notImplementedAlertShow) {
		NotImplementedAlert { notImplementedAlertShow = false }
	}
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(end = 16.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Row {
			IconButton(
				onClick = { notImplementedAlertShow = true },
			) {
				Icon(
					Icons.AutoMirrored.Outlined.Comment,
					contentDescription = null//TODO
				)
			}
			Spacer(modifier = Modifier.size(4.dp))
			Text(
				text = post.commentsCount.toString(),
				modifier = Modifier
					.fillMaxHeight()
					.align(Alignment.CenterVertically)
			)
		}
		Row {
			IconButton(
				onClick = { notImplementedAlertShow = true },
			) {
				Icon(
					painterResource(R.drawable.ic_reblog),
					tint = if (post.reblogged) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
					contentDescription = null, //TODO,
					modifier = Modifier.size(32.dp)
				)
			}
			Spacer(modifier = Modifier.size(4.dp))
			Text(
				text = post.reblogCount.toString(),
				modifier = Modifier
					.fillMaxHeight()
					.align(Alignment.CenterVertically)
			)
		}
		Row {
			IconButton(
				onClick = { notImplementedAlertShow = true },
			) {
				Icon(
					if (post.liked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
					tint = if (post.liked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
					contentDescription = null//TODO
				)
			}
			Spacer(modifier = Modifier.size(4.dp))
			Text(
				text = post.likeCount.toString(),
				modifier = Modifier
					.fillMaxHeight()
					.align(Alignment.CenterVertically)
			)
		}
		Row {
			IconButton(
				onClick = { notImplementedAlertShow = true },
			) {
				Icon(
					Icons.Outlined.Share,
					contentDescription = null//TODO
				)
			}
		}
	}
}