package com.teamx.zeus.ui.fragments.OrderList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.multivendor.dataclasses.allorders.DocX
import com.teamx.zeus.databinding.ItemOrderListBinding

class OrderListAdapter(
    val orderArrayList: ArrayList<DocX>,
    val onOrderListListener: OnOrderListListener
) : RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(ItemOrderListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {

        val orderList: DocX = orderArrayList[position]
        holder.bind.orderId.text = "Order Id # " + orderList._id.dropLast(18)
        holder.bind.orderQty.text = orderList.products.get(0).product_id.quantity.toString()
        holder.bind.ProductName.text = orderList.products.get(0).product_id.name.toString()
        holder.bind.orderType.text = orderList.products.get(0).product_id.type.dropLast(18)
        holder.bind.orderPrice.text = "Aed "+orderList.products.get(0).product_id.price.toString()
        Picasso.get().load(orderList.products.get(0).product_id.image).into(holder.bind.img)

        holder.bind.btnReview.setOnClickListener {
            onOrderListListener.onAddReviewClickListener(orderList._id)
        }

    }

    override fun getItemCount(): Int {
        return orderArrayList.size
    }
}

class OrderViewHolder(private var binding: ItemOrderListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val bind = binding

}