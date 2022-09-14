package com.teamx.zeus.ui.fragments.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.databinding.ItemCategoriesBinding
import com.teamx.zeus.dummyData.Categories

class CategoriesAdapter(val arrayList: ArrayList<Categories>, val onTopCategoriesListener: OnTopCategoriesListener ) : RecyclerView.Adapter<CategoriesAdapter.TopCategoriesViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemTopCategoriesBinding = ItemCategoriesBinding.inflate(inflater,parent,false)
        return TopCategoriesViewHolder(itemTopCategoriesBinding)
    }

    override fun onBindViewHolder(holder: TopCategoriesViewHolder, position: Int) {
        val categories : Categories = arrayList[position]
        holder.binding.categoryName.text = categories.categoriesName
        holder.binding.categoryImage.setImageResource(categories.categoriesImage)
        holder.binding.checkedTextView.isChecked = categories.isChecked
        holder.binding.categoryName.isChecked = categories.isChecked


        holder.itemView.setOnClickListener {
            onTopCategoriesListener.onTopSellerClick(position)
        }

    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    class TopCategoriesViewHolder(itemCategoriesBinding: ItemCategoriesBinding) : RecyclerView.ViewHolder(itemCategoriesBinding.root){
        val binding =itemCategoriesBinding

    }
}