package com.italo.myweather.di

import android.content.Context
import androidx.room.Room
import com.italo.myweather.dao.FavoriteCityDao
import com.italo.myweather.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FavoriteCityDBModule {

    @Provides
    fun provideFavoriteCityDao(appDatabase: AppDatabase): FavoriteCityDao {
        return appDatabase.favoriteCityDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, DB_NAME).build()
    }

    companion object {
        const val DB_NAME = "myweather_db"
    }
}
