package com.dtechatoms.cheffcipe.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel
import java.lang.IllegalArgumentException

/**
 * Created by Joshua Nwokoye (Atoms) on 7/30/2020.
 */

class FoodDetailFragmentViewModelFactory(
    private val foodsByNameModel: FoodsByNameModel,
    private val application: Application
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodDetailFragmentViewModel::class.java)) {
            return FoodDetailFragmentViewModel(foodsByNameModel, application) as T
        }
        throw IllegalArgumentException("Unable to construct viewModel from FoodDetailFragmentViewModel")
    }
}