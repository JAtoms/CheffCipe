package com.dtechatoms.cheffcipe.network

import com.dtechatoms.cheffcipe.database.AllCategoriesEntity
import com.dtechatoms.cheffcipe.database.AllRecipesEntity
import com.dtechatoms.cheffcipe.database.AllSpecificCategoriesEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * DataTransferObjects go in this file. These are responsible for parsing responses from the server
 * or formatting objects to send to the server. You should convert these to domain objects before
 * using them.
 */


/**
 * List all categories
 */
@JsonClass(generateAdapter = true)
data class AllCategories(val categories: List<CategoryClass>)

@JsonClass(generateAdapter = true)
data class CategoryClass(
    val idCategory: String,
    val strCategory: String,
    @Json(name = "strCategoryThumb")
    val strCategoryImg: String,
    val strCategoryDescription: String
)

// << Convert Network results to database objects >>

fun AllCategories.asDataModel(): Array<AllCategoriesEntity> {
    return categories.map {
        AllCategoriesEntity(
            idCategory = it.idCategory,
            strCategory = it.strCategory,
            strCategoryImg = it.strCategoryImg,
            strCategoryDescription = it.strCategoryDescription
        )
    }.toTypedArray()
}


/**
 * List all foods by category
 */
@JsonClass(generateAdapter = true)
data class FoodsByCategory(val meals: List<FoodCategory>)

@JsonClass(generateAdapter = true)
data class FoodCategory(
    @Json(name = "strMeal")
    val foodName: String,
    @Json(name = "strMealThumb")
    val imageUrl: String,
    val idMeal: String
)



// << Convert Network results to database objects >>
fun FoodsByCategory.asDataModel(category:String): Array<AllSpecificCategoriesEntity> {
    return meals.map {
        AllSpecificCategoriesEntity(
            idMeal = it.idMeal,
            foodName = it.foodName,
            imageUrl = it.imageUrl,
            mealCategory = category
        )
    }.toTypedArray()
}


/**
 * List all foods by name
 */
@JsonClass(generateAdapter = true)
data class FoodsByName(val meals: List<FoodName>)

@JsonClass(generateAdapter = true)
data class FoodName(
    val idMeal: String,
    @Json(name = "strMeal")
    val foodName: String,
    @Json(name = "strMealThumb")
    val imageUrl: String,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    @Json(name = "strYoutube")
    val youtubeUrl: String,
    @Json(name = "strSource")
    val sourceUrl: String?,
    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String,
    val strIngredient12: String,
    val strIngredient13: String,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient20: String?,
    val strMeasure1: String,
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String,
    val strMeasure12: String,
    val strMeasure13: String,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure20: String?
)

fun FoodsByName.asDatabaseModel(): Array<AllRecipesEntity> {
    return meals.map {
        AllRecipesEntity(
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
    }.toTypedArray()
}
