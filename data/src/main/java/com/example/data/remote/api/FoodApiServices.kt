package com.example.data.remote.api

import com.example.data.remote.response.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodApiServices {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): RecipeResponse
}
