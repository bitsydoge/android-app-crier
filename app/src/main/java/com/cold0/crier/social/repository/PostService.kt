package com.cold0.crier.social.repository

import com.cold0.crier.social.model.Post
import com.cold0.crier.social.model.User
import retrofit2.Response
import retrofit2.http.GET

interface PostService {
	@GET("posts.json")
	suspend fun getAllPosts(): Response<List<Post>>

	@GET("users.json")
	suspend fun getAllUsers(): Response<List<User>>
}