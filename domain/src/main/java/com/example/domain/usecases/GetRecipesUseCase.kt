package com.example.domain.usecases

import com.example.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val recipesRepository: RecipeRepository) {
    operator fun invoke() = recipesRepository.getRecipes()
    suspend fun fetchRecipesCache(query: HashMap<String, String>) = recipesRepository.fetchRecipesCache(query)
    suspend fun fetchRecipe(recipeKey: String) = recipesRepository.fetchRecipe(recipeKey)
}
