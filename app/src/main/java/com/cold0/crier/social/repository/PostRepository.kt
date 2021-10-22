package com.cold0.crier.social.repository

import android.util.Log
import com.cold0.crier.social.room.dao.PostDao
import com.cold0.crier.social.room.dao.UserDao
import javax.inject.Inject

class PostRepository @Inject constructor(
	private val remoteDataSource: PostRemoteDataSource,
	private val postLocalDataSource: PostDao,
	private val userLocalDataSource: UserDao
) {
	fun getPosts() = performGetOperation(
		databaseQuery = {
			Log.e("PostInfo", "getPosts: Use Database Query")
			postLocalDataSource.getAllPost()
		},
		networkCall = {
			Log.e("PostInfo", "getPosts: Use Remote Query")
			remoteDataSource.getPosts()
		},
		saveCallResult = { postLocalDataSource.insertAll(it) }
	)

	fun getUsers() = performGetOperation(
		databaseQuery = {
			Log.e("PostInfo", "getPosts: Use Database Query")
			userLocalDataSource.getAllUser()
		},
		networkCall = {
			Log.e("PostInfo", "getPosts: Use Remote Query")
			remoteDataSource.getUsers()
		},
		saveCallResult = { userLocalDataSource.insertAll(it) }
	)
}