package com.dtechatoms.cheffcipe.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.dtechatoms.cheffcipe.R
import com.dtechatoms.cheffcipe.databinding.FoodDetailFragmentBinding
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel
import com.dtechatoms.cheffcipe.viewmodel.FoodDetailFragmentViewModel
import com.dtechatoms.cheffcipe.viewmodel.FoodDetailFragmentViewModelFactory
import com.dtechatoms.cheffcipe.viewmodel.HomeFragmentViewModel
import com.dtechatoms.cheffcipe.viewmodel.HomeFragmentViewModelFactory
import kotlinx.android.synthetic.main.home_fragment.*

class FoodDetailFragment : Fragment() {

    private lateinit var viewModel: FoodDetailFragmentViewModel
    private lateinit var foodDetailFragmentBinding: FoodDetailFragmentBinding
    private lateinit var foodDetailViewModelFactory: FoodDetailFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        foodDetailFragmentBinding = FoodDetailFragmentBinding.inflate(inflater)

        // Get passed argument
        val argument = FoodDetailFragmentArgs.fromBundle(arguments!!).selectedFood

        val activity = requireNotNull(this.activity)
        foodDetailViewModelFactory =
            FoodDetailFragmentViewModelFactory(argument, activity.application)
        viewModel = ViewModelProvider(
            this,
            foodDetailViewModelFactory
        ).get(FoodDetailFragmentViewModel::class.java)

        viewModel.selectedFood.observe(viewLifecycleOwner, Observer {
            upDateUI(it)
        })

        foodDetailFragmentBinding.lifecycleOwner = this
        return foodDetailFragmentBinding.root
    }


    fun upDateUI(foodsByNameModel: FoodsByNameModel) {
        foodDetailFragmentBinding.foodName.text = foodsByNameModel.foodName
        foodDetailFragmentBinding.foodOrigin.text = foodsByNameModel.strArea
        //foodDetailFragmentBinding.ingredients.text = foodsByNameModel.
        foodDetailFragmentBinding.directions.text = foodsByNameModel.strInstructions

        Glide.with(this)
            .load(foodsByNameModel.imageUrl)
            .into(foodDetailFragmentBinding.foodImage)
    }


//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(FoodDetailFragmentViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onViewCreated(view, savedInstanceState)
    }

}
