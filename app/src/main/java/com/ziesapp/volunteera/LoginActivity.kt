package com.ziesapp.volunteera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ziesapp.volunteera.ui.CallBackFragment
import com.ziesapp.volunteera.ui.LoginFragment
import com.ziesapp.volunteera.ui.RegisterFragment

class LoginActivity : AppCompatActivity(), CallBackFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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


    }

    private fun replaceFragment() {
        val mRegisterFragment = RegisterFragment()
        val mFragmentManager = supportFragmentManager

        mFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.fragment_container,
                mRegisterFragment,
                RegisterFragment::class.java.simpleName
            )
            .commit()
    }

    override fun changeFragment() {
        replaceFragment()
    }
}