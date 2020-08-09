package com.dtechatoms.cheffcipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dtechatoms.cheffcipe.databinding.HomeCategoriesItemBinding
import com.dtechatoms.cheffcipe.domain.CategoryModel

/**
 * Created by Joshua Nwokoye (Atoms) on 7/22/2020.
 */
class CategoryRecyclerViewAdapter(val clickListener: CategoryClickListener) :
    ListAdapter<CategoryModel, CategoryRecyclerViewAdapter.CategoryViewHolder>(
        DiffCallBack
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryModel = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(categoryModel)
        }
        holder.bind(categoryModel)
    }

    class CategoryViewHolder(private var binding: HomeCategoriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryModel: CategoryModel) {
            binding.categoryModel = categoryModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HomeCategoriesItemBinding.inflate(layoutInflater, parent, false)
                return CategoryViewHolder(
                    binding
                )
            }
        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }

    }

    class CategoryClickListener(val clickListener: (categoryModel: CategoryModel) -> Unit) {
        fun onClick(categoryModel: CategoryModel) = clickListener(categoryModel)
    }
}
