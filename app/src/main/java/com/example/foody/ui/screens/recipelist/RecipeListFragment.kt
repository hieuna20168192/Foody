package com.example.foody.ui.screens.recipelist

import android.graphics.Typeface
import android.view.Menu
import android.view.MenuInflater
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.foody.R
import com.example.foody.adapters.RecipeClickListener
import com.example.foody.adapters.RecipeListAdapter
import com.example.foody.databinding.FragmentRecipeListBinding
import com.example.foody.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment : BaseFragment<FragmentRecipeListBinding>(),
    SearchView.OnQueryTextListener {
    override val layoutId: Int
        get() = R.layout.fragment_recipe_list

    private val viewModel: RecipeViewModel by viewModels()

    private lateinit var adapter: RecipeListAdapter

    override fun initComponents() {
        setHasOptionsMenu(true)
        viewBinding.viewModel = viewModel
    }

    override fun initListeners() {
        onRecipeItem()
        onRecipeRefresh()
        onRecipeLoadMore()
        onNavigateRecipeDetail()
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

    private fun onRecipeItem() {
        adapter = RecipeListAdapter(RecipeClickListener { recipe ->
            viewModel.onRecipeItemClicked(recipe.id)
        })
        viewBinding.rclRecipeList.adapter = adapter
    }

    private fun onRecipeRefresh() {
        viewBinding.swipeRefreshList.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun onRecipeLoadMore() {
        viewBinding.rclRecipeList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == SCROLL_STATE_SETTLING &&
                    !recyclerView.canScrollVertically(1)
                ) {
                    viewModel.loadMore()
                }
            }
        })
    }

    private fun onNavigateRecipeDetail() {
        viewModel.navigateToRecipeDetail.observe(viewLifecycleOwner, { recipeId ->
            recipeId?.let {
                this.findNavController().navigate(
                    RecipeListFragmentDirections
                        .actionRecipeListDestToRecipeDetailDest(recipeId)
                )
                viewModel.onRecipeDetailNavigated()
            }
        })
    }

}
