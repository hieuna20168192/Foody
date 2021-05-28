package com.example.domain.models

data class Recipe(
        val id: String,
        val title: String = "",
        val description: String = "",
        var isFavorite: Boolean = false,
        val imageUrl: String? = "",
        val aggregateLikes: Int = 0,
        val isVegan: Boolean = false,
        val isVegetarian: Boolean = false,
        val isHealthy: Boolean = false,
        val isCheap: Boolean = false,
        val isDairyFree: Boolean = false,
        val isGlutenFree: Boolean = false,
        val readyInMinutes: Int = 0,
        val ingredients: List<Ingredient>? = null
)
