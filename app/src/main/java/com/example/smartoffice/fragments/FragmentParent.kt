package com.example.smartoffice.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.smartoffice.service.Sensor
import com.example.smartoffice.service.SensorIndicator

open class FragmentParent : Fragment() {
    var sensor: Sensor? = null
    var sensorIndicator: SensorIndicator? = null
    var strData: String? = null
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