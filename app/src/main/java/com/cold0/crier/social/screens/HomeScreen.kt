package com.cold0.crier.social.screens

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
import com.cold0.crier.social.data.DummyData


@Composable
fun HomeScreen(padding: PaddingValues = PaddingValues()) {
    val lazyListState = rememberLazyListState()
    Box(
        Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        LazyColumn(state = lazyListState) {
            items(DummyData.getCriList()) { cri ->
                CriComponent(cri)
                Divider()
            }
        }
    }
}