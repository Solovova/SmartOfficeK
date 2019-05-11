package com.example.smartoffice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartoffice.R
import com.example.smartoffice.SOApplication

class FragmentStart : FragmentParent() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onShow() {
        super.onShow()
        val view = this.view
        if (view != null) {
            (activity?.application as SOApplication).sensorContainer.setViewContainer(view.findViewById(R.id.container))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentStart()
    }
}
