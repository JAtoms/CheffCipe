package com.dtechatoms.cheffcipe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Joshua Nwokoye (Atoms) on 7/27/2020.
 */

@Database(entities = [AllCategoriesEntity::class, AllRecipesEntity::class, AllSpecificCategoriesEntity::class],
    version = 1, exportSchema = false)
abstract class MealDataBase : RoomDatabase() {

    abstract val recipeDao: RecipeDao

    companion object {

        @Volatile
        private var INSTANCE: MealDataBase? = null
        fun getInstance(context: Context): MealDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MealDataBase::class.java,
                        "the_meal_recipe_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}