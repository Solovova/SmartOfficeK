package com.example.smartoffice.soviews


import android.content.Context
import android.support.constraint.ConstraintLayout
import android.widget.TextView
import com.example.smartoffice.R
import com.example.smartoffice.service.SensorIndicator

class SensorIndicatorButton: ConstraintLayout  {
    var textMain: TextView
    private var sensorIndicator: SensorIndicator? = null

    constructor(context: Context):super(context){
        inflate(context, R.layout.soview_sensor_indicator_button, this)
        this.textMain = findViewById(R.id.textMain)
    }

    fun refreshValue() {

    }

    fun setSensor(_sensorIndicator: SensorIndicator) {
        this.sensorIndicator = _sensorIndicator
        //textMain.text = _sensorIndicator.
    }
}