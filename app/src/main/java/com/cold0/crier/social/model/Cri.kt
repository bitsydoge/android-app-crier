package com.cold0.crier.social.model

import com.cold0.crier.social.data.DummyData.getUserFromUID
import java.util.*

data class Cri(
    val userUID: UUID,
    val content: String,
    val timestamp: Date = Date(),
    var likeCount: Int,
    var reblogCount: Int,
    var commentsCount: Int,
    var liked: Boolean,
    var rebloged: Boolean,
    var image: ImageHolder?
) {
    private var user: User? = null
    fun getUser(): User {
        if (user == null)
            user = getUserFromUID(userUID)
        return user as User
    }
}