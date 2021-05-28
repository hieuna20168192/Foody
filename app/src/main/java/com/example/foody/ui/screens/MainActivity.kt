package com.example.foody.ui.screens

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foody.R
import com.example.foody.databinding.ActivityMainBinding
import com.example.foody.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    init {
        themeId = R.style.Theme_Foody
    }

    override fun initComponents() {
        setUpNavController()
        setUpAppBarConfig()
        setUpActionBar()
        setupBottomNav()
        onDestinationChange()
    }

    override fun initListeners() = Unit

    private fun setUpNavController() {
        navController = findNavController(R.id.navHostFragment)
    }

    private fun setUpAppBarConfig() {
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.recipeListDest, R.id.favoriteDest, R.id.jokeDest)
        )
    }

    private fun setUpActionBar() {
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )
    }

    private fun setupBottomNav() {
        viewBinding.bottomNav.setupWithNavController(navController)
    }

    private fun onDestinationChange() {
        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            val toolbar = supportActionBar ?: return@addOnDestinationChangedListener
            when (destination.id) {
                R.id.recipeListDest -> {
                    toolbar.setDisplayShowTitleEnabled(false)
                    viewBinding.bottomNav.visibility = View.VISIBLE
                }
                R.id.favoriteDest -> {
                    toolbar.setDisplayShowTitleEnabled(true)
                    viewBinding.bottomNav.visibility = View.VISIBLE
                }
                R.id.jokeDest -> {
                    toolbar.setDisplayShowTitleEnabled(true)
                    viewBinding.bottomNav.visibility = View.VISIBLE
                }
                else -> {
                    toolbar.setDisplayShowTitleEnabled(true)
                    viewBinding.bottomNav.visibility = View.GONE
                }
            }
        }
    }

}
