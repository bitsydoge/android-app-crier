package com.cold0.crier.social

import androidx.lifecycle.ViewModel
import com.cold0.crier.social.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	repository: PostRepository
) : ViewModel() {
	val postList = repository.getPosts()
	val userList = repository.getUsers()
}
