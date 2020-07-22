package com.ziesapp.volunteera.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.ziesapp.volunteera.MainActivity
import com.ziesapp.volunteera.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var etNama: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etImage: EditText
    private lateinit var etWebsite: EditText
    private lateinit var btnRegister: Button
    private lateinit var pbRegister: ProgressBar

    private lateinit var mAuth: FirebaseAuth

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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        etNama = view.findViewById(R.id.et_nama)
        etPassword = view.findViewById(R.id.et_password)
        etEmail = view.findViewById(R.id.et_email)
        etWebsite = view.findViewById(R.id.et_web)
        etImage = view.findViewById(R.id.et_image_url)
        pbRegister = view.findViewById(R.id.pb_register)
        btnRegister = view.findViewById(R.id.btn_register)
        btnRegister.setOnClickListener(this)

        if (mAuth.currentUser != null) {
            startActivity(Intent(context, MainActivity::class.java))
            activity?.finish()
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (email.isEmpty()) {
            etEmail.error = "Email perlu diisi"
            return
        }
        if (password.isEmpty()) {
            etPassword.error = "Password perlu diisi"
            return
        }
        if (password.length < 8) {
            etPassword.error = "Password membutuhkan setidaknya 8 karakter"
            return
        }
        pbRegister.visibility = View.VISIBLE
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!it.isSuccessful) {
                return@addOnCompleteListener
            } else {
                Toast.makeText(activity, "Succesfully Login", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
        }
            .addOnFailureListener {
                Log.d("RegisterFragment", "Failed Login: ${it.message}")
                Toast.makeText(activity, "Email/Password incorrect", Toast.LENGTH_SHORT).show()
                pbRegister.visibility = View.INVISIBLE
            }
    }
}