package com.dtechatoms.cheffcipe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel

class FoodDetailFragmentViewModel(foodsByNameModel: FoodsByNameModel, application: Application) :
    AndroidViewModel(application) {

    private val _selectedFood = MutableLiveData<FoodsByNameModel>()
    val selectedFood : LiveData<FoodsByNameModel>
    get() = _selectedFood

    init {
        _selectedFood.value = foodsByNameModel
    }
}
