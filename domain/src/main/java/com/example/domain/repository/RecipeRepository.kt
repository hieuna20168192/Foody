package com.example.domain.repository

import com.example.domain.common.Result
import com.example.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<List<Recipe>>
    suspend fun fetchRecipesCache(query: HashMap<String, String>): Result<List<Recipe>>
}
