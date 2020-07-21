package com.ziesapp.volunteera.ui

import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ziesapp.volunteera.Interest
import com.ziesapp.volunteera.R
import com.ziesapp.volunteera.adapter.GridInterestAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    private val list = ArrayList<Interest>()

    private lateinit var arrayNama: Array<String>
    private lateinit var arrayFoto: TypedArray

    private var param1: String? = null
    private var param2: String? = null

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
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)
        val rvInterest = rootView.findViewById<RecyclerView>(R.id.rv_interest)
        list.addAll(getGridActivity())
        rvInterest.layoutManager = GridLayoutManager(activity, 2)
        val gridInterestAdapter = GridInterestAdapter(list)
        rvInterest.adapter = gridInterestAdapter

        return rootView
    }

    private fun getGridActivity(): ArrayList<Interest> {
        arrayNama = resources.getStringArray(R.array.judul_interest)
        arrayFoto = resources.obtainTypedArray(R.array.data_interest)
        val listInterest = ArrayList<Interest>()
        for (position in arrayNama.indices) {
            val interest = Interest(arrayNama[position], arrayFoto.getResourceId(position,-1))
            listInterest.add(interest)
        }
        return listInterest
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}