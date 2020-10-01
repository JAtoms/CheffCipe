package com.dtechatoms.cheffcipe.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Joshua Nwokoye (Atoms) on 7/27/2020.
 */

@Dao
interface RecipeDao{


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoCategories(vararg videos: AllCategoriesEntity)

    @Query("SELECT *from allcategoriesentity")
    fun getListOfCategories(): LiveData<List<AllCategoriesEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoSpecificCategories(vararg videos: AllSpecificCategoriesEntity)

    @Query("SELECT *from allspecificcategoriesentity")
    fun getListOfSpecificCategories(): LiveData<List<AllSpecificCategoriesEntity>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoAllRecipes(vararg allRecipesEntity: AllRecipesEntity)

    @Update
    fun updateRecipeTable(allRecipesEntity: AllRecipesEntity)

    @Query("SELECT *from allrecipesentity")
    fun getAllRecipes(): LiveData<List<AllRecipesEntity>>

    @Query("SELECT *from allrecipesentity WHERE strCategory= :category ")
    fun getSpecificCategory(category: String): LiveData<List<AllRecipesEntity>>

    @Query("SELECT *from allrecipesentity WHERE idMeal= :mealID ")
    fun getSpecificMeal(mealID: String): AllRecipesEntity

    @Query("SELECT *from allrecipesentity WHERE favourite = :favouriteRecipe ")
    fun getSpecificFavourite(favouriteRecipe: Boolean): LiveData<List<AllRecipesEntity>>
}