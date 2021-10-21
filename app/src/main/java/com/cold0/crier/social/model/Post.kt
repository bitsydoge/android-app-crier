package com.cold0.crier.social.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cold0.crier.social.data.DummyData.getUserFromUID
import java.util.*

@Entity
data class Post(
	@PrimaryKey
	@NonNull
	val uid: UUID,
	val userUID: UUID,
	val content: String,
	val timestamp: Date = Date(),
	val likeCount: Int,
	val reblogCount: Int,
	val commentsCount: Int,
	val liked: Boolean,
	val reblogged: Boolean,
	val imageList: List<ImageHolder> = listOf()
) {
	private var user: User? = null
	fun getUser(): User {
		if (user == null)
			user = getUserFromUID(userUID)
		return user as User
	}
}