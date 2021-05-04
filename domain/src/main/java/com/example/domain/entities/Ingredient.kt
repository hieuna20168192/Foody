package com.example.domain.entities

class Ingredient(
        val title: String = "",
        val imageUrl: String = "",
        val summary: String = "",
        val extendedIngredient: ExtendedIngredient?
)

class ExtendedIngredient(
        val amount: Double = 0.0,
        val consistency: String = "",
        val unit: String = ""
)
