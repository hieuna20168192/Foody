package com.example.data.remote.mappers

import com.example.data.BuildConfig.SMALL_IMAGE_URL
import com.example.data.remote.response.Food
import com.example.data.remote.response.RecipeResponse
import com.example.domain.models.Ingredient
import com.example.domain.models.Recipe

object RecipeApiMapper {

    fun toRecipeModel(recipeResponse: RecipeResponse): List<Recipe> =
        recipeResponse.results.map { food ->
            Recipe(
                food.recipeId.toString(),
                food.title,
                food.summary,
                false,
                food.image,
                food.aggregateLikes,
                food.vegan,
                food.vegetarian,
                food.veryHealthy,
                food.cheap,
                food.dairyFree,
                food.glutenFree,
                food.readyInMinutes,
                food.extendedIngredients?.map {
                    Ingredient(
                        it.name,
                        SMALL_IMAGE_URL + it.image,
                        it.original,
                        it.amount,
                        it.consistency,
                        it.unit
                    )
                }
            )
        }

    fun toRecipeModel(foodInfo: Food): Recipe =
        Recipe(
            foodInfo.recipeId.toString(),
            foodInfo.title,
            foodInfo.summary,
            false,
            foodInfo.image,
            foodInfo.aggregateLikes,
            foodInfo.vegan,
            foodInfo.vegetarian,
            foodInfo.veryHealthy,
            foodInfo.cheap,
            foodInfo.dairyFree,
            foodInfo.glutenFree,
            foodInfo.readyInMinutes,
            foodInfo.extendedIngredients?.map {
                Ingredient(
                    it.name,
                    SMALL_IMAGE_URL + it.image,
                    it.original,
                    it.amount,
                    it.consistency,
                    it.unit
                )
            }
        )
}
