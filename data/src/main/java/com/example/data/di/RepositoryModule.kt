package com.example.data.di

import com.example.data.local.dao.RecipeDao
import com.example.data.remote.api.FoodApiServices
import com.example.data.repository.RecipeRepositoryImpl
import com.example.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFoodRepository(
        recipeDao: RecipeDao,
        recipeApiServices: FoodApiServices
    ): RecipeRepository =
        RecipeRepositoryImpl(recipeDao, recipeApiServices)
}
