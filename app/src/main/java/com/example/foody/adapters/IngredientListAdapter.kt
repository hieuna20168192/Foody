package com.example.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.Ingredient
import com.example.foody.databinding.IngredientItemBinding
import com.example.foody.ui.base.BaseViewHolder

class IngredientListAdapter :
    ListAdapter<Ingredient, IngredientListAdapter.ViewHolder>(IngredientDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = getItem(position)
        holder.bind(ingredient)
    }

    class ViewHolder private constructor(private val binding: IngredientItemBinding) :
        BaseViewHolder(binding.root) {
        fun bind(ingredient: Ingredient) {
            binding.ingredient = ingredient
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object IngredientDiff : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient) =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient) =
            oldItem == newItem
    }
}
