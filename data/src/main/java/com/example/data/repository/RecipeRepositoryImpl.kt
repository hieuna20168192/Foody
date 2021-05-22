package com.example.data.repository

import com.example.data.local.dao.RecipeDao
import com.example.data.local.mappers.RecipeEntityMapper
import com.example.data.remote.api.FoodApiServices
import com.example.data.remote.mappers.RecipeApiMapper
import com.example.domain.common.Result
import com.example.domain.models.Recipe
import com.example.domain.repository.RecipeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import java.lang.Exception

@ExperimentalCoroutinesApi
class RecipeRepositoryImpl(
    private val recipeDao: RecipeDao,
    private val recipeApiServices: FoodApiServices,
) : RecipeRepository {

    override fun getRecipes(): Flow<List<Recipe>> =
        recipeDao.getRecipes().mapLatest { entities ->
            entities.map { entity -> RecipeEntityMapper.toRecipeModel(entity) }
        }

    override suspend fun fetchRecipesCache(query: HashMap<String, String>): Result<List<Recipe>> {
        return try {
            val response = recipeApiServices.getRecipes(query)
            val recipeModels = RecipeApiMapper.toRecipeModel(response)
            val recipeEntities = recipeModels.map { recipeModel ->
                RecipeEntityMapper.toRecipeEntity(recipeModel)
            }.toTypedArray()
            // Now, we cache the list into local storage.
            recipeDao.insertRecipe(*recipeEntities)
            Result.Success(recipeModels)
        } catch (e: Exception) {
            Result.Failure(Throwable(e))
        }
    }
}
