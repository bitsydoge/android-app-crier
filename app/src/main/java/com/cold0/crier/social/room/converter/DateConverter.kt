package com.cold0.crier.social.room.converter

import androidx.room.TypeConverter
import java.util.*

object DateConverter {
	@TypeConverter
	fun timestampFromDate(date: Date): Long {
		return date.time
	}

	@TypeConverter
	fun dateFromTimestamp(timestamp: Long): Date {
		return Date(timestamp)
	}
}