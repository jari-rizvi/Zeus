package com.teamx.zeus.ui.fragments.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zues.dataclasses.dashboard.PopularProduct
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.*
import com.teamx.zeus.dummyData.Categories
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : BaseFragment<FragmentHomeBinding, HomeViewModel>(), OnTopCategoriesListener,
    OnTopProductListener {

    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: Class<HomeViewModel>
        get() = HomeViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var categoriesAdapter: CategoriesAdapter
    lateinit var categoriesArrayList2: ArrayList<Categories>

    lateinit var productAdapter: ProductAdapter
    lateinit var productArrayList: ArrayList<PopularProduct>

    private lateinit var options: NavOptions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        options = navOptions {
            anim {
                enter = R.anim.enter_from_left
                exit = R.anim.exit_to_left
                popEnter = R.anim.nav_default_pop_enter_anim
                popExit = R.anim.nav_default_pop_exit_anim
            }
        }

        mViewDataBinding.btnFilter.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.paymentFragment, null,options)
        }

        mViewDataBinding.editSearch.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.searchFragment, null,options)
        }

        productRecyclerview()

        mViewModel.home()

        mViewModel.homeResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                    Log.e("ajdhsdsahkjhsd","start")

                }

                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.let {
                            productArrayList.addAll(it.popularProducts)
                            productAdapter.notifyDataSetChanged()

                        }
                    }
                }

                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    Log.e("ajdhsdsahkjhsd","end")

                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        })

        categoriesRecyclerview()

    }

    private fun categoriesRecyclerview() {
        categoriesArrayList2 = ArrayList()
        categoriesArrayList2.add(Categories("Shop", R.drawable.ic_resturant,true))
        categoriesArrayList2.add(Categories("Popular", R.drawable.ic_resturant,false))
        categoriesArrayList2.add(Categories("Service", R.drawable.ic_resturant,false))
        categoriesArrayList2.add(Categories("Resturant", R.drawable.ic_resturant,false))

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mViewDataBinding.categoriesRecycler.layoutManager = linearLayoutManager

        categoriesAdapter = CategoriesAdapter(categoriesArrayList2, this)
        mViewDataBinding.categoriesRecycler.adapter = categoriesAdapter
    }

    private fun productRecyclerview() {
        productArrayList = ArrayList()

        val linearLayoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        mViewDataBinding.ProductRecycler.layoutManager = linearLayoutManager

        productAdapter = ProductAdapter(productArrayList, this)
        mViewDataBinding.ProductRecycler.adapter = productAdapter

    }

    override fun onTopSellerClick(position: Int) {
        for(cat in categoriesArrayList2){
            cat.isChecked = false
        }
        categoriesArrayList2.get(position).isChecked = true
        categoriesAdapter.notifyDataSetChanged()
    }

    override fun onTopproductClick(position: Int) {
        sharedViewModel.setProductBySlug(productArrayList[position].slug)
        navController = Navigation.findNavController(
            requireActivity(),
            R.id.nav_host_fragment
        )
        navController.navigate(R.id.productFragment, null, options)
    }

}

