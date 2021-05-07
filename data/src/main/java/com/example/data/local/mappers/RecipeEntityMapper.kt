package com.example.data.local.mappers

typealias RecipeModel = com.example.domain.models.Recipe
typealias RecipeEntity = com.example.data.local.entities.Recipe

object RecipeEntityMapper {

    fun toRecipeModel(recipeEntity: RecipeEntity) =
        RecipeModel(
            recipeEntity.id,
            recipeEntity.title,
            recipeEntity.description,
            recipeEntity.isFavorite,
            recipeEntity.imageUrl,
            recipeEntity.numberOfLikes,
            recipeEntity.isVegan,
            readyInMinutes = recipeEntity.timeForReady.toInt()
        )

    fun toRecipeEntity(recipeModel: RecipeModel) =
        RecipeEntity(
            recipeModel.id,
            recipeModel.title,
            recipeModel.description,
            recipeModel.imageUrl,
            recipeModel.aggregateLikes,
            recipeModel.readyInMinutes.toLong(),
            recipeModel.isVegan,
            recipeModel.isFavorite
        )
}
