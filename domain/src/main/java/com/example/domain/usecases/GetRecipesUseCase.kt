package com.example.domain.usecases

import com.example.domain.repository.RecipesRepository

class GetRecipesUseCase (private val recipesRepository: RecipesRepository) {
    operator fun invoke() = recipesRepository.getRecipes()
    suspend fun fetchRecipesCache() = recipesRepository.fetchRecipesCache()
}
