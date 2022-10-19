package com.teamx.zeus.ui.fragments.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.zeus.databinding.ItemTopShopBinding
import com.teamx.zues.dataclasses.dashboard.PopularShop

class ShopAdapter(val arrayList: ArrayList<PopularShop>, val onTopShopListener: OnTopShopListener ) : RecyclerView.Adapter<ShopAdapter.TopShopViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopShopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemTopShopBinding = ItemTopShopBinding.inflate(inflater, parent, false)
        return TopShopViewHolder(itemTopShopBinding)

    }

    override fun onBindViewHolder(holder: TopShopViewHolder, position: Int) {
        val shop : PopularShop = arrayList[position]
        holder.binding.shopName.text = shop.name
        holder.binding.shopDistance.text = "5m"
        holder.binding.shopType.text = shop.slug
        Picasso.get().load(shop.logo).into(holder.binding.shopImage)


        holder.itemView.setOnClickListener {
            onTopShopListener.onTopshopClick(position)
        }

    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    class TopShopViewHolder(itemTopShopBinding: ItemTopShopBinding) : RecyclerView.ViewHolder(itemTopShopBinding.root){
        val binding =itemTopShopBinding

    }
}