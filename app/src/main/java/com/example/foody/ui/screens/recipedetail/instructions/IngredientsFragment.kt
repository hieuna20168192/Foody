package com.example.foody.ui.screens.recipedetail.instructions

import com.example.foody.R
import com.example.foody.databinding.FragmentIngredientsBinding
import com.example.foody.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientsFragment : BaseFragment<FragmentIngredientsBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_ingredients

    override fun initComponents() = Unit

    override fun initListeners() = Unit

    companion object {
        const val INGREDIENT_TAB = "Ingredients"
    }
}
