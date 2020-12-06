package com.dtechatoms.cheffcipe.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel

/**
 * Created by Joshua Nwokoye (Atoms) on 7/27/2020.
 */

@Dao
interface RecipeDao{

    // For category names
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoCategories(vararg videos: AllCategoriesEntity)

    // For a specific category
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoSpecificCategories(vararg allSpecificCategoriesEntity: AllSpecificCategoriesEntity)

    // Update database with a specific food
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoAllRecipes(vararg allRecipesEntity: AllRecipesEntity)



    // Get list of categories
    @Query("SELECT *from allcategoriesentity")
    fun getListOfCategories(): LiveData<List<AllCategoriesEntity>>

    // Get random list of a specific category
    @Query("SELECT *from allspecificcategoriesentity WHERE mealCategory = :category ORDER BY RANDOM() ")
    fun getSpecificCategory(category: String): LiveData<List<AllSpecificCategoriesEntity>>

    // Get every meal in the database randomly
    @Query("SELECT *from allrecipesentity ORDER BY RANDOM()")
    fun getAllRecipes(): LiveData<List<AllRecipesEntity>>

    // Get specific meal by category
    @Query("SELECT *from allrecipesentity WHERE strCategory= :foodCategory ORDER BY RANDOM() LIMIT 4")
    fun getSpecificMealCategory(foodCategory: String): LiveData<List<AllRecipesEntity>>

    // Get a specific meal in the database
    @Query("SELECT *from allrecipesentity WHERE idMeal= :mealID ")
    fun getSpecificMeal(mealID: String): FoodsByNameModel

    // Get a favorite meal
    @Query("SELECT *from allrecipesentity WHERE favourite = :favouriteRecipe ")
    fun getSpecificFavourite(favouriteRecipe: Boolean): LiveData<List<AllRecipesEntity>>
}