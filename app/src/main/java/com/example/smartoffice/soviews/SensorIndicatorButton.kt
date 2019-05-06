package com.example.smartoffice.soviews


import android.content.Context
import android.support.constraint.ConstraintLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.smartoffice.R
import com.example.smartoffice.service.SensorIndicator

class SensorIndicatorButton: ConstraintLayout  {
    private var visualTextViewUp: TextView
    private var visualImageBig: ImageView

    private var sensorIndicator: SensorIndicator? = null

    constructor(context: Context):super(context){
        inflate(context, R.layout.soview_sensor_indicator_button, this)
        this.visualTextViewUp = findViewById(R.id.textView_Up)
        this.visualImageBig = findViewById(R.id.imageView)
    }

    fun refreshValue() {

    }

    private fun refreshAll() {
        val sensorIndicator = this.sensorIndicator
        if (sensorIndicator != null) {
            this.visualTextViewUp.text = sensorIndicator.type.toString()
            this.visualImageBig.setBackgroundResource(sensorIndicator.visualGetMainImage())

        }
    }

    fun setSensorIndicator(_sensorIndicator: SensorIndicator) {
        if (this.sensorIndicator != _sensorIndicator) {
            this.sensorIndicator = _sensorIndicator
            this.refreshAll()
        }
    }


}