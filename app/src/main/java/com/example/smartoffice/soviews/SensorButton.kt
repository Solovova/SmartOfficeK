package com.example.smartoffice.soviews

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.widget.TextView
import com.example.smartoffice.MainActivity
import com.example.smartoffice.R
import com.example.smartoffice.service.Sensor

class SensorButton: ConstraintLayout  {
    private var textMain: TextView
    private var sensor: Sensor? = null

    constructor(context: Context):super(context){
        inflate(context, R.layout.soview_sensor_button, this)
        this.textMain = findViewById(R.id.textMain)

        val onclickListener = OnClickListener {
            val sensor = this.sensor
            if (sensor!=null) {
                val fragmentName = "FragmentSensor_${sensor.sensorID}"
                (context as MainActivity).fragmentsShow(fragmentName, sensor)
            }
        }

        this.setOnClickListener(onclickListener)
    }

    fun refreshValue() {

    }

    fun setSensor(_sensor: Sensor) {
        this.sensor = _sensor
        textMain.text = _sensor.sensorName
    }

    fun getSensor():Sensor? {
        return this.sensor
    }
}