package com.dtechatoms.cheffcipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dtechatoms.cheffcipe.adapters.ListOfMealsRecyclerViewAdapter
import com.dtechatoms.cheffcipe.databinding.FoodDetailFragmentBinding
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel
import com.dtechatoms.cheffcipe.util.setImageUrl
import com.dtechatoms.cheffcipe.util.setTextViews
import com.dtechatoms.cheffcipe.viewmodel.FoodDetailFragmentViewModel
import com.dtechatoms.cheffcipe.viewmodel.FoodDetailFragmentViewModelFactory
import kotlinx.coroutines.channels.ticker
import android.os.CountDownTimer as CountDownTimer1

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

        // Timer to handle refreshing
        val timer = object : CountDownTimer1(15000, 1000){
            override fun onFinish() {
                foodDetailFragmentBinding.swiper.isRefreshing = false
                foodDetailFragmentBinding.loadingAnim.visibility = GONE
                foodDetailFragmentBinding.errorMsg.visibility = VISIBLE
            }
            override fun onTick(p0: Long) {

            }
        }

        foodDetailViewModelFactory =
            FoodDetailFragmentViewModelFactory(argument, activity.application)
        viewModel = ViewModelProvider(
            this,
            foodDetailViewModelFactory
        ).get(FoodDetailFragmentViewModel::class.java)


        foodDetailFragmentBinding.backIcon.setOnClickListener {
            findNavController().navigateUp()
        }

        // start timer immediately fragment is opened
        timer.start()

        // Display contents if data is not empty
        viewModel.isDataResultNull.observe(viewLifecycleOwner, Observer { isDataEmpty ->
            if (!isDataEmpty){
                viewModel.selectedFood.observe(viewLifecycleOwner, Observer {
                    upDateUI(it)
                })
            } else{
                foodDetailFragmentBinding.errorMsg.visibility = VISIBLE
            }
        })

        foodDetailFragmentBinding.swiper.setOnRefreshListener {
            foodDetailFragmentBinding.swiper.isRefreshing = true

            if (viewModel.refresh()) {
                // stop timer
                timer.cancel()
                foodDetailFragmentBinding.swiper.isRefreshing = false
            } else {
                // start timer
                foodDetailFragmentBinding.loadingAnim.visibility = VISIBLE
                foodDetailFragmentBinding.errorMsg.visibility = GONE
               timer.start()
            }
        }

        // Click listener for recyclerViewQuickRecipe
        val quickAdapter =
            ListOfMealsRecyclerViewAdapter(
                ListOfMealsRecyclerViewAdapter.AllMealsClickListener {

                })

        foodDetailFragmentBinding.lifecycleOwner = this
        return foodDetailFragmentBinding.root
    }



    private fun upDateUI(foodsByNameModel: FoodsByNameModel) {
        foodDetailFragmentBinding.apply {
            swiper.isRefreshing = false
            loadingAnim.visibility = GONE
            errorMsg.visibility = GONE
            nestedScrollView.visibility = VISIBLE
        }
        setTextViews(foodDetailFragmentBinding.foodName, foodsByNameModel.foodName)
        setTextViews(foodDetailFragmentBinding.foodOrigin, foodsByNameModel.strArea)
        setTextViews(foodDetailFragmentBinding.directions, foodsByNameModel.strInstructions)
        setTextViews(foodDetailFragmentBinding.ingredients, foodsByNameModel.strIngredient1)
        setImageUrl(foodDetailFragmentBinding.foodImage, foodsByNameModel.imageUrl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onViewCreated(view, savedInstanceState)
    }

}
