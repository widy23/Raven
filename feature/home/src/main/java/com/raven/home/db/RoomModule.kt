package com.raven.home.db

import android.content.Context
import androidx.room.Room
import com.raven.home.presentation.Utils.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, NewsDB::class.java,DATABASE_NAME)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
    @Provides
    fun providerDao(db :NewsDB)= db.ravenNews()

    @Provides
    fun providesNews()= NewsModelDB()
}