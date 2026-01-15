package com.photoguardian.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.photoguardian.data.local.entity.SecuredPhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SecuredPhotoDao {
    @Query("SELECT * FROM secured_photos WHERE person_id = :personId ORDER BY secured_at DESC")
    fun getPhotosForPerson(personId: Long): Flow<List<SecuredPhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: SecuredPhotoEntity): Long

    @Delete
    suspend fun deletePhoto(photo: SecuredPhotoEntity)
}