package com.example.smartoffice.soviews

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.widget.TextView
import com.example.smartoffice.R

class SensorButton: ConstraintLayout  {
    var textMain: TextView
    constructor(context: Context):super(context){
        inflate(context, R.layout.soview_sensor_button, this)
        this.textMain = findViewById(R.id.textMain)
    }

    fun setText(name: String){
        textMain.text = name
    }
}