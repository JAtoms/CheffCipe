package com.dtechatoms.cheffcipe.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dtechatoms.cheffcipe.adapters.CategoryRecyclerViewAdapter
import com.dtechatoms.cheffcipe.adapters.HomeAllMealRecyclerViewAdapter
import com.dtechatoms.cheffcipe.domain.CategoryModel
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel

/**
 * Created by Joshua Nwokoye (Atoms) on 7/22/2020.
 */

@BindingAdapter("homeCategoriesListData")
fun categoryBindRecyclerView(recyclerView: RecyclerView, categoryClass: List<CategoryModel>?) {
    val adapter = recyclerView.adapter as CategoryRecyclerViewAdapter
    adapter.submitList(categoryClass)
}

@BindingAdapter("homeAllMealsListData")
fun allFoodsBindRecyclerView(recyclerView: RecyclerView, foodsByNameModel: List<FoodsByNameModel>?) {
    val adapter = recyclerView.adapter as HomeAllMealRecyclerViewAdapter
    adapter.submitList(foodsByNameModel)
}

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}
