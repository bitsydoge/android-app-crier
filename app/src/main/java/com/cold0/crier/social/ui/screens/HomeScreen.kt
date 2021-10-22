package com.cold0.crier.social.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cold0.crier.social.data.DummyData.getUserFromUID
import com.cold0.crier.social.model.Post
import com.cold0.crier.social.model.User
import com.cold0.crier.social.ui.components.PostLayout


@Composable
fun HomeScreen(padding: PaddingValues = PaddingValues(), postList: List<Post>, userList: List<User>) {
	val lazyListState = rememberLazyListState()

	Box(
		Modifier
			.fillMaxSize()
			.padding(padding)
	) {
		LazyColumn(state = lazyListState) {
			items(postList) { post ->
				PostLayout(post, userList.getUserFromUID(post.userUID))
				Divider()
			}
		}
	}
}