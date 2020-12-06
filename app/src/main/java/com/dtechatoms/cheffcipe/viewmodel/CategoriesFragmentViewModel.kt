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
    private var mealRecipeRepository = MealRecipeRepository(database, categoryModel.strCategory)

    // Gets group of category items to be listed on the Category fragment
    private val _categoryList = MutableLiveData<CategoryModel>()
    val categoryListDetail: LiveData<CategoryModel>
        get() = _categoryList


    // Gets all the specified categories
    private var _specifiedCategory = mealRecipeRepository.categoriesWithContent
    val specifiedCategory : LiveData<List<FoodsByCategoryModel>>
    get() = _specifiedCategory

    // List categories of meals in bottomSheet
    private val _bottomSheetCategoryList = mealRecipeRepository.listCategory
    val categoryList : LiveData<List<CategoryModel>>
        get() = _bottomSheetCategoryList

    init {
        _categoryList.value = categoryModel
        uiScope.launch {
            // Gets the specified category from network and stores it in the database
            mealRecipeRepository.fetchSpecifiedCategory(categoryModel.strCategory)
        }
    }

    fun upChangeCategoryList(categoryModel: CategoryModel){
//        val categoriesWithContent : LiveData<List<FoodsByCategoryModel>> = Transformations
//            .map(database.recipeDao.getSpecificCategory(categoryModel.strCategory)){
//                it.asSpecificCategoryDomainModel()
//            }
      //c  _specifiedCategory = categoriesWithContent
        _categoryList.value = categoryModel

    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

}
