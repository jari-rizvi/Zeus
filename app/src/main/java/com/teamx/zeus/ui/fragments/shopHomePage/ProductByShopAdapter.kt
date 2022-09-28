package com.teamx.zeus.ui.fragments.shopHomePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.zeus.data.models.productsShop.Doc
import com.teamx.zeus.databinding.ItemShopHomePageBinding

class ProductByShopAdapter(val arrayList: ArrayList<Doc>) : RecyclerView.Adapter<ProductByShopAdapter.ShopProductViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemshopHomeBinding = ItemShopHomePageBinding .inflate(inflater, parent, false)
        return ShopProductViewHolder(itemshopHomeBinding)

    }

    override fun onBindViewHolder(holder: ShopProductViewHolder, position: Int) {
        val product : Doc = arrayList[position]

        holder.binding.name.text = product.name
        Picasso.get().load(product.image).into(holder.binding.productimg)


    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    class ShopProductViewHolder(itemshopHomeBinding: ItemShopHomePageBinding) : RecyclerView.ViewHolder(itemshopHomeBinding.root){
        val binding =itemshopHomeBinding

    }
}