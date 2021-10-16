package com.cold0.crier.social.composant

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.cold0.crier.social.model.ImageHolder

@Composable
fun DraggableImage(image: ImageHolder, painter: Painter = rememberImagePainter(image.getDataForPainter())) {
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(0f) }
    val offsetState = remember { mutableStateOf(Offset(0f, 0f)) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, rotation ->
                    scale.value *= zoom
                    rotationState.value += rotation
                    offsetState.value += Offset(pan.x.toDp().value, pan.y.toDp().value)
                }
            }
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(offsetState.value.x.dp, offsetState.value.y.dp)
                .fillMaxSize()
                .graphicsLayer(
                    scaleX = maxOf(.5f, minOf(5f, scale.value)),
                    scaleY = maxOf(.5f, minOf(5f, scale.value)),
                    rotationZ = rotationState.value
                ),
            painter = painter, contentDescription = ""
        )
    }
}