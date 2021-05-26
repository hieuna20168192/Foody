package com.example.foody.ui.screens.recipelist

import android.content.Context
import androidx.lifecycle.*
import com.example.data.common.Constants.DEFAULT_DIET_TYPE
import com.example.data.common.Constants.DEFAULT_LOAD_MORE_RECIPES_NUMBER
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
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ScrollDirection {
    UP, DOWN
}

@ExperimentalCoroutinesApi
@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val useCase: GetRecipesUseCase,
    @ApplicationContext context: Context
) : BaseViewModel() {

    private val queryMap: HashMap<String, String> = HashMap()

    private val internetState = ConnectionLiveData(context)

    private val _recipeList = MutableLiveData<List<Recipe?>>()
    val recipeList: LiveData<List<Recipe?>> =
        Transformations.switchMap(internetState) { state ->
            when {
                status.value == VMStatus.ERROR -> {
                    liveData {
                        emit(listOf<Recipe?>())
                    }
                }
                state == InternetState.DISCONNECTED -> {
                    cachedRecipeFlow.asLiveData()
                }
                else -> {
                    if (status.value == VMStatus.IS_EMPTY) {
                        fetchRecipes()
                    }
                    _recipeList
                }
            }
        }

    private val _errorMsg = MutableLiveData<String?>()
    val errorMsg: LiveData<String?>
        get() = _errorMsg

    private val _isRefreshing = MutableLiveData<Boolean?>()
    val isRefreshing: LiveData<Boolean?>
        get() = _isRefreshing

    private var isLoadMore = true

    private val _scrollDirection = MutableLiveData<ScrollDirection?>()
    val scrollDirection: LiveData<ScrollDirection?>
        get() = _scrollDirection

    private val cachedRecipeFlow =
        useCase.invoke()
            .onStart {
                _status.value = VMStatus.LOADING
            }
            .map {
                if (it.isEmpty()) {
                    _status.value = VMStatus.IS_EMPTY
                } else {
                    _status.value = VMStatus.DONE
                    _recipeList.value = it
                }
                it
            }
            .catch { e: Throwable ->
                _status.value = VMStatus.ERROR
                _errorMsg.value = e.toString()
            }

    init {
        applyDefaultQueries()
        _status.value = VMStatus.IS_EMPTY
        _scrollDirection.value = ScrollDirection.UP
    }

    fun loadMore() {
        if (!isLoadMore)
            return
        isLoadMore = false
        _scrollDirection.value = ScrollDirection.DOWN
        val currentQueryNum = queryMap[QUERY_NUMBER]?.toInt() ?: 0
        val loadMoreNum = DEFAULT_LOAD_MORE_RECIPES_NUMBER.toInt()
        val totalNum = currentQueryNum + loadMoreNum
        queryMap[QUERY_NUMBER] = totalNum.toString()
        val currentRecipes = recipeList.value?.toMutableList()?.apply {
            add(null)
        }
        _recipeList.value = currentRecipes?.toList()
        fetchRecipes()
    }

    fun refresh() {
        _scrollDirection.value = ScrollDirection.UP
        fetchRecipes()
    }

    fun enableLoadingMore() {
        isLoadMore = true
    }

    private fun fetchRecipes() {
        if (internetState.value == InternetState.DISCONNECTED) {
            _isRefreshing.value = false
            return
        }
        _status.value = VMStatus.LOADING
        viewModelScope.launch {
            when (val response = useCase.fetchRecipesCache(queryMap)) {
                is Result.Success -> {
                    val recipes = response.data
                    if (recipes.isNotEmpty()) {
                        _status.value = VMStatus.DONE
                        _recipeList.value = recipes
                    } else {
                        _status.value = VMStatus.IS_EMPTY
                    }
                }
                is Result.Failure -> {
                    _recipeList.value = listOf()
                    _status.value = VMStatus.ERROR
                    _errorMsg.value = response.t.toString()
                }
            }
            _isRefreshing.value = false
        }
    }

    private fun applyDefaultQueries() {
        queryMap.clear()
        queryMap[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queryMap[QUERY_TYPE] = DEFAULT_MEAL_TYPE
        queryMap[QUERY_DIET] = DEFAULT_DIET_TYPE
        queryMap[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queryMap[QUERY_FILL_INGREDIENTS] = "true"
        queryMap[QUERY_API_KEY] = API_KEY
    }
}
