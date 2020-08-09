package com.dtechatoms.cheffcipe.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.bumptech.glide.Glide.init
import com.dtechatoms.cheffcipe.R
import com.dtechatoms.cheffcipe.database.MealDataBase
import com.dtechatoms.cheffcipe.domain.MealRecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /**
         * [Handler] is a class meant to process a queue of messages (known as [android.os.Message]s)
         * or actions (known as [Runnable]s)
         */

//        // Create the runnable action, which prints out a log and increments the seconds counter
//        runnable = Runnable {
//            // postDelayed re-adds the action to the queue of actions the Handler is cycling
//            // through. The delayMillis param tells the handler to run the runnable in
//            // 1 second (1000ms)
//            handler.postDelayed(runnable, 3000)
//        }
//
//        // This is what initially starts the timer
//        handler.postDelayed(runnable, 1000)
//
//        // Note that the Thread the handler runs on is determined by a class called Looper.
//        // In this case, no looper is defined, and it defaults to the main or UI thread.
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//        fun stopTimer() {
//            // Removes all pending posts of runnable from the handler's queue, effectively stopping the
//            // timer
//            handler.removeCallbacks(runnable)
//        }
        val database = MealDataBase.getInstance(application)

        val mealRecipeRepository = MealRecipeRepository(database)
        val viewModelJob = SupervisorJob()
        val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


        uiScope.launch {
            mealRecipeRepository.refreshCategories()
        }
        startActivity(Intent(this, MainActivity::class.java))
    }
}

