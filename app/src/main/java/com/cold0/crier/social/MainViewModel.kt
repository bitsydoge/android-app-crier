package com.cold0.crier.social

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cold0.crier.social.data.DummyData.getPostList
import com.cold0.crier.social.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {
	private val _postList = MutableLiveData<List<Post>>()
	val postList: LiveData<List<Post>>
		get() = _postList

	init {
		_postList.value = getPostList()
	}
}