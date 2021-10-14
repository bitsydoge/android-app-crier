package com.cold0.crier.social.model

import com.cold0.crier.social.data.DummyData.getUserFromUID
import java.util.*

data class Post(
    val userUID: UUID,
    val content: String,
    val timestamp: Date = Date(),
    val likeCount: Int,
    val reblogCount: Int,
    val commentsCount: Int,
    val liked: Boolean,
    val rebloged: Boolean,
    val imageList: List<ImageHolder> = listOf()
) {
    private var user: User? = null
    fun getUser(): User {
        if (user == null)
            user = getUserFromUID(userUID)
        return user as User
    }
}