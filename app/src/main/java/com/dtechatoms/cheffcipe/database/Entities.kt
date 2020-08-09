package com.dtechatoms.cheffcipe.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dtechatoms.cheffcipe.domain.CategoryModel
import com.dtechatoms.cheffcipe.domain.FoodsByCategoryModel
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel

/**
 * Created by Joshua Nwokoye (Atoms) on 7/27/2020.
 */

@Entity
data class AllCategoriesEntity constructor(
    @PrimaryKey
    val idCategory: String,
    val strCategory: String,
    val strCategoryImg: String,
    val strCategoryDescription: String
)

// << Convert Network results to database objects >>

fun List<AllCategoriesEntity>.asAllCatDomainModel(): List<CategoryModel> {
    return map {
        CategoryModel(
            idCategory = it.idCategory,
            strCategory = it.strCategory,
            strCategoryImg = it.strCategoryImg,
            strCategoryDescription = it.strCategoryDescription
        )
    }
}

@Entity
data class AllSpecificCategoriesEntity constructor(
    @PrimaryKey
    val idMeal: String,
    val foodName: String,
    val imageUrl: String
)


// << Convert Network results to database objects >>
fun List<AllSpecificCategoriesEntity>.asSpecificCategoryDomainModel(): List<FoodsByCategoryModel> {
    return map {
        FoodsByCategoryModel(
            idMeal = it.idMeal,
            foodName = it.foodName,
            imageUrl = it.imageUrl
        )
    }
}

@Entity
data class AllRecipesEntity constructor(
    @PrimaryKey
    var idMeal: String,
    var foodName: String,
    var imageUrl: String,
    var strCategory: String,
    var strArea: String,
    var strInstructions: String,
    var youtubeUrl: String?,
    var sourceUrl: String?,
    var favourite: Boolean = false,
    var strIngredient1: String,
    var strIngredient2: String,
    var strIngredient3: String,
    var strIngredient4: String?,
    var strIngredient5: String?,
    var strIngredient6: String?,
    var strIngredient7: String?,
    var strIngredient8: String?,
    var strIngredient9: String?,
    var strIngredient10: String?,
    var strIngredient11: String,
    var strIngredient12: String,
    var strIngredient13: String,
    var strIngredient14: String?,
    var strIngredient15: String?,
    var strIngredient16: String?,
    var strIngredient17: String?,
    var strIngredient18: String?,
    var strIngredient19: String?,
    var strIngredient20: String?,
    var strMeasure1: String,
    var strMeasure2: String,
    var strMeasure3: String,
    var strMeasure4: String?,
    var strMeasure5: String?,
    var strMeasure6: String?,
    var strMeasure7: String?,
    var strMeasure8: String?,
    var strMeasure9: String?,
    var strMeasure10: String?,
    var strMeasure11: String,
    var strMeasure12: String,
    var strMeasure13: String,
    var strMeasure14: String?,
    var strMeasure15: String?,
    var strMeasure16: String?,
    var strMeasure17: String?,
    var strMeasure18: String?,
    var strMeasure19: String?,
    var strMeasure20: String?
)


fun List<AllRecipesEntity>.asAllRecipeDomainModel(): List<FoodsByNameModel> {
    return map {
        FoodsByNameModel(
            idMeal = it.idMeal,
            foodName = it.foodName,
            imageUrl = it.imageUrl,
            strCategory = it.strCategory,
            strArea = it.strArea,
            strInstructions = it.strInstructions,
            youtubeUrl = it.youtubeUrl,
            sourceUrl = it.sourceUrl,
            strIngredient1 = it.strIngredient1,
            strIngredient2 = it.strIngredient2,
            strIngredient3 = it.strIngredient3,
            strIngredient4 = it.strIngredient4,
            strIngredient5 = it.strIngredient5,
            strIngredient6 = it.strIngredient6,
            strIngredient7 = it.strIngredient7,
            strIngredient8 = it.strIngredient8,
            strIngredient9 = it.strIngredient9,
            strIngredient10 = it.strIngredient10,
            strIngredient11 = it.strIngredient11,
            strIngredient12 = it.strIngredient12,
            strIngredient13 = it.strIngredient13,
            strIngredient14 = it.strIngredient14,
            strIngredient15 = it.strIngredient15,
            strIngredient16 = it.strIngredient16,
            strIngredient17 = it.strIngredient17,
            strIngredient18 = it.strIngredient18,
            strIngredient19 = it.strIngredient19,
            strIngredient20 = it.strIngredient20,
            strMeasure1 = it.strMeasure1,
            strMeasure2 = it.strMeasure2,
            strMeasure3 = it.strMeasure3,
            strMeasure4 = it.strMeasure4,
            strMeasure5 = it.strMeasure5,
            strMeasure6 = it.strMeasure6,
            strMeasure7 = it.strMeasure7,
            strMeasure8 = it.strMeasure8,
            strMeasure9 = it.strMeasure9,
            strMeasure10 = it.strMeasure10,
            strMeasure11 = it.strMeasure11,
            strMeasure12 = it.strMeasure12,
            strMeasure13 = it.strMeasure13,
            strMeasure14 = it.strMeasure14,
            strMeasure15 = it.strMeasure15,
            strMeasure16 = it.strMeasure16,
            strMeasure17 = it.strMeasure17,
            strMeasure18 = it.strMeasure18,
            strMeasure19 = it.strMeasure19,
            strMeasure20 = it.strMeasure20
        )
    }
}