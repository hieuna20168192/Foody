package com.example.foody.utils

import com.example.data.common.Constants
import com.example.data.common.Constants.IS_CHEAP
import com.example.data.common.Constants.IS_DAIRY_FREE
import com.example.data.common.Constants.IS_GLUTEN_FREE
import com.example.data.common.Constants.IS_HEALTHY
import com.example.data.common.Constants.IS_VEGAN
import com.example.data.common.Constants.IS_VEGETARIAN
import com.example.domain.models.Recipe

fun applyDefaultQueries(queryMap: HashMap<String, String>) {
    queryMap.clear()
    queryMap[Constants.QUERY_NUMBER] = Constants.DEFAULT_RECIPES_NUMBER
    queryMap[Constants.QUERY_TYPE] = Constants.DEFAULT_MEAL_TYPE
    queryMap[Constants.QUERY_DIET] = Constants.DEFAULT_DIET_TYPE
    queryMap[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
    queryMap[Constants.QUERY_FILL_INGREDIENTS] = "true"
}

fun Recipe.getDietTypes() =
    HashMap<String, Boolean>().apply {
        this[IS_VEGAN] = isVegan
        this[IS_VEGETARIAN] = isVegetarian
        this[IS_HEALTHY] = isHealthy
        this[IS_CHEAP] = isCheap
        this[IS_DAIRY_FREE] = isDairyFree
        this[IS_GLUTEN_FREE] = isGlutenFree
    }

