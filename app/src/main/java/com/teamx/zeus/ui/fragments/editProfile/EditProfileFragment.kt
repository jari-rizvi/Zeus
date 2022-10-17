package com.teamx.zeus.ui.fragments.editProfile

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import com.teamx.zeus.BR
import com.teamx.zeus.MainApplication
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.FragmentEditProfileBinding
import com.teamx.zeus.databinding.FragmentForgotPassBinding
import com.teamx.zeus.databinding.FragmentOTPBinding
import com.teamx.zeus.databinding.FragmentSignInBinding
import com.teamx.zeus.localization.LocaleManager
import com.teamx.zeus.ui.fragments.SignInFragment.AuthViewModel
import com.teamx.zeus.utils.DialogHelperClass
import com.teamx.zues.dataclasses.Profile
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONException
import java.io.File
import java.io.FileOutputStream


@AndroidEntryPoint
class EditProfileFragment() : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_edit_profile
    override val viewModel: Class<EditProfileViewModel>
        get() = EditProfileViewModel::class.java
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

        mViewDataBinding.textView28.setOnClickListener {
            updateProfile(jsonObjectOfProfile())
        }
        mViewDataBinding.imgpProfile.setOnClickListener {
            fetchImageFromGallery()

        }
        mViewDataBinding.profilePicture.setOnClickListener {
            fetchImageFromGallery()
        }
        subscribeToNetworkLiveData()






    }

    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        mViewModel.editProfile()

        mViewModel.editProfileResponse.observe(requireActivity()) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d("TAG", "subscribeToNetworkLiveData:1 ")
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->
                        var addressString = ""
                        data.address?.let {

                            for (address in it) {
                                addressString = addressString + address.address.street_address +
                                        " " + address.address.city +
                                        " " + address.address.state +
                                        " " + address.address.country +
                                        " " + "Zip: " + address.address.zip + "\n"
                            }
                        }
                        val str = data.name ?: ""
                        mViewDataBinding.userName.setText(str)
                        mViewDataBinding.userName.setText(str)
//                        data.contact?.let { contact ->
//                            mViewDataBinding.editPhoneNumber.setText(contact)
//                        }
                        data.email?.let { email ->
                            mViewDataBinding.userEmail.setText(email)
                        }
//                        mViewDataBinding.editAddress.setText(addressString)
//                        data.gender?.let { gender ->
//
//                            when (gender) {
//                                "Male" -> {
//                                    mViewDataBinding.radioMale.isChecked = true
//                                    mViewDataBinding.radioFemale.isChecked = false
//                                    mViewDataBinding.radioOther.isChecked = false
//                                }
//                                "Female" -> {
//                                    mViewDataBinding.radioMale.isChecked = false
//                                    mViewDataBinding.radioFemale.isChecked = true
//                                    mViewDataBinding.radioOther.isChecked = false
//                                }
//                                else -> {
//                                    mViewDataBinding.radioMale.isChecked = false
//                                    mViewDataBinding.radioFemale.isChecked = false
//                                    mViewDataBinding.radioOther.isChecked = true
//                                }
//                            }
//                        }
                        data.profile?.let { profile ->
                            mViewDataBinding.Pass.setText(profile.bio)
                            strImg = profile.avatar
                            strId = profile._id
                            Picasso.get().load(profile.avatar)
                                .into(mViewDataBinding.profilePicture)
                        }

                        Log.d("TAG", "subscribeToNetworkLiveData:2 $data")

                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                    Log.d("TAG", "subscribeToNetworkLiveData:3 ${it.message}")
                }
            }
        }
    }


    private fun updateProfile(obj: JsonObject) {
        Log.d("1235", "updateProfile: $obj")
        mViewModel.updateProfile(obj)
        mViewModel.updateProfileResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d("TAG", "updateProfile:1 ")
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->
                        val snackBar = Snackbar.make(
                            mViewDataBinding.textView28,
                            "Updated",
                            Snackbar.LENGTH_SHORT
                        )
                        snackBar.show()

                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                    Log.d("TAG", "updateProfile:32 ${it.message}")
                }
            }
        }


    }

    var strImg = ""
    var strId = ""
    private fun jsonObjectOfProfile(): JsonObject {

        val obj = JsonObject()
        try {

            var genderStr = "Male"
           /* genderStr = if (mViewDataBinding.radioMale.isChecked) {
                "Male"
            } else if (mViewDataBinding.radioFemale.isChecked) {
                "Female"
            } else {
                "Others"
            }*/

            mViewModel.viewModelScope.launch(Dispatchers.IO) {
                dataStoreProvider.saveUserDetails(
                    mViewDataBinding.userName.text.toString(),
                    mViewDataBinding.userEmail.text.toString(),
                    strImg, mViewDataBinding.PhoneNumber.text.toString()
                )
            }

            obj.addProperty("name", mViewDataBinding.userName.text.toString())
            obj.addProperty("gender", genderStr)
            obj.add(
                "profile",
                Gson().toJsonTree(Profile(strId, strImg, mViewDataBinding.Pass.text.toString()))
            )


        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return obj
    }


    private fun fetchImageFromGallery() {
        startForResult.launch("image/*")
        checker()
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                Picasso.get().load(it).into(mViewDataBinding.profilePicture)
                uploadWithRetrofit(it)
/*                val stream = requireContext().contentResolver.openInputStream(it)
                if (stream != null) {
//                    mViewModel.updateImgProfile(stream, "${File(it.toString()).name}")
                }
                var str = ""

                str = it.toString()

                Log.d("1235", "boddy:$str ")
                val file = File(str)
                Log.d("1235", "boddy:${file.name} ")
                Log.d("1235", "boddy:${file.extension} ")
                val filePart: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "attachment",
                    file.name,
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
                )
                val isp: InputStream? = requireActivity().contentResolver.openInputStream(uri)
                val body = MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart(
                        "attachment",
                        file.name + file.extension,
                        RequestBody.create(
                            "application/octet-stream".toMediaType()*//* MultipartBody.FORM*//*,
                            isp!!.readBytes()
                        )
                    )
                    .build()

                Log.d("1235", "bodyy:${body.parts[0].body} ")
                mViewModel.updateImgProfile(body.parts[0])
//                mViewModel.updateImgProfile(prepareFilePart("attachment", it))
//                red(it)*/
            }

        }


    private fun checker() {
        mViewModel.updateImgProfileResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d("TAG", "updateProfile:1 ")
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->

                        this.strImg = data[0]
//                        Toast.makeText(requireContext(), "hellol${strImg}", Toast.LENGTH_SHORT)
//                            .show()
                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                    Log.d("TAG", "updateProfile:3 ${it.message}")
                }
            }
        }
    }


    private fun uploadWithRetrofit(uri: Uri) {
        val fileDir = requireContext().applicationContext.filesDir
        val file = File(fileDir, "picture.png")
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        inputStream!!.copyTo(outputStream)
        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())

        val filePart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "attachment",
            file.name,
            requestBody
        )
        mViewModel.updateImgProfile(filePart)

    }



}