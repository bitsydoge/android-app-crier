package com.cold0.crier.social.room.converter

import androidx.room.TypeConverter
import com.cold0.crier.social.model.ImageHolder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.Collections

object ImageHolderConverter {
	private var gson: Gson = Gson()

	// ----------------------
	// List
	// ----------------------
	@TypeConverter
	fun toImageHolderist(data: String?): ImageHolder {
		if (data == null) {
			return ImageHolder()
		}
		val listType: Type = object : TypeToken<ImageHolder?>() {}.type
		return gson.fromJson(data, listType)
	}

	@TypeConverter
	fun fromImageHolder(someObjects: ImageHolder?): String {
		return gson.toJson(someObjects)
	}

	// ----------------------
	// List
	// ----------------------
	@TypeConverter
	fun toImageHolderList(data: String?): List<ImageHolder> {
		if (data == null) {
			return Collections.emptyList()
		}
		val listType: Type = object : TypeToken<List<ImageHolder?>?>() {}.type
		return gson.fromJson(data, listType)
	}

	@TypeConverter
	fun fromImageHolderList(someObjects: List<ImageHolder>?): String {
		return gson.toJson(someObjects)
	}
}