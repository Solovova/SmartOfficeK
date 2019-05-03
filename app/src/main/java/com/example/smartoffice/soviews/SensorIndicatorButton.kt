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

    private fun refreshAll() {
        val sensorIndicator = this.sensorIndicator
        if (sensorIndicator != null) {
            this.textMain.text = sensorIndicator.type.toString()
        }
    }

    fun setSensorIndicator(_sensorIndicator: SensorIndicator) {
        if (this.sensorIndicator != _sensorIndicator) {
            this.sensorIndicator = _sensorIndicator
            this.refreshAll()
        }
    }
}