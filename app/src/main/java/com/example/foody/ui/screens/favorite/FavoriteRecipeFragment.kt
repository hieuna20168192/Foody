package com.example.foody.ui.screens.favorite

import com.example.foody.R
import com.example.foody.databinding.FragmentFavoriteRecipeBinding
import com.example.foody.ui.base.BaseFragment

class FavoriteRecipeFragment :
    BaseFragment<FragmentFavoriteRecipeBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_favorite_recipe

    override fun initComponents() = Unit

    override fun initListeners() = Unit
}
