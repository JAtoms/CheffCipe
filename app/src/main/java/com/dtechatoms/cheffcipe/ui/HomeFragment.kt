package com.dtechatoms.cheffcipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dtechatoms.cheffcipe.adapters.CategoryRecyclerViewAdapter
import com.dtechatoms.cheffcipe.adapters.HomeAllMealRecyclerViewAdapter
import com.dtechatoms.cheffcipe.databinding.HomeFragmentBinding
import com.dtechatoms.cheffcipe.viewmodel.HomeFragmentViewModel
import com.dtechatoms.cheffcipe.viewmodel.HomeFragmentViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var homeFragmentBinding: HomeFragmentBinding
    private lateinit var viewModelFactory: HomeFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = HomeFragmentBinding.inflate(inflater)

        val activity = requireNotNull(this.activity)
        viewModelFactory = HomeFragmentViewModelFactory(this.requireContext(), activity.application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeFragmentViewModel::class.java)
        homeFragmentBinding.homeFragmentViewModel = viewModel

        viewModel.shimmerCategory.observe(viewLifecycleOwner, Observer {
            if (it){
                homeFragmentBinding.shimmerAllContainer.stopShimmer()
                homeFragmentBinding.shimmerAllContainer.visibility = GONE
            }
        })

        // Click listener for recyclerViewQuickRecipe
        val quickAdapter =
            HomeAllMealRecyclerViewAdapter(
                HomeAllMealRecyclerViewAdapter.AllMealsClickListener {
                    viewModel.navigateToPropertiesDetail(it)
                })

        // Click listener for recyclerViewCategories
        val adapter =
            CategoryRecyclerViewAdapter(
                CategoryRecyclerViewAdapter.CategoryClickListener {
                    viewModel.navigateToCategories(it)
                    Toast.makeText(context, it.strCategory, Toast.LENGTH_SHORT).show()
                })

        // Navigate to FoodDetails
        viewModel.navigateToSelectedFood.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToFoodDetailFragment2(it))
                viewModel.displayPropertiesDetailCompleted()
            }
        })

        // Navigate to Categories
        viewModel.navigateToSelectedCategory.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToCategoriesFragment2(it))
                viewModel.navigateToCategoriesCompleted()
            }
        })



        homeFragmentBinding.recyclerViewQuickRecipe.adapter = quickAdapter
        homeFragmentBinding.recyclerViewCategories.adapter = adapter

        homeFragmentBinding.lifecycleOwner = this
        return homeFragmentBinding.root
    }

    override fun onResume() {
        super.onResume()
        homeFragmentBinding.shimmerAllContainer.startShimmer()
    }

    override fun onPause() {
        homeFragmentBinding.shimmerAllContainer.stopShimmer()
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onViewCreated(view, savedInstanceState)
    }

}