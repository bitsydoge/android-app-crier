package com.cold0.crier.social.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class User(
	@PrimaryKey
	val uid: UUID,
	val name: String,
	val handle: String,
	val avatar: ImageHolder = ImageHolder(),
	val verified: Boolean,
	val follower: Int,
	val following: Int
)