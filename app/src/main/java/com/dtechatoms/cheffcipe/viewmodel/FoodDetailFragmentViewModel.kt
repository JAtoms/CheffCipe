package com.dtechatoms.cheffcipe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dtechatoms.cheffcipe.database.MealDataBase
import com.dtechatoms.cheffcipe.database.asAllRecipeDomainModel
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel
import com.dtechatoms.cheffcipe.domain.MealRecipeRepository
import kotlinx.coroutines.*

class FoodDetailFragmentViewModel(foodID: String, application: Application) :
    AndroidViewModel(application) {

    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val refreshFoodID = foodID

    private val database = MealDataBase.getInstance(application)
    private var mealRecipeRepository = MealRecipeRepository(database)

    private val _selectedFood = MutableLiveData<FoodsByNameModel>()
    val selectedFood: LiveData<FoodsByNameModel>
        get() = _selectedFood

    private val _isDataResultNull = MutableLiveData<Boolean>()
    val isDataResultNull: LiveData<Boolean>
        get() = _isDataResultNull

    private val _similarFoodDataCategory = MutableLiveData<List<FoodsByNameModel>>()
    private val similarFoodDataCategory : LiveData<List<FoodsByNameModel>>
    get() = _similarFoodDataCategory

    init {

        uiScope.launch {

            // Look for food ID in database
            val dataResult = fetchDetails(foodID)

            // If food ID is not found in database, we request for it via network
            if (dataResult == null) {
                requestForItFromNetwork(foodID)
            } else {
                _selectedFood.value = dataResult
                _isDataResultNull.value = false
                val allFoods: LiveData<List<FoodsByNameModel>> =
                    Transformations.map(database.recipeDao.getSpecificMealCategory(dataResult.strCategory)) {
                        it.asAllRecipeDomainModel()
                    }
              //  _similarFoodDataCategory.value = allFoods
            }

        }

    }

    private fun requestForItFromNetwork(foodID: String): Boolean {
        var foodIdWithResponse = false
        uiScope.launch {
            foodIdWithResponse = mealRecipeRepository.fetchFoodByID(foodID)
            if (foodIdWithResponse) {
                val dataResult = fetchDetails(foodID)
                _selectedFood.value = dataResult
                _isDataResultNull.value = false
               // _similarFoodDataCategory.value = dataResult.strCategory
            }
        }
        return foodIdWithResponse
    }

    private suspend fun fetchDetails(foodID: String): FoodsByNameModel {
        return withContext(Dispatchers.IO) {
            var foodDetailID = database.recipeDao.getSpecificMeal(foodID)
            foodDetailID
        }
    }

    fun refresh(): Boolean {
        var networkRefresh = false
        uiScope.launch {

            // Look for food ID in database
            val dataResult = fetchDetails(refreshFoodID)

            // If food ID is not found in database, we request for it via network
            if (dataResult == null) {
                networkRefresh = requestForItFromNetwork(refreshFoodID)
            } else {
                _selectedFood.value = dataResult
                _isDataResultNull.value = false
            }
        }
        return networkRefresh
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}
