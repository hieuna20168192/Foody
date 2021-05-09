package com.example.foody.ui.screens

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foody.R
import com.example.foody.databinding.ActivityMainBinding
import com.example.foody.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    init {
        themeId = R.style.Theme_Foody
    }

    override fun initComponents() {
        setupBottomNav()
    }

    override fun initListeners() = Unit

    private fun setupBottomNav() {
        viewBinding.bottomNav.setupWithNavController(findNavController(R.id.navHostFragment))
    }

}
