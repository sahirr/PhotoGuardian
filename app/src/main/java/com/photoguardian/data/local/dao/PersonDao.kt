package com.photoguardian.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.photoguardian.data.local.entity.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM persons ORDER BY created_at DESC")
    fun getAllPersons(): Flow<List<PersonEntity>>

    @Query("SELECT * FROM persons WHERE id = :id")
    suspend fun getPersonById(id: Long): PersonEntity?

    @Insert(onConflict = OnConflict Strategy.REPLACE)
    suspend fun insertPerson(person: PersonEntity): Long

    @Delete
    suspend fun deletePerson(person: PersonEntity)
}

