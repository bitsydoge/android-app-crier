package com.cold0.crier.social.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity
data class Post(
	@PrimaryKey
	val uid: UUID = UUID.randomUUID(),
	val userUID: UUID,
	val content: String,
	val timestamp: Date = Date(),
	val likeCount: Int,
	val reblogCount: Int,
	val commentsCount: Int,
	val liked: Boolean,
	val reblogged: Boolean,
	val imageList: List<ImageHolder> = listOf()
)