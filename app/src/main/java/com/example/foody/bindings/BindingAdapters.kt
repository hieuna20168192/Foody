package com.example.foody.bindings

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.domain.models.Recipe
import com.example.foody.R
import com.example.foody.adapters.RecipeListAdapter
import com.example.foody.ui.base.VMStatus

@BindingAdapter("recipeList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Recipe>?) {
    val adapter = recyclerView.adapter as RecipeListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(it)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("numToText")
fun bindText(textView: TextView, num: Int?) {
    num?.let {
        textView.text = num.toString()
    }
}

@BindingAdapter("numToMinute")
fun bindMinute(textView: TextView, num: Int?) {
    num?.let {
        textView.text = "$num'"
    }
}

@BindingAdapter("isVegan")
fun setVeganTint(imgView: ImageView, vegan: Boolean?) {
    vegan?.let {
        val color =
            if (it) ContextCompat.getColor(imgView.context, R.color.secondaryDarkColor) else return
        imgView.setColorFilter(color, android.graphics.PorterDuff.Mode.MULTIPLY)
    }
}

@BindingAdapter("recipesApiStatus")
fun bindStatus(
    statusImgView: ImageView,
    status: VMStatus?
) {
    when (status) {
        VMStatus.LOADING -> {
            statusImgView.visibility = View.VISIBLE
            statusImgView.setImageResource(R.drawable.loading_animation)
        }
        VMStatus.ERROR -> {
            statusImgView.visibility = View.VISIBLE
            statusImgView.setImageResource(R.drawable.ic_connection_error)
        }
        VMStatus.IS_EMPTY -> {
            statusImgView.visibility = View.VISIBLE
            statusImgView.setImageResource(R.drawable.ic_empty_icon)
        }
        VMStatus.DONE -> {
            statusImgView.visibility = View.GONE
        }
    }
}
