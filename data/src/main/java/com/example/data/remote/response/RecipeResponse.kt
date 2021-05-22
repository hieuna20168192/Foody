package com.example.data.remote.response

import com.squareup.moshi.Json

data class RecipeResponse(
    val results: List<Food>
)

data class Food(
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val extendedIngredients: List<ExtendedIngredient>?,
    val glutenFree: Boolean,
    @Json(name = "id")
    val recipeId: Int,
    val image: String?,
    val readyInMinutes: Int,
    val sourceName: String?,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
)

data class ExtendedIngredient(
    val amount: Double?,
    val consistency: String?,
    val image: String?,
    val name: String?,
    val original: String?,
    val unit: String?
)
