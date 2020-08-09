package com.dtechatoms.cheffcipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dtechatoms.cheffcipe.databinding.HomeFragmentBinding
import com.dtechatoms.cheffcipe.adapters.CategoryRecyclerViewAdapter
import com.dtechatoms.cheffcipe.adapters.HomeAllMealRecyclerViewAdapter
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

        viewModel.searchValue = homeFragmentBinding.clickToSearch.toString()

        viewModel.searchError.observe(viewLifecycleOwner, Observer {
            homeFragmentBinding.textView1.text = it
        })

        //TODO Delete it latter
        viewModel.searchClicked.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        val quickAdapter =
            HomeAllMealRecyclerViewAdapter(
                HomeAllMealRecyclerViewAdapter.AllMealsClickListener {
                    Toast.makeText(context, "All Foods", Toast.LENGTH_SHORT).show()
                })
        homeFragmentBinding.recyclerViewQuickRecipe.adapter = quickAdapter

        val adapter =
            CategoryRecyclerViewAdapter(
                CategoryRecyclerViewAdapter.CategoryClickListener {
                    Toast.makeText(context, "Categories", Toast.LENGTH_SHORT).show()
                })
        homeFragmentBinding.recyclerViewCategories.adapter = adapter

        homeFragmentBinding.lifecycleOwner = this
        return homeFragmentBinding.root
    }
}