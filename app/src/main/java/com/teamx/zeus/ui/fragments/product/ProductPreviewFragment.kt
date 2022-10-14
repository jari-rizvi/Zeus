package com.teamx.zeus.ui.fragments.product

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.squareup.picasso.Picasso
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.*
import com.teamx.zeus.utils.DialogHelperClass
import com.teamx.zeus.data.local.dbModel.CartTable
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductPreviewFragment() : BaseFragment<FragmentProductBinding, ProductPreviewViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_product
    override val viewModel: Class<ProductPreviewViewModel>
        get() = ProductPreviewViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

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

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }
        mViewDataBinding.btnReview.setOnClickListener {

            Log.d("jsdks", sharedViewModel.productBySlug.value!!)

            sharedViewModel.setProductBySlug(sharedViewModel.productBySlug.value!!)
            navController = Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
            )
            navController.navigate(R.id.reviewListFragment, null, options)
        }

        mViewDataBinding.btnAddtoCart.setOnClickListener {
            mViewModel.productPreviewResponse.value?.data?.let {
                mViewModel.insertCartProduct(CartTable(0, it))

                Toast.makeText(requireContext(), "Added To Cart Successfully", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        mViewDataBinding.btnBack.setOnClickListener {
         popUpStack()
        }

        mViewModel.productPreview()

        mViewModel.productPreviewResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->
                        mViewDataBinding.ProductName.text = data.name
                        mViewDataBinding.productDescriptio.text = data.description
                        mViewDataBinding.productPrice.text = data.price.toString() + " AED"
                        Picasso.get().load(data.image).into(mViewDataBinding.img)

                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        })



    }


}