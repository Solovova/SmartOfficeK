package com.example.smartoffice.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

open class FragmentParent : Fragment() {
    //Must be call from two point. 1 - from OnVie
    open fun onShow() {
        Log.i("APP_SHOW",this.javaClass.name)
    }

    open fun onHide() {
        Log.i("APP_HIDE",this.javaClass.name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.onShow()
    }
}