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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.cold0.crier.social.data.DummyData
import com.cold0.crier.social.ui.components.PostLayout


@Composable
fun HomeScreen(padding: PaddingValues = PaddingValues()) {
    val lazyListState = rememberLazyListState()
    val list by remember { mutableStateOf(DummyData.getPostList()) }
    Box(
        Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        LazyColumn(state = lazyListState) {
            items(list) { post ->
                PostLayout(post)
                Divider()
            }
        }
    }
}