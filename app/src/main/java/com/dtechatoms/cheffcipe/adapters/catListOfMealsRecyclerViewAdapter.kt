package com.dtechatoms.cheffcipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dtechatoms.cheffcipe.databinding.CategoryFragmentFoodListItemBinding
import com.dtechatoms.cheffcipe.domain.FoodsByCategoryModel

/**
 * Created by Joshua Nwokoye (Atoms) on 7/22/2020.
 */
class catListOfMealsRecyclerViewAdapter(val clickListener: AllCatMealsClickListener) :
    ListAdapter<FoodsByCategoryModel, catListOfMealsRecyclerViewAdapter.AllMealViewHolder>(
        DiffCallBack
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMealViewHolder {
        return AllMealViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: AllMealViewHolder, position: Int) {
        val foodsByNameModelModel = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(foodsByNameModelModel)
        }
        holder.bind(foodsByNameModelModel)
    }

    class AllMealViewHolder(private var binding: CategoryFragmentFoodListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(foodsByCategoryModel: FoodsByCategoryModel) {
            binding.allFoodsModel = foodsByCategoryModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AllMealViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CategoryFragmentFoodListItemBinding.inflate(layoutInflater, parent, false)
                return AllMealViewHolder(binding)
            }
        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<FoodsByCategoryModel>() {
        override fun areItemsTheSame(
            oldItem: FoodsByCategoryModel,
            newItem: FoodsByCategoryModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FoodsByCategoryModel,
            newItem: FoodsByCategoryModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    class AllCatMealsClickListener(val clickListener: (foodsByCategoryModel: FoodsByCategoryModel) -> Unit) {
        fun onClick(foodsByCategoryModel: FoodsByCategoryModel) = clickListener(foodsByCategoryModel)
    }
}
