package com.teamx.zeus.ui.fragments.shopHomePage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.models.productsShop.Doc
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.*
import com.teamx.zeus.ui.fragments.Home.OnTopProductListener
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class shopHomePageFragment() : BaseFragment<FragmentShopHomePageBinding, ShopBySlugViewModel>(),
    OnTopProductListener {

    override val layoutId: Int
        get() = R.layout.fragment_shop_home_page
    override val viewModel: Class<ShopBySlugViewModel>
        get() = ShopBySlugViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions

    lateinit var productAdapter: ProductByShopAdapter
    lateinit var productArrayList: ArrayList<Doc>

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

        val str = sharedViewModel.shopBySlug

        str.observe(requireActivity()) {
            Log.d("shopByslug", "onViewCreated: $it")
            mViewModel.shopBySlug(it)
        }

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }

        mViewModel.shopBySlugResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()

                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.let {
                            Picasso.get().load(it.cover_image).into(mViewDataBinding.img)
                            mViewDataBinding.shopName.text = it.name
                            mViewDataBinding.ratingBar.rating = it.rating.toFloat()
                            mViewDataBinding.totalRating.text = it.ratings_count.toString() + " + ratings"
                        }
                    }
                }

                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        })

        val r = sharedViewModel.shopById

        r.observe(requireActivity()) {
            mViewModel.productsByShopId(it)
        }

        mViewModel.productsByShopResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                    Log.e("ajdhsdsahkjhsd","start")

                }

                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {
                        it.let {
                            productArrayList.addAll(it.docs)
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

        productRecyclerview()

    }

    private fun productRecyclerview() {
        productArrayList = ArrayList()

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        mViewDataBinding.ProductRecycler.layoutManager = linearLayoutManager

        productAdapter = ProductByShopAdapter(productArrayList,this)
        mViewDataBinding.ProductRecycler.adapter = productAdapter

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