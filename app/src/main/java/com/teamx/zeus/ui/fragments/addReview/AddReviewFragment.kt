package com.teamx.zeus.ui.fragments.addReview

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.teamx.zues.dataclasses.addreview.AddReviewData
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.FragmentAddReviewBinding
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_reviews_list.*


@AndroidEntryPoint
class AddReviewFragment() : BaseFragment<FragmentAddReviewBinding, AddReviewViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_add_review
    override val viewModel: Class<AddReviewViewModel>
        get() = AddReviewViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private var userReview: String? = null
    private var ratingbar: Int? = null
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

        mViewDataBinding.btnAddReview.setOnClickListener {
            when {
                TextUtils.isEmpty(mViewDataBinding.editProductReview.text.toString()) -> {
                    mViewDataBinding.editProductReview.error = "Add Comment"
                    mViewDataBinding.editProductReview.requestFocus()
                }

                else -> {
                    method2(method())
                }
            }
        }

    }
    private fun initialization() {
        userReview = mViewDataBinding.editProductReview.getText().toString().trim()
        ratingbar = mViewDataBinding.ratingBar.rating.toInt()
    }

    fun method(): JsonObject {

        val gson = Gson()
        var hellou: String? = ""

        val arr = listOf("https://api.teamxmv.com/api/attachments/images/WhatsApp Image 2022-09-14 at 12-7386.jpeg")

        val add = AddReviewData(
            pivotId= "6318921e6937e0d2e7918678",
            product= sharedViewModel.productBySlug.toString(),
            shop= "631701eabaf027ab6bd013f0",
            comment= "This is a test review",
            rating= 5,
            photos= arr

        )
        hellou = gson.toJson(add)
        val jsonParser = JsonParser()
        val jo = jsonParser.parse(hellou) as JsonObject


        return jo

    }

    fun method2(json: JsonObject) {

        initialization()


        mViewModel.addReview(json)
        mViewModel.addReviewResponse.observe(requireActivity()) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let {

                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        }
    }

}