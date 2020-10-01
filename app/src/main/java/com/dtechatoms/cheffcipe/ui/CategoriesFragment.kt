package com.dtechatoms.cheffcipe.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.dtechatoms.cheffcipe.R
import com.dtechatoms.cheffcipe.databinding.CategoriesFragmentBinding
import com.dtechatoms.cheffcipe.viewmodel.CategoriesFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.categories_bottom_sheet.*

class CategoriesFragment : Fragment() {

    private lateinit var categoriesFragmentBinding: CategoriesFragmentBinding
    private lateinit var viewModel: CategoriesFragmentViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        categoriesFragmentBinding = CategoriesFragmentBinding.inflate(inflater)
        return categoriesFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoriesFragmentViewModel::class.java)

        categoriesFragmentBinding.backIcon.setOnClickListener {
            //findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragment2ToHomeFragment22())
           // requireActivity().onBackPressedDispatcher.addCallback(ond)
        }

        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
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

}
