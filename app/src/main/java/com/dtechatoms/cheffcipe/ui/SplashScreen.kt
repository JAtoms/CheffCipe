package com.dtechatoms.cheffcipe.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dtechatoms.cheffcipe.MainActivity
import com.dtechatoms.cheffcipe.R
import com.dtechatoms.cheffcipe.database.MealDataBase
import com.dtechatoms.cheffcipe.domain.MealRecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val database = MealDataBase.getInstance(application)

        val mealRecipeRepository = MealRecipeRepository(database)
        val viewModelJob = SupervisorJob()
        val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        uiScope.launch {
            mealRecipeRepository.fetchSpecifiedCategory("Beef")
            mealRecipeRepository.refreshCategories()
        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

