package com.ziesapp.volunteera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ziesapp.volunteera.ui.CallBackFragment
import com.ziesapp.volunteera.ui.LoginFragment
import com.ziesapp.volunteera.ui.RegisterFragment

class MainActivity : AppCompatActivity(),CallBackFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()

        val mLoginFragment = LoginFragment()
        val mFragmentManager = supportFragmentManager
        mLoginFragment.setCallbackFragment(this)
        val fragment = mFragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)

        if (fragment !is LoginFragment) {
            Log.d("Fragment", "Fragment name : " + LoginFragment::class.java.simpleName)
        }
        mFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, mLoginFragment, LoginFragment::class.java.simpleName)
            .commit()

//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment)
//
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home,
//                R.id.navigation_search,
//                R.id.navigation_profile
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

    }

    private fun replaceFragment(){
        val mRegisterFragment = RegisterFragment()
        val mFragmentManager = supportFragmentManager

        mFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container,mRegisterFragment,RegisterFragment::class.java.simpleName)
            .commit()
    }

    override fun changeFragment() {
        replaceFragment()
    }
}