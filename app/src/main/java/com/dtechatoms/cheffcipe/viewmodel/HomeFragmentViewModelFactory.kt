package com.dtechatoms.cheffcipe.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 * Created by Joshua Nwokoye (Atoms) on 7/30/2020.
 */

class HomeFragmentViewModelFactory(val context: Context, val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(context, application) as T
        }
throw IllegalArgumentException("Unable to construct viewModel from HomeFragmentViewModelFactory")
    }
}