package com.example.foody.ui.screens.recipedetail.overview

import android.util.Log
import androidx.fragment.app.activityViewModels
import com.example.foody.R
import com.example.foody.databinding.FragmentOverviewBinding
import com.example.foody.ui.base.BaseFragment
import com.example.foody.ui.screens.recipedetail.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverViewFragment : BaseFragment<FragmentOverviewBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_overview

    private val viewModel: RecipeDetailViewModel by activityViewModels()

    override fun initComponents() {
        viewBinding.recipe = viewModel.recipe.value
    }

    override fun initListeners() {
        viewModel.recipe.observe(viewLifecycleOwner, { recipe ->
            Log.d("recipe", "${recipe.title}")
        })
    }
}
