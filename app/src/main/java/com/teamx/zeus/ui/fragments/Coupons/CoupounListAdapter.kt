package com.teamx.zeus.ui.fragments.Coupons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.databinding.ItemCouponBinding

class CoupounListAdapter(val coupounArrayList: ArrayList<com.teamx.zeus.data.dataclasses.coupouns.Doc>) :
    RecyclerView.Adapter<CoupounViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoupounViewHolder {
        return CoupounViewHolder(ItemCouponBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CoupounViewHolder, position: Int) {

        val coupounList: com.teamx.zeus.data.dataclasses.coupouns.Doc = coupounArrayList[position]
        holder.bind.discount.text = coupounList.description
        holder.bind.expireDate .text = coupounList.expire_at.dropLast(14)

    }

    override fun getItemCount(): Int {
        return coupounArrayList.size
    }
}

class CoupounViewHolder(private var binding: ItemCouponBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val bind = binding

}