package com.teamx.zeus.ui.fragments.cart

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.teamx.zeus.MainApplication.Companion.context
import com.teamx.zeus.databinding.ItemViewpagerBinding


class ViewPagerViewHolder(itemViewPagerBinding: ItemViewpagerBinding) :
    RecyclerView.ViewHolder(itemViewPagerBinding.root) {
    val binding = itemViewPagerBinding
}


class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    var fragmentList: ArrayList<Fragment>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }
}



