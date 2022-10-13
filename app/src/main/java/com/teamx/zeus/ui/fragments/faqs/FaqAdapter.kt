package com.teamx.zeus.ui.fragments.faqs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.data.dataclasses.faq.FaqData
import com.teamx.zeus.databinding.ItemFaqBinding

class FaqAdapter(
    val orderArrayList: ArrayList<FaqData>
) : RecyclerView.Adapter<FaqViewHolder>() {
    private var mExpandedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        return FaqViewHolder(ItemFaqBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {

        val faqList: FaqData = orderArrayList[position]

        val isExpanded = position == mExpandedPosition
        holder.bind.detailLayout.setVisibility(if (isExpanded) View.VISIBLE else View.GONE)
        holder.bind.detailLayout.setActivated(isExpanded)

        holder.bind.question.text = faqList.question
        holder.bind.answer.text = faqList.ans

        holder.itemView.setOnClickListener {
            mExpandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(position)

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