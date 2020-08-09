package com.dtechatoms.cheffcipe

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by Joshua Nwokoye (Atoms) on 7/26/2020.
 */

class CheffCipeApplication : Application() {

    private val uiScope = CoroutineScope(Dispatchers.IO)
    override fun onCreate() {
        super.onCreate()
       Timber.plant(Timber.DebugTree())

    }

    private fun delayedInit() {
        uiScope.launch {
            // TODO schedule work here
        }
    }
}