package com.dtechatoms.cheffcipe.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dtechatoms.cheffcipe.R
import com.dtechatoms.cheffcipe.database.MealDataBase
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel
import com.dtechatoms.cheffcipe.domain.MealRecipeRepository
import com.dtechatoms.cheffcipe.network.*
import kotlinx.coroutines.*
import timber.log.Timber
import java.lang.Exception

class HomeFragmentViewModel(val context: Context, application: Application) :
    AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val database = MealDataBase.getInstance(application)
    private val mealRecipeRepository = MealRecipeRepository(database)

    val categoryList = mealRecipeRepository.listCategory
    val allDataBaseMeals = mealRecipeRepository.allFoods

    private val _homeRecipes = allDataBaseMeals
    val homeRecipes: LiveData<List<FoodsByNameModel>>
        get() = _homeRecipes

    private val _searchResultMeals = MutableLiveData<List<FoodsByName>>()
    val searchResultMeals: LiveData<List<FoodsByName>>
        get() = _searchResultMeals

    private val _searchError = MutableLiveData<String>()
    val searchError: LiveData<String>
        get() = _searchError

    private val _searchClicked = MutableLiveData<String>()
    val searchClicked: LiveData<String>
        get() = _searchClicked

    private var searchMade = false
    var searchValue: String? = null


    fun searchMeal() {
        //TODO check network status
        // TODO if no network, update searchError

        uiScope.launch {
            var errorGetter = "Just Error"
            if (searchValue.isNullOrBlank()) {
                _searchError.value = context.getString(R.string.empty_search_fields)
            } else {
                withContext(Dispatchers.IO) {
                    try {
                        val foodsByName = Network.mealService.searchByName("apple").await()
                      //  _searchResultMeals.value = foodsByName
                        // TODO Update search result to data base
                        searchMade = true

                    } catch (e: Exception) {
                        Timber.e(e)
                        errorGetter = e.toString()
                    }
                }
            }
            _searchError.value = errorGetter
        }
        if (searchMade) {
            searchResultMeals
            _searchClicked.value = "Done"
        }
    }


    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
