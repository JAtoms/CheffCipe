package com.dtechatoms.cheffcipe.util

import android.app.Application
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dtechatoms.cheffcipe.R
import com.dtechatoms.cheffcipe.adapters.ListOfCategoryRecyclerViewAdapter
import com.dtechatoms.cheffcipe.adapters.ListOfMealsRecyclerViewAdapter
import com.dtechatoms.cheffcipe.adapters.catListOfMealsRecyclerViewAdapter
import com.dtechatoms.cheffcipe.domain.CategoryModel
import com.dtechatoms.cheffcipe.domain.FoodsByCategoryModel
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
fun allFoodsBindRecyclerView(
    recyclerView: RecyclerView, foodsByNameModel: List<FoodsByNameModel>?) {
    val adapter = recyclerView.adapter as ListOfMealsRecyclerViewAdapter
    adapter.submitList(foodsByNameModel)
}

// For Category Fragment
@BindingAdapter("allCatMealsListData")
fun allCatListRecyclerView(
    recyclerView: RecyclerView, foodsByCategoryModel: List<FoodsByCategoryModel>?) {
    val adapter = recyclerView.adapter as catListOfMealsRecyclerViewAdapter
    adapter.submitList(foodsByCategoryModel)
}

 // Binding adapter used to display images from URL using Glide
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).apply(
        RequestOptions()
            .placeholder(R.drawable.loading_animation)
    ).into(imageView)
}

// Binding adapter to set TextViews
@BindingAdapter("setTexts")
fun setTextViews(textView: TextView, string: String){
    textView.text = string
}

fun toasts(context:Context?,string: String){
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}
