package com.cold0.crier.social.di

import android.content.Context
import com.cold0.crier.social.repository.PostRemoteDataSource
import com.cold0.crier.social.repository.PostRepository
import com.cold0.crier.social.repository.PostService
import com.cold0.crier.social.room.CrierDatabase
import com.cold0.crier.social.room.dao.PostDao
import com.cold0.crier.social.room.dao.UserDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CrierModule {
	@Singleton
	@Provides
	fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
		.baseUrl("https://down.ovh/crier/")
		.addConverterFactory(GsonConverterFactory.create(gson))
		.build()

	@Provides
	fun provideGson(): Gson = GsonBuilder().create()

	@Provides
	fun providePostService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)

	@Singleton
	@Provides
	fun providePostRemoteDataSource(postService: PostService) = PostRemoteDataSource(postService)

	@Singleton
	@Provides
	fun provideDatabase(@ApplicationContext appContext: Context) = CrierDatabase.getDatabase(appContext)

	@Singleton
	@Provides
	fun providePostDao(db: CrierDatabase) = db.postDao()

	@Singleton
	@Provides
	fun provideUserDao(db: CrierDatabase) = db.userDao()

	@Singleton
	@Provides
	fun provideRepository(
		remoteDataSource: PostRemoteDataSource,
		postLocalDataSource: PostDao,
		userLocalDataSource: UserDao
	) =
		PostRepository(remoteDataSource, postLocalDataSource, userLocalDataSource)
}