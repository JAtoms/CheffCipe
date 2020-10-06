package com.dtechatoms.cheffcipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dtechatoms.cheffcipe.databinding.FoodListItemBinding
import com.dtechatoms.cheffcipe.databinding.HomeCategoriesItemBinding
import com.dtechatoms.cheffcipe.domain.FoodsByNameModel

/**
 * Created by Joshua Nwokoye (Atoms) on 7/22/2020.
 */
class ListOfMealsRecyclerViewAdapter(val clickListener: AllMealsClickListener) :
    ListAdapter<FoodsByNameModel, ListOfMealsRecyclerViewAdapter.AllMealViewHolder>(
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

    class AllMealViewHolder(private var binding: FoodListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(allFoodsModel: FoodsByNameModel) {
            binding.allFoodsModel = allFoodsModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AllMealViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FoodListItemBinding.inflate(layoutInflater, parent, false)
                return AllMealViewHolder(
                    binding
                )
            }
        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<FoodsByNameModel>() {
        override fun areItemsTheSame(
            oldItem: FoodsByNameModel,
            newItem: FoodsByNameModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FoodsByNameModel,
            newItem: FoodsByNameModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    class AllMealsClickListener(val clickListener: (foodsByNameModel: FoodsByNameModel) -> Unit) {
        fun onClick(foodsByNameModel: FoodsByNameModel) = clickListener(foodsByNameModel)
    }
}
