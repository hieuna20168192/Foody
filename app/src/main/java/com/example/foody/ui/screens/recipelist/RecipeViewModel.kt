package com.example.foody.ui.screens.recipelist

import androidx.lifecycle.*
import com.example.data.common.Constants.DEFAULT_DIET_TYPE
import com.example.data.common.Constants.DEFAULT_MEAL_TYPE
import com.example.data.common.Constants.DEFAULT_RECIPES_NUMBER
import com.example.data.common.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.example.data.common.Constants.QUERY_API_KEY
import com.example.data.common.Constants.QUERY_DIET
import com.example.data.common.Constants.QUERY_FILL_INGREDIENTS
import com.example.data.common.Constants.QUERY_NUMBER
import com.example.data.common.Constants.QUERY_TYPE
import com.example.domain.common.Result
import com.example.domain.models.Recipe
import com.example.domain.usecases.GetRecipesUseCase
import com.example.foody.BuildConfig.API_KEY
import com.example.foody.ui.base.BaseViewModel
import com.example.foody.ui.base.VMStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val useCase: GetRecipesUseCase
) : BaseViewModel() {

    private val queryMap: HashMap<String, String> = HashMap()

    private val _recipeList = MutableLiveData<List<Recipe>>()
    val recipeList: LiveData<List<Recipe>>
        get() = _recipeList

    private val _errorMsg = MutableLiveData<String?>()
    val errorMsg: LiveData<String?>
        get() = _errorMsg

    init {
        applyQueries()
        fetchRecipes()
    }

    private fun fetchRecipes() {
        _status.value = VMStatus.LOADING
        viewModelScope.launch {
            when (val response = useCase.fetchRecipesCache(queryMap)) {
                is Result.Success -> {
                    _errorMsg.value = null
                    val recipes = response.data
                    if (recipes.isNotEmpty()) {
                        _recipeList.postValue(recipes)
                        _status.value = VMStatus.DONE
                    } else {
                        _status.value = VMStatus.IS_EMPTY
                    }
                }
                is Result.Failure -> {
                    _status.value = VMStatus.ERROR
                    _errorMsg.value = response.t.toString()
                }
            }
        }
    }

    private fun applyQueries() {
        queryMap[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queryMap[QUERY_TYPE] = DEFAULT_MEAL_TYPE
        queryMap[QUERY_DIET] = DEFAULT_DIET_TYPE
        queryMap[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queryMap[QUERY_FILL_INGREDIENTS] = "true"
        queryMap[QUERY_API_KEY] = API_KEY
    }
}
