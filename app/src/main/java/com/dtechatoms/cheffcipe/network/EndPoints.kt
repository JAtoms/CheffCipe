package com.dtechatoms.cheffcipe.network


import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * EndPoints to query the Api
 */
interface MealService {
    @GET("categories.php")
    fun getAllCategories(): Deferred<AllCategories>

    @GET("search.php")
    fun searchByName(@Query("s") s : String): Deferred<FoodsByName>

    @GET("filter.php")
    fun searchByCategory(@Query("c") c : String): Deferred<FoodsByCategory>


}
