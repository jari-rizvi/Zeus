package com.teamx.zues.ui.fragments.OrderDeatilsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.databinding.ItemOrderDetailBinding
import com.teamx.zues.dataclasses.allorders.Product

class OrderDetailAdapter(
    private val orderArrayList: ArrayList<Product>,
    private val onReviewListener: OnReviewListener
) : RecyclerView.Adapter<OrderDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailViewHolder {
        return OrderDetailViewHolder(ItemOrderDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: OrderDetailViewHolder, position: Int) {
        val orderDetails: Product = orderArrayList[position]

        holder.binding.txtitemName.text = orderDetails.product_id.name
        holder.binding.txtItemIdentifier.text = orderDetails.product_id._id
        holder.binding.txtItemQuantity.text = orderDetails.order_quantity.toString()

        holder.binding.btnReview.setOnClickListener {

            onReviewListener.onReviewClickLister(position)

        }

    }

    override fun getItemCount(): Int {
        return orderArrayList.size
    }
}

class OrderDetailViewHolder(itemOrderDetailBinding: ItemOrderDetailBinding) :
    RecyclerView.ViewHolder(itemOrderDetailBinding.root) {

    val binding = itemOrderDetailBinding
}