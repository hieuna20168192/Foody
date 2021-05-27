package com.example.data.remote.api

import com.example.data.remote.response.Food
import com.example.data.remote.response.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface FoodApiServices {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): RecipeResponse

    @GET("/recipes/{recipeKey}/information")
    suspend fun getRecipe(@Path("recipeKey") recipeKey: String): Food
}
