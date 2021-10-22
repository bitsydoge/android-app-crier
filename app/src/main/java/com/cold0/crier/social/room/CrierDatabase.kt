package com.cold0.crier.social.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cold0.crier.social.model.Post
import com.cold0.crier.social.model.User
import com.cold0.crier.social.room.converter.DateConverter
import com.cold0.crier.social.room.converter.ImageHolderConverter
import com.cold0.crier.social.room.converter.UUIDConverter
import com.cold0.crier.social.room.dao.PostDao
import com.cold0.crier.social.room.dao.UserDao

@Database(entities = [Post::class, User::class], version = 1, exportSchema = false)
@TypeConverters(UUIDConverter::class, DateConverter::class, ImageHolderConverter::class)
abstract class CrierDatabase : RoomDatabase() {

	abstract fun postDao(): PostDao
	abstract fun userDao(): UserDao

	companion object {
		@Volatile
		private var instance: CrierDatabase? = null

		fun getDatabase(context: Context): CrierDatabase =
			instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

		private fun buildDatabase(appContext: Context) =
			Room.databaseBuilder(appContext, CrierDatabase::class.java, "crier")
				.fallbackToDestructiveMigration()
				.build()
	}

}