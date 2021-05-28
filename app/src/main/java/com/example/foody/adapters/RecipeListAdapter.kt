package com.example.foody.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.Recipe
import com.example.foody.R
import com.example.foody.databinding.RecipeListItemBinding
import com.example.foody.ui.base.BaseViewHolder
import java.lang.IllegalArgumentException

private const val LOAD_MORE_TYPE = 0
private const val RECIPE_TYPE = 1
private const val UNKNOWN_TYPE = -1

class RecipeListAdapter(
    private val recipeClickListener: RecipeClickListener
) : ListAdapter<Recipe, BaseViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            RECIPE_TYPE -> RecipeViewHolder.from(parent)
            LOAD_MORE_TYPE -> LoadMoreViewHolder.from(parent)
            else -> throw IllegalArgumentException("ViewHolder Type is unknown!")
        }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val recipe = getItem(position)
        if (holder is RecipeViewHolder) {
            holder.bind(recipeClickListener, recipe)
        }
    }

    override fun getItemViewType(position: Int) =
        when (getItem(position)) {
            is Recipe -> RECIPE_TYPE
            null -> LOAD_MORE_TYPE
            else -> UNKNOWN_TYPE
        }

    companion object DiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) = oldItem === newItem

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) = oldItem == newItem
    }

    class RecipeViewHolder private constructor(private val binding: RecipeListItemBinding) :
        BaseViewHolder(binding.root) {
        fun bind(listener: RecipeClickListener, recipe: Recipe) {
            binding.recipe = recipe
            binding.clickListener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecipeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipeListItemBinding.inflate(layoutInflater, parent, false)
                return RecipeViewHolder(binding)
            }
        }
    }

    class LoadMoreViewHolder private constructor(itemView: View) :
        BaseViewHolder(itemView) {

        companion object {
            fun from(parent: ViewGroup): LoadMoreViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemView = layoutInflater.inflate(R.layout.load_more_item, parent, false)
                return LoadMoreViewHolder(itemView)
            }
        }
    }
}

class RecipeClickListener(val clickListener: (recipe: Recipe) -> Unit) {
    fun onClick(recipe: Recipe) = clickListener(recipe)
}
