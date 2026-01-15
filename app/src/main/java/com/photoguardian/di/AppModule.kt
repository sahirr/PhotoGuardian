package com.photoguardian.di

import android.content.Context
import androidx.room.Room
import com.photoguardian.core.security.EncryptionManager
import com.photoguardian.data.local.AppDatabase
import com.photoguardian.data.local.dao.PersonDao
import com.photoguardian.data.local.dao.SecuredPhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "photo_guardian_db"
        )
            .fallbackToDestructiveMigration() // Handle migration properly in production
            .build()
    }

    @Provides
    @Singleton
    fun providePersonDao(database: AppDatabase): PersonDao {
        return database.personDao()
    }

    @Provides
    @Singleton
    fun provideSecuredPhotoDao(database: AppDatabase): SecuredPhotoDao {
        return database.securedPhotoDao()
    }

    @Provides
    @Singleton
    fun provideEncryptionManager(): EncryptionManager {
        return EncryptionManager()
    }
}