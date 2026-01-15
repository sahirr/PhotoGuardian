package com.photoguardian.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.photoguardian.data.local.dao.PersonDao
import com.photoguardian.data.local.dao.SecuredPhotoDao
import com.photoguardian.data.local.entity.PersonEntity
import com.photoguardian.data.local.entity.SecuredPhotoEntity
import com.photoguardian.data.local.util.Converters

@Database(
    entities = [PersonEntity::class, SecuredPhotoEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun securedPhotoDao(): SecuredPhotoDao
}