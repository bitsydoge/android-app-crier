package com.cold0.crier.social.model

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class ImageHolder(
    var colorPlaceHolder: Color = Color.White,
    var online: String = "https://i.pravatar.cc/300?img=${Random.nextInt(0, 71)}",
    var local: String? = null
)