package com.ziesapp.volunteera.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ziesapp.volunteera.MainActivity
import com.ziesapp.volunteera.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var mAuth: FirebaseAuth
    private lateinit var pbLogin: ProgressBar

    private lateinit var callBackFragment: CallBackFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etEmail = view.findViewById(R.id.et_email)
        etPassword = view.findViewById(R.id.et_password)
        mAuth = FirebaseAuth.getInstance()
        pbLogin = view.findViewById(R.id.pb_login)
        btnLogin = view.findViewById(R.id.btn_login)
        btnRegister = view.findViewById(R.id.btn_register)
        btnRegister.setOnClickListener(this)
        btnLogin.setOnClickListener(this)

        if (mAuth.currentUser != null) {
            startActivity(Intent(context, MainActivity::class.java))
            activity?.finish()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_register -> callBackFragment.changeFragment()
            R.id.btn_login -> {
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()

                if (email.isEmpty()) {
                    etEmail.error = "Email required"
                    return
                }
                if (password.isEmpty()) {
                    etPassword.error = "Password required"
                    return
                }
                if (password.length < 8) {
                    etPassword.error = "Password at least needs 8 character"
                    return
                }
                pbLogin.visibility = View.VISIBLE
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (!it.isSuccessful) {
                        Toast.makeText(
                            activity,
                            "Error! : " + (it.exception?.message),
                            Toast.LENGTH_SHORT
                        ).show()
                        pbLogin.visibility = View.INVISIBLE
                    } else {
                        Toast.makeText(activity, "Succesfully Login", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }


        }
    }

    fun setCallbackFragment(callBackFragment: CallBackFragment) {
        this.callBackFragment = callBackFragment
    }
}