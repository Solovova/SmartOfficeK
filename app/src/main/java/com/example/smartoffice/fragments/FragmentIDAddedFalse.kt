package com.example.smartoffice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.smartoffice.R


class FragmentIDAddedFalse : FragmentParent() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_id_added_false, container, false)
    }

    override fun onShow() {
        super.onShow()
    }



    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentIDAddedFalse().apply {
            }
    }
}