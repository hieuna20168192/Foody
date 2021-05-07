package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.dao.RecipeDao
import com.example.data.local.db.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideRecipeDao(database: RecipeDatabase): RecipeDao {
        return database.recipeDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): RecipeDatabase =
        Room.databaseBuilder(
            appContext,
            RecipeDatabase::class.java,
            "recipe_database"
        ).build()
}
