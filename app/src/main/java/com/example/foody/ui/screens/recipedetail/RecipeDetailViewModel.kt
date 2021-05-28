package com.example.foody.ui.screens.recipedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Result
import com.example.domain.models.Ingredient
import com.example.domain.models.Recipe
import com.example.domain.usecases.GetRecipesUseCase
import com.example.foody.ui.base.BaseViewModel
import com.example.foody.ui.base.VMStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val useCase: GetRecipesUseCase,
) : BaseViewModel() {

    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe>
        get() = _recipe

    val ingredients: LiveData<List<Ingredient>> =
        Transformations.map(recipe) { recipe ->
            recipe.ingredients
        }

    fun fetchRecipeDetail(recipeKey: String) {
        viewModelScope.launch {
            _status.value = VMStatus.LOADING
            when (val response = useCase.fetchRecipe(recipeKey)) {
                is Result.Success -> {
                    _status.value = VMStatus.DONE
                    _recipe.value = response.data
                }
                is Result.Failure -> {
                    _status.value = VMStatus.ERROR
                }
            }
        }
    }
}
