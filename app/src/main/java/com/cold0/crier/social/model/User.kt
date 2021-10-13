package com.cold0.crier.social.model

import java.util.*

data class User(
    val uid: UUID,
    val name: String,
    val handle: String,
    val avatar: ImageHolder = ImageHolder(),
    val verified: Boolean,
    val follower: Int,
    val following: Int
)