package com.example.smartoffice.service

import android.app.Application
import android.util.Log
import android.widget.LinearLayout
import com.example.smartoffice.soviews.SensorButton

class SensorContainer {
    var sensors = mutableMapOf<String,Sensor>()
    private var layoutScrollContainer : LinearLayout? = null

    var app: Application? = null
    constructor(_app: Application){
        this.app = _app
    }

    fun setLayoutScrollContainer (linearLayout: LinearLayout) {
        this.layoutScrollContainer = linearLayout
    }

    fun testAdd() {
        Log.i("Added", "Start")
        val layoutScrollContainer = this.layoutScrollContainer
        if (layoutScrollContainer != null) {
            if (layoutScrollContainer.childCount > 0) layoutScrollContainer.removeAllViews()
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            for (i in 1..10) {
                val newButton = SensorButton(layoutScrollContainer.context)
                newButton.setText("Name $i")
                params.setMargins(10, 40, 10, 40)
                newButton.layoutParams = params
                Log.i("Added", "Name $i")
                layoutScrollContainer.addView(newButton)
            }
            layoutScrollContainer.invalidate()
        }
    }

    fun testChange() {

    }
}