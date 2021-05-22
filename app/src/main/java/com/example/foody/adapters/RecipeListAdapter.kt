package com.example.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Recipe
import com.example.foody.databinding.RecipeListItemBinding

class RecipeListAdapter(
    private val recipeClickListener: RecipeClickListener
) : ListAdapter<Recipe, RecipeListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipeClickListener, recipe)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) = oldItem === newItem

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) = oldItem == newItem
    }

    class ViewHolder(private var binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: RecipeClickListener, recipe: Recipe) {
            binding.recipe = recipe
            binding.clickListener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipeListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class RecipeClickListener(val clickListener: (recipe: Recipe) -> Unit) {
    fun onClick(recipe: Recipe) = clickListener(recipe)
}
