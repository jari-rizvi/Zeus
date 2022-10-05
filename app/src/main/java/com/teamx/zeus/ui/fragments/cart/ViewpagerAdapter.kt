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


/*class ViewPagerAdapter(private val arrayList: ArrayList<Fragment>, context: Context) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemViewPagerBinding = ItemViewpagerBinding.inflate(inflater, parent, false)
        return ViewPagerViewHolder(itemViewPagerBinding)

    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        val ft: FragmentTransaction = context.getSupportFragmentManager().beginTransaction()
        ft.replace(holder.binding.button3 ,arrayList[position])
        ft.commit()
        holder.itemView.setOnClickListener {

        }
    }


    override fun getItemCount(): Int {
        return arrayList.size

    }*/

//
//
//
//}
//
class ViewPagerViewHolder(itemViewPagerBinding: ItemViewpagerBinding) :
    RecyclerView.ViewHolder(itemViewPagerBinding.root) {
    val binding = itemViewPagerBinding
}
//
//private class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
//    override fun getItemCount(): Int = 3
//
//
//    override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment()

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



