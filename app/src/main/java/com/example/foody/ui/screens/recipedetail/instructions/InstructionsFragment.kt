package com.example.foody.ui.screens.recipedetail.instructions

import com.example.foody.R
import com.example.foody.databinding.FragmentInstructionsBinding
import com.example.foody.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstructionsFragment : BaseFragment<FragmentInstructionsBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_instructions

    override fun initComponents() = Unit

    override fun initListeners() = Unit

    companion object {
        const val INSTRUCTION_TAB = "Instructions"
    }
}
