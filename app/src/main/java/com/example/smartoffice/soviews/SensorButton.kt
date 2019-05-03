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

    private fun refreshValue() {
        val sensor = this.sensor
        if (sensor != null) {
            // ----
        }
    }

    private fun refreshAll() {
        val sensor = this.sensor
        if (sensor != null) {
            textMain.text = sensor.sensorName
        }
    }

    fun setSensor(sensor: Sensor) {
        this.sensor = sensor
        this.refreshAll()
    }

    fun getSensor():Sensor? {
        return this.sensor
    }
}