package com.example.appdepruebas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"


class Titulo : Fragment() {
    private var name: String? = null
    private var address: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME_BUNDLE)
            address = it.getString(ADDRESS_BUNDLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_titulo, container, false)
    }



    companion object {

        private const val NAME_BUNDLE = "name_bundle"
        private const val ADDRESS_BUNDLE = "address_bundle"


        @JvmStatic
        fun newInstance(name: String, address: String) =
            Titulo().apply {
                arguments = Bundle().apply {
                    putString(NAME_BUNDLE, name)
                    putString(ADDRESS_BUNDLE, address)
                }
            }
    }
}