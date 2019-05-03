package com.example.smartoffice.soviews

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.widget.TextView
import com.example.smartoffice.R
import com.example.smartoffice.service.Sensor
import com.example.smartoffice.MainActivity
import android.view.View




class SensorButton: ConstraintLayout  {
    var textMain: TextView
    private var sensor: Sensor? = null

    constructor(context: Context):super(context){
        inflate(context, R.layout.soview_sensor_button, this)
        this.textMain = findViewById(R.id.textMain)
    }

    fun refreshValue() {

    }

    fun setSensor(_sensor: Sensor) {
        this.sensor = _sensor
        textMain.text = _sensor.sensorName
    }
}