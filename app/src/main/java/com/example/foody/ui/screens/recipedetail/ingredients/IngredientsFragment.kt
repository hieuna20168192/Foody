package com.example.foody.ui.screens.recipedetail.ingredients

import androidx.fragment.app.activityViewModels
import com.example.foody.R
import com.example.foody.adapters.IngredientListAdapter
import com.example.foody.databinding.FragmentIngredientsBinding
import com.example.foody.ui.base.BaseFragment
import com.example.foody.ui.screens.recipedetail.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientsFragment : BaseFragment<FragmentIngredientsBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_ingredients
    private val viewModel: RecipeDetailViewModel by activityViewModels()
    private lateinit var ingredientAdapter: IngredientListAdapter

    override fun initComponents() {
        setHasOptionsMenu(true)
        viewBinding.viewModel = viewModel
        ingredientAdapter = IngredientListAdapter()
        setIngredientAdapter()
    }

    private fun setIngredientAdapter() {
        viewBinding.rclIngredients.adapter = ingredientAdapter
    }

    override fun initListeners() = Unit

    companion object {
        const val INGREDIENT_TAB = "Ingredients"
    }
}
