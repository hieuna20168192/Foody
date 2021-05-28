package com.example.foody.ui.screens.recipedetail

import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.foody.R
import com.example.foody.adapters.RecipeDetailAdapter
import com.example.foody.databinding.FragmentRecipeDetailBinding
import com.example.foody.ui.base.BaseFragment
import com.example.foody.ui.screens.recipedetail.instructions.InstructionsFragment
import com.example.foody.ui.screens.recipedetail.ingredients.IngredientsFragment
import com.example.foody.ui.screens.recipedetail.overview.OverViewFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : BaseFragment<FragmentRecipeDetailBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_recipe_detail

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var recipeArgument: RecipeDetailFragmentArgs
    private val viewModel: RecipeDetailViewModel by activityViewModels()

    override fun initComponents() {
        setHasOptionsMenu(true)
        recipeArgument = RecipeDetailFragmentArgs.fromBundle(requireArguments())
        tabLayout = viewBinding.tabRecipeDetail
        viewPager = viewBinding.viewPagerRecipeDetail
        setPagerAdapter()
        setTabWithPager()
        retrieveRecipe()
    }

    private fun setTabWithPager() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> OverViewFragment.OVERVIEW_TAB
                1 -> IngredientsFragment.INGREDIENT_TAB
                2 -> InstructionsFragment.INSTRUCTION_TAB
                else -> throw IllegalStateException("Tab menu item is unknown!")
            }
        }.attach()
    }

    override fun initListeners() = Unit

    private fun setPagerAdapter() {
        val pagerAdapter = RecipeDetailAdapter(this)
        viewPager.adapter = pagerAdapter
    }

    private fun retrieveRecipe() {
        viewModel.fetchRecipeDetail(recipeArgument.recipeKey)
    }
}
