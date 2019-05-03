package com.example.smartoffice.soviews

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.widget.TextView
import com.example.smartoffice.R
import com.example.smartoffice.service.Sensor

class SensorButton: ConstraintLayout  {
    var textMain: TextView
    private var sensor: Sensor? = null

    constructor(context: Context):super(context){
        inflate(context, R.layout.soview_sensor_button, this)
        this.textMain = findViewById(R.id.textMain)
    }

    fun setText(name: String){
        textMain.text = name
    }

    private fun refreshAllDataFromSensor() {
        val sensor = this.sensor
        if (sensor != null) {
            textMain.text = sensor.sensorName
        }

    }

    fun refreshValueDataFromSensor() {

    }

    fun setSensor(_sensor: Sensor) {
        this.sensor = _sensor
        this.refreshAllDataFromSensor()
    }
}