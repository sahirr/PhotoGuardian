package com.photoguardian.data.local.util

import androidx.room.TypeConverter

/**
 * Type converters to allow Room to store complex data types.
 * For this app, we simply handle the embedding data (List<Float>) 
 * by converting it to/from a String.
 */
class Converters {
    @TypeConverter
    fun fromFloatList(value: List<Float>?): String? {
        return value?.joinToString(",")
    }

    @TypeConverter
    fun toFloatList(value: String?): List<Float>? {
        return value?.split(",")?.mapNotNull { 
            try {
                it.toFloat()
            } catch (e: NumberFormatException) {
                null
            }
        }
    }
}