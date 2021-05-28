package com.example.foody.ui.screens.recipedetail.overview

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import com.example.domain.models.Recipe
import com.example.foody.R
import com.example.foody.databinding.FragmentOverviewBinding
import com.example.foody.ui.base.BaseFragment
import com.example.foody.ui.screens.recipedetail.RecipeDetailViewModel
import com.example.foody.utils.getDietTypes
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverViewFragment : BaseFragment<FragmentOverviewBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_overview

    private val viewModel: RecipeDetailViewModel by activityViewModels()

    override fun initComponents() {
        viewBinding.viewModel = viewModel
    }

    override fun initListeners() {
        viewModel.recipe.observe(viewLifecycleOwner, { recipe ->
            bindDietTypeList(recipe)
        })
    }

    private fun bindDietTypeList(recipe: Recipe?) {
        recipe ?: return
        val chipGroup = viewBinding.cgTypes
        val inflater = LayoutInflater.from(chipGroup.context)
        val dietTypes = recipe.getDietTypes()
        val children = dietTypes.map { (diet, status) ->
            val chip = inflater.inflate(R.layout.diet_type, chipGroup, false) as Chip
            chip.text = diet
            chip.tag = diet
            chip.isChecked = status
            chip
        }
        chipGroup.removeAllViews()
        for (chip in children) {
            chipGroup.addView(chip)
        }
    }

    companion object {
        const val OVERVIEW_TAB = "Overview"
    }
}
