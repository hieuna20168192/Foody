package com.example.foody.ui.screens.recipedetail

import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.foody.R
import com.example.foody.adapters.RecipeDetailAdapter
import com.example.foody.databinding.FragmentRecipeDetailBinding
import com.example.foody.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : BaseFragment<FragmentRecipeDetailBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_recipe_detail

    private lateinit var viewPager: ViewPager2
    private lateinit var recipeArgument: RecipeDetailFragmentArgs
    private val viewModel: RecipeDetailViewModel by activityViewModels()

    override fun initComponents() {
        recipeArgument = RecipeDetailFragmentArgs.fromBundle(requireArguments())

        setPagerAdapter()
        retrieveRecipe()
    }

    override fun initListeners() = Unit

    private fun setPagerAdapter() {
        viewPager = viewBinding.viewPagerRecipeDetail
        val pagerAdapter = RecipeDetailAdapter(this)
        viewPager.adapter = pagerAdapter
    }

    private fun retrieveRecipe() {
        viewModel.fetchRecipeDetail(recipeArgument.recipeKey)
    }
}
