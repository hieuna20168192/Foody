package com.example.foody.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foody.ui.screens.recipedetail.ingredients.InstructionsFragment
import com.example.foody.ui.screens.recipedetail.instructions.IngredientsFragment
import com.example.foody.ui.screens.recipedetail.overview.OverViewFragment
import java.lang.IllegalArgumentException

private const val NUM_PAGES = 3

class RecipeDetailAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = NUM_PAGES

    override fun createFragment(position: Int) =
        when (position) {
            0 -> OverViewFragment()
            1 -> IngredientsFragment()
            2 -> InstructionsFragment()
            else -> throw IllegalArgumentException("The fragment is unknown!")
        }
}
