package com.dtechatoms.cheffcipe.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dtechatoms.cheffcipe.adapters.HomeAllMealRecyclerViewAdapter
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel

/**
 * Created by Joshua Nwokoye (Atoms) on 7/22/2020.
 */

@BindingAdapter("homeAllMealsListData")
fun bindRecyClerView(recyclerView: RecyclerView, foodsByNameModel: List<FoodsByNameModel>?) {
    val adapter = recyclerView.adapter as HomeAllMealRecyclerViewAdapter
    adapter.submitList(foodsByNameModel)
}