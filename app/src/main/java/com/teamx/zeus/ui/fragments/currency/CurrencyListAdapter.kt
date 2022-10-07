package com.teamx.zeus.ui.fragments.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.data.dataclasses.currency.CurrencyModel
import com.teamx.zeus.databinding.ItemCurrencyBinding
import kotlin.collections.ArrayList

class CurrencyListAdapter(
    val currencyArrayList: ArrayList<CurrencyModel>,
) : RecyclerView.Adapter<CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {

        val currencyList: CurrencyModel = currencyArrayList[position]
        holder.bind.name.text = currencyList.name
        holder.bind.rate.text = currencyList.rate.toString()

    }

    override fun getItemCount(): Int {
        return currencyArrayList.size
    }
}

class CurrencyViewHolder(private var binding: ItemCurrencyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val bind = binding

}