package com.dtechatoms.cheffcipe.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dtechatoms.cheffcipe.database.MealDataBase
import com.dtechatoms.cheffcipe.database.asSpecificCategoryDomainModel
import com.dtechatoms.cheffcipe.domain.CategoryModel
import com.dtechatoms.cheffcipe.domain.FoodsByCategoryModel
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel
import com.dtechatoms.cheffcipe.domain.MealRecipeRepository
import kotlinx.coroutines.*

class HomeFragmentViewModel(val context: Context, application: Application) :
    AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val database = MealDataBase.getInstance(application)
    private val mealRecipeRepository = MealRecipeRepository(database)

    // Navigate to selected food
    private val _navigateToSelectedFood = MutableLiveData<FoodsByNameModel>()
    val navigateToSelectedFood : LiveData<FoodsByNameModel>
        get() = _navigateToSelectedFood

    // Navigate to selected category
    private val _navigateToSelectedCategory = MutableLiveData<CategoryModel>()
    val navigateToSelectedCategory : LiveData<CategoryModel>
        get() = _navigateToSelectedCategory

    // List categories of meals
    private val _categoryList = mealRecipeRepository.listCategory
    val categoryList : LiveData<List<CategoryModel>>
    get() = _categoryList

    // List different meals on the home fragment
    private val _allDataBaseMeals = mealRecipeRepository.allFoods
    val homeRecipes: LiveData<List<FoodsByNameModel>>
        get() = _allDataBaseMeals

    private val _shimmerCategory = MutableLiveData<Boolean>()
    val shimmerCategory : LiveData<Boolean>
    get() = _shimmerCategory


    init {
        uiScope.launch {
            mealRecipeRepository.fetchSpecifiedFood("cake")
            mealRecipeRepository.fetchSpecifiedFood("soup")
            mealRecipeRepository.fetchSpecifiedFood("salmon")
            mealRecipeRepository.fetchSpecifiedFood("avocado")
            mealRecipeRepository.fetchSpecifiedFood("egg")
            mealRecipeRepository.fetchSpecifiedFood("apple")
            mealRecipeRepository.fetchSpecifiedFood("arrabiata")
        }
    }


    fun searchMeal() {
        //TODO check network status
        // TODO if no network, update searchError

        uiScope.launch {
            _shimmerCategory.value = mealRecipeRepository.fetchSpecifiedFood("egg")
        }


    }

    fun navigateToPropertiesDetail(foodsByNameModel: FoodsByNameModel){
        _navigateToSelectedFood.value = foodsByNameModel
    }

    fun displayPropertiesDetailCompleted(){
        _navigateToSelectedFood.value = null
    }

    fun navigateToCategories(categoryModel: CategoryModel){
       _navigateToSelectedCategory.value = categoryModel
    }

    fun navigateToCategoriesCompleted(){
        _navigateToSelectedCategory.value = null
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
