package com.example.domain.usecases

import com.example.domain.repository.RecipeRepository

class GetRecipesUseCase (private val recipesRepository: RecipeRepository) {
    operator fun invoke() = recipesRepository.getRecipes()
    suspend fun fetchRecipesCache(query: Map<String, String>) = recipesRepository.fetchRecipesCache(query)
}
