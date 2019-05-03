package com.example.smartoffice.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.example.smartoffice.R
import com.example.smartoffice.SOApplication
import com.example.smartoffice.soviews.SensorButton

class FragmentStart : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var app = activity?.application as SOApplication
        app.sensorContainer.setViewContainer(view.findViewById(R.id.container))
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentStart().apply {

            }
    }

}
