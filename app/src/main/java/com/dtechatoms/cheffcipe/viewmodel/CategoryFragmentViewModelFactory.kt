package com.dtechatoms.cheffcipe.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dtechatoms.cheffcipe.domain.CategoryModel
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel

/**
 * Created by Joshua Nwokoye (Atoms) on 7/30/2020.
 */

class CategoryFragmentViewModelFactory(
    private val categoryModel: CategoryModel,
    val context: Context, val application: Application
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesFragmentViewModel::class.java)) {
            return CategoriesFragmentViewModel(categoryModel, context, application) as T
        }
        throw IllegalArgumentException("Unable to construct viewModel from FoodDetailFragmentViewModel")
    }
}