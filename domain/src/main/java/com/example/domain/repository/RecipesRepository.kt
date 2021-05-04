package com.example.domain.repository

import com.example.domain.entities.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    fun getRecipes(): Flow<List<Recipe>>
    suspend fun fetchRecipesCache()
}
