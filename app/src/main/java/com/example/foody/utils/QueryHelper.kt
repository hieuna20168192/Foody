package com.example.foody.utils

import com.example.data.common.Constants

fun applyDefaultQueries(queryMap: HashMap<String, String>) {
    queryMap.clear()
    queryMap[Constants.QUERY_NUMBER] = Constants.DEFAULT_RECIPES_NUMBER
    queryMap[Constants.QUERY_TYPE] = Constants.DEFAULT_MEAL_TYPE
    queryMap[Constants.QUERY_DIET] = Constants.DEFAULT_DIET_TYPE
    queryMap[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
    queryMap[Constants.QUERY_FILL_INGREDIENTS] = "true"
}
