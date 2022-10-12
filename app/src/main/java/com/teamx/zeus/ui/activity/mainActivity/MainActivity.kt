package com.teamx.zeus.ui.activity.mainActivity

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.teamx.zeus.BR
import com.teamx.zeus.MainApplication
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseActivity
import com.teamx.zeus.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {


    override val viewModel: Class<MainViewModel>
        get() = MainViewModel::class.java

    override val layoutId: Int
        get() = R.layout.activity_main

    override val bindingVariable: Int
        get() = BR.viewModel

    val RC_SIGN_IN: Int = 1

    private var mFbHelper = null
    private var navController: NavController? = null


    lateinit var progress_bar: ProgressBar
//    private var mFbHelper: FacebookHelper? = null
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initialising()

        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)


        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)

        setupBottomNavMenu(navController!!)

        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    bottomNav?.visibility = View.VISIBLE


                }
                R.id.cartFragment -> {
                    bottomNav?.visibility = View.VISIBLE

                }

                R.id.shopHomePageFragment -> {
                    bottomNav?.visibility = View.VISIBLE
                }

                R.id.settingsFragment -> {
                    bottomNav?.visibility = View.VISIBLE
                }

                R.id.profileFragment -> {
                    bottomNav?.visibility = View.VISIBLE
                }



                else -> {
                    bottomNav?.visibility = View.GONE


                }
            }
        }

    }

    private fun initialising() {
        progress_bar = findViewById(R.id.progress_bar)
    }

    open fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }


    open fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    fun googleSignIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    override fun attachBaseContext(newBase: Context?) =
        super.attachBaseContext(MainApplication.localeManager!!.setLocale(newBase!!))
}

var bottomNav: BottomNavigationView? = null

private fun setupBottomNavMenu(navController: NavController) {

    bottomNav?.setupWithNavController(navController)
    bottomNav?.setOnItemSelectedListener {
        when (it.itemId) {

            R.id.profile -> {
                navController.navigate(R.id.editProfileFragment, null)
            }
            R.id.cartFragment -> {
                navController.navigate(R.id.cartFragment, null)
            }
            R.id.homeFragment -> {
                navController.navigate(R.id.homeFragment, null)
            }
            R.id.menuFragment -> {
                navController.navigate(R.id.profileFragment, null)
            }
        }
        return@setOnItemSelectedListener true
    }

    }

