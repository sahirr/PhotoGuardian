package com.photoguardian.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    /**
     * Serialized list of float embeddings (128 dimensions).
     * Stored as a JSON string or byte array depending on converter.
     * This represents the "average" face embedding for the person.
     */
    @ColumnInfo(name = "embedding_data")
    val embeddingData: String, 

    @ColumnInfo(name = "thumbnail_path")
    val thumbnailPath: String
)