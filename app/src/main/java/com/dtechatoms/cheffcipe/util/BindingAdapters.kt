package com.dtechatoms.cheffcipe.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dtechatoms.cheffcipe.adapters.ListOfCategoryRecyclerViewAdapter
import com.dtechatoms.cheffcipe.adapters.ListOfMealsRecyclerViewAdapter
import com.dtechatoms.cheffcipe.domain.CategoryModel
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel

/**
 * Created by Joshua Nwokoye (Atoms) on 7/22/2020.
 */

// For Home Fragment
@BindingAdapter("homeCategoriesListData")
fun homeCategoryBindRecyclerView(recyclerView: RecyclerView, categoryModel: List<CategoryModel>?) {
    val adapter = recyclerView.adapter as ListOfCategoryRecyclerViewAdapter
    adapter.submitList(categoryModel)
}

@BindingAdapter("homeAllMealsListData")
fun allFoodsBindRecyclerView(recyclerView: RecyclerView, foodsByNameModel: List<FoodsByNameModel>?) {
    val adapter = recyclerView.adapter as ListOfMealsRecyclerViewAdapter
    adapter.submitList(foodsByNameModel)
}




/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}
