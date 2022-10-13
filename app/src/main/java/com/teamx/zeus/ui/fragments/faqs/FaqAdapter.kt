package com.teamx.zeus.ui.fragments.faqs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.data.dataclasses.faq.FaqData
import com.teamx.zeus.databinding.ItemFaqBinding

class FaqAdapter(
    val orderArrayList: ArrayList<FaqData>
) : RecyclerView.Adapter<FaqViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        return FaqViewHolder(ItemFaqBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {

        val faqList: FaqData = orderArrayList[position]
        holder.bind.question.text = faqList.question
        holder.bind.answer.text = faqList.ans

        holder.itemView.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return orderArrayList.size
    }
}

class FaqViewHolder(private var binding: ItemFaqBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val bind = binding

}