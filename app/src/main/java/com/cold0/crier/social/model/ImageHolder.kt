package com.cold0.crier.social.model

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class ImageHolder(
    var width: Int,
    var height: Int,
    var colorAverage: Color = Color.White,
    var online: String = "https://i.pravatar.cc/300?img=${Random.nextInt(0, 71)}",
    var local: String? = null
)