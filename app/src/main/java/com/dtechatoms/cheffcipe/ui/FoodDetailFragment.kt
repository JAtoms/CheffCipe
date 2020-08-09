package com.dtechatoms.cheffcipe.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dtechatoms.cheffcipe.R
import com.dtechatoms.cheffcipe.viewmodel.FoodDetailFragmentViewModel

class FoodDetailFragment : Fragment() {

    companion object {
        fun newInstance() = FoodDetailFragment()
    }

    private lateinit var viewModel: FoodDetailFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.food_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FoodDetailFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
