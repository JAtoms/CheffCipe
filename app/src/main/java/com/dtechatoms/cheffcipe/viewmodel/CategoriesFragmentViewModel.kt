package com.dtechatoms.cheffcipe.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dtechatoms.cheffcipe.database.AllRecipesEntity
import com.dtechatoms.cheffcipe.database.MealDataBase
import com.dtechatoms.cheffcipe.database.asAllRecipeDomainModel
import com.dtechatoms.cheffcipe.domain.CategoryModel
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel
import com.dtechatoms.cheffcipe.domain.MealRecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CategoriesFragmentViewModel(
    categoryModel: CategoryModel, val context: Context,
    application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val database = MealDataBase.getInstance(application)
    private val mealRecipeRepository = MealRecipeRepository(database)

    private val _categoryList = MutableLiveData<CategoryModel>()
    val categoryListDetail: LiveData<CategoryModel>
        get() = _categoryList

    // Gets specified food category in the database
    private val _categoriesWithContent : LiveData<List<FoodsByNameModel>> = Transformations
        .map(database.recipeDao.getSpecificCategory(categoryModel.idCategory)){
        it.asAllRecipeDomainModel()
    }

    val categoriesWithContent: LiveData<List<FoodsByNameModel>>
        get() = _categoriesWithContent

    // List categories of meals
    val categoryList = mealRecipeRepository.listCategory

    init {
        _categoryList.value = categoryModel

        uiScope.launch {

            // Gets the specified category from network and stores it in the database
            mealRecipeRepository.fetchSpecifiedCategory(categoryModel.idCategory)
        }

    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

}
