package com.example.smartoffice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.smartoffice.R
import com.example.smartoffice.SOApplication

class FragmentStart : FragmentParent() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = activity?.application as SOApplication
        app.sensorContainer.setViewContainer(view.findViewById(R.id.container))
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentStart().apply {

            }
    }

}
