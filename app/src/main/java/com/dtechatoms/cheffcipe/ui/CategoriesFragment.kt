package com.dtechatoms.cheffcipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dtechatoms.cheffcipe.R
import com.dtechatoms.cheffcipe.adapters.ListOfMealsRecyclerViewAdapter
import com.dtechatoms.cheffcipe.adapters.catListOfMealsRecyclerViewAdapter
import com.dtechatoms.cheffcipe.databinding.CategoriesBottomSheetBinding
import com.dtechatoms.cheffcipe.databinding.CategoriesFragmentBinding
import com.dtechatoms.cheffcipe.domain.CategoryModel
import com.dtechatoms.cheffcipe.viewmodel.CategoriesFragmentViewModel
import com.dtechatoms.cheffcipe.viewmodel.CategoryFragmentViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.categories_bottom_sheet.*

class CategoriesFragment : Fragment() {

    private lateinit var categoriesFragmentBinding: CategoriesFragmentBinding
    private lateinit var viewModel: CategoriesFragmentViewModel
    private lateinit var viewModelFactory: CategoryFragmentViewModelFactory
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var bottomSheetBinding: CategoriesBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get passed argument from HomeFragment
        val argument = CategoriesFragmentArgs.fromBundle(arguments!!).foodCategoryList
        val activity = requireNotNull(this.activity)

        viewModelFactory =
            CategoryFragmentViewModelFactory(argument, this.requireContext(), activity.application)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CategoriesFragmentViewModel::class.java)

        categoriesFragmentBinding = CategoriesFragmentBinding.inflate(inflater)
        bottomSheetBinding = CategoriesBottomSheetBinding.inflate(inflater)

        // ViewModels
        categoriesFragmentBinding.categoryViewModel = viewModel
        bottomSheetBinding.categoryViewModel = viewModel

       // Bind RecyclerViews
        val allCategoryListAdapter = catListOfMealsRecyclerViewAdapter(
            catListOfMealsRecyclerViewAdapter.AllCatMealsClickListener{

            })
        categoriesFragmentBinding.allCategoryList.adapter = allCategoryListAdapter


        viewModel.categoryListDetail.observe(viewLifecycleOwner, Observer {
            upDateUI(it)
        })

        categoriesFragmentBinding.toolBar.setNavigationIcon(R.drawable.ic_icon_back_circled)
        categoriesFragmentBinding.lifecycleOwner = this
        return categoriesFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        categoriesFragmentBinding.categoryChooser.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            else
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onViewCreated(view, savedInstanceState)
    }

    fun upDateUI(categoryModel: CategoryModel) {
        categoriesFragmentBinding.categoryName.text = categoryModel.strCategory
        categoriesFragmentBinding.foodDescription.text = categoryModel.strCategoryDescription

        Glide.with(this)
            .load(categoryModel.strCategoryImg)
            .into(categoriesFragmentBinding.foodImage)
    }

}
