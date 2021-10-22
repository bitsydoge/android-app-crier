package com.cold0.crier.social

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.cold0.crier.social.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	repository: PostRepository
) : ViewModel() {
	lateinit var navHostController: NavHostController
	val postList = repository.getPosts()
	val userList = repository.getUsers()
}
