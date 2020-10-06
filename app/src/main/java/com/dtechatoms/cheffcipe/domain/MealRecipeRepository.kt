package com.dtechatoms.cheffcipe.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.RoomDatabase
import com.dtechatoms.cheffcipe.database.MealDataBase
import com.dtechatoms.cheffcipe.database.asAllCatDomainModel
import com.dtechatoms.cheffcipe.database.asAllRecipeDomainModel
import com.dtechatoms.cheffcipe.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

/**
 * Created by Joshua Nwokoye (Atoms) on 7/27/2020.
 */

class MealRecipeRepository(private val database: MealDataBase) {

    /**
     * Perform dataBase calls
     */

    // Gets list of category items
    val listCategory: LiveData<List<CategoryModel>> = Transformations.map(database.recipeDao.getListOfCategories()){
        it.asAllCatDomainModel()
    }

    // Gets all meal in the database
    val allFoods: LiveData<List<FoodsByNameModel>> = Transformations.map(database.recipeDao.getAllRecipes()){
        it.asAllRecipeDomainModel()
    }

    // Perform network calls
    suspend fun refreshCategories() {
        withContext(Dispatchers.IO){
            try {
                val listOfMeals = Network.mealService.getAllCategories().await()
                database.recipeDao.insertIntoCategories(*listOfMeals.asDataModel())
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

    }

    suspend fun fetchSpecifiedCategory(category: String) {
        withContext(Dispatchers.IO){
            try {
                val foodsByCategory = Network.mealService.searchByCategory(category).await()
                database.recipeDao.insertIntoSpecificCategories(*foodsByCategory.asDataModel())
                Log.e("AtomsLogs", "Beef")
            } catch (e: Exception) {
                Log.e("AtomsLogs", "No beef $e.toString()")
                Timber.e(e)
            }
        }
    }

    suspend fun fetchSpecifiedFood(name: String): Boolean {

        var boolean = false

         withContext(Dispatchers.IO){
             boolean = try {
                 val foodsByName = Network.mealService.searchByName(name).await()
                 database.recipeDao.insertIntoAllRecipes(*foodsByName.asDatabaseModel())
                 true
             } catch (e: Exception) {
                 Timber.e(e)
                 false
             }
        }
        return boolean
    }
}