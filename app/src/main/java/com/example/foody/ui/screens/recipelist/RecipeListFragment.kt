package com.example.foody.ui.screens.recipelist

import android.graphics.Typeface
import android.view.Menu
import android.view.MenuInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.foody.R
import com.example.foody.adapters.RecipeClickListener
import com.example.foody.adapters.RecipeListAdapter
import com.example.foody.databinding.FragmentRecipeListBinding
import com.example.foody.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : BaseFragment<FragmentRecipeListBinding>(),
    SearchView.OnQueryTextListener {
    override val layoutId: Int
        get() = R.layout.fragment_recipe_list

    private val viewModel: RecipeViewModel by viewModels()

    override fun initComponents() {
        setHasOptionsMenu(true)
        viewBinding.viewModel = viewModel
    }

    override fun initListeners() {
        onListAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipe_menu, menu)
        val searchMenuItem = menu.findItem(R.id.menuItemSearch)
        searchMenuItem.actionView?.apply {
            val searchEditText =
                (this as SearchView).findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            searchEditText.typeface = Typeface.DEFAULT
            isSubmitButtonEnabled = true
            setOnQueryTextListener(this@RecipeListFragment)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        // handle searching recipes from a backend by query here later
        return true
    }

    override fun onQueryTextChange(newText: String?) = false

    private fun onListAdapter() {
        val adapter = RecipeListAdapter(RecipeClickListener { recipe ->
            // Route to the recipe detail screen here.
            Toast.makeText(context, recipe.title, Toast.LENGTH_SHORT).show()
        })
        viewBinding.rclRecipeList.adapter = adapter
    }
}
