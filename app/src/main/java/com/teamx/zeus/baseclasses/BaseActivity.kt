package com.teamx.zeus.baseclasses

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.teamx.zeus.MainApplication
import pub.devrel.easypermissions.EasyPermissions


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    private lateinit var mViewDataBinding: T
    protected lateinit var mViewModel: V
    private val SOME_PERMISSION = 0


    /**
     * viewModel variable that will get value from activity which it will implement this
     * we will use this variable viewModel to bind with view through databinding
     */
    abstract val viewModel: Class<V>

    /**
     * layoutId variable to get layout value from activity which will implement this layoutId
     * we will use this layoutId for databinding
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * bindingVariable which will bind with view
     */

    abstract val bindingVariable: Int


    lateinit var fusedLocation: FusedLocationProviderClient
    private var locationRequest: LocationRequest? = null
    private var locationCallback: LocationCallback? = null
    lateinit var latlng: LatLng

    /**
     * LocationFoundListener instance
     */
    var locationFoundListener: LocationFoundListener? = null

    /**
     * OnAllowPermissionListener instance
     */
    var allowPermissionListener: OnAllowPermissionListener? = null

    /**
     * Trigger when permissions are allowed
     */
    fun setOnPermissionAllowListener(listener: OnAllowPermissionListener) {
        allowPermissionListener = listener
    }

    /**
     * setting up listener callback to emit location to activity
     */
    fun setOnLocationFoundListener(listener: LocationFoundListener) {
        locationFoundListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databindingWithViewModel()


        fusedLocationInitialization()

        /**
         * Check Permission
         */
        checkAndAskLocationPermission()

    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(MainApplication.localeManager!!.setLocale(newBase!!))
    }

    private fun fusedLocationInitialization() {
        fusedLocation = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.create()
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest?.interval = 20 * 1000
    }


    private fun checkAndAskLocationPermission() {
        if (EasyPermissions.hasPermissions(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            /// Already have permission, do the thing
            allowPermissionListener?.onPermissionAllow(true)
        } else {
            EasyPermissions.requestPermissions(
                this,
                "We need to access",
                SOME_PERMISSION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }

//    private fun checkGps() {
//        if (!isGpsEnable(this)) {
//            checkGPSDialog.show()
//        } else {
//            //checkGPSDialog.dismiss()
//        }
//    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // EasyPermissions handles the request result.
//        onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    interface OnAllowPermissionListener {
        fun onPermissionAllow(allAllow: Boolean = false)
    }

    interface LocationFoundListener {
        fun onLocationFound(latLng: LatLng?)
    }


    /**
     * Function to perform databinding and attaching viewmodel with view
     */
    private fun databindingWithViewModel() {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        mViewModel = ViewModelProvider(this).get(viewModel)
        mViewDataBinding.setVariable(bindingVariable, mViewModel)
        mViewDataBinding.executePendingBindings()

    }



}