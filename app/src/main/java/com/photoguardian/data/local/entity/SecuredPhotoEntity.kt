package com.photoguardian.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "secured_photos",
    foreignKeys = [
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["id"],
            childColumns = ["person_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SecuredPhotoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "person_id", index = true)
    val personId: Long,

    @ColumnInfo(name = "original_uri")
    val originalUri: String,

    @ColumnInfo(name = "encrypted_file_path")
    val encryptedFilePath: String,

    @ColumnInfo(name = "original_hash")
    val originalHash: String, // SHA-256 for integrity check

    @ColumnInfo(name = "secured_at")
    val securedAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "is_synced")
    val isSynced: Boolean = false,
    
    @ColumnInfo(name = "drive_file_id")
    val driveFileId: String? = null
)