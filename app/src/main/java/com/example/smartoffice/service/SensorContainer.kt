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
        Log.i("Added","Start" )

        if (layoutScrollContainer?.childCount != 0 ) layoutScrollContainer?.removeAllViews()

        var params: LinearLayout.LayoutParams  = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        val myContext = layoutScrollContainer?.context
        Log.i("Added","Startpp" )
        if (myContext != null) {
            Log.i("Added","Starttt" )
            for (i in 1..3) {
                val newButton = SensorButton(myContext)
                newButton.setText("Name $i")

                params.setMargins(10, 10, 10, 10)
                newButton.layoutParams = params

//                newButton.setOnClickListener(View.OnClickListener {
//                    // your handler code here
//                    (activity as MainActivity).fragmentsShow("FragmentRoom")
//                })
                Log.i("Added","Name $i" )
                layoutScrollContainer?.addView(newButton)
            }
        }
        layoutScrollContainer?.invalidate()
    }

    fun testChange() {

    }
}