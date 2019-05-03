package com.example.smartoffice.service

import com.example.smartoffice.soviews.SensorIndicatorButton
import java.util.*


class SensorIndicator  {
    private var indicatorValue: Double = 0.0
    private var alarmRange: DoubleArray = doubleArrayOf(0.0, 0.0)
    var type: enIndicatorType

    private var sensorIndicatorButton: SensorIndicatorButton? = null

    constructor(_type: enIndicatorType) {
        type = _type
        when (type) {
            enIndicatorType.Brightness -> {
                alarmRange[0] = 1.0
                alarmRange[1] = 1.0
            }
        }
    }


    fun setIndicatorVal(_value: Double){
        this.indicatorValue = _value
    }

    fun testGenerateData() {

    }

    fun nextTestData() {

    }

    fun setLinkToSensorIndicatorButton(_sensorIndicatorButton: SensorIndicatorButton) {
        if (this.sensorIndicatorButton !=_sensorIndicatorButton) {
            this.sensorIndicatorButton = _sensorIndicatorButton
            _sensorIndicatorButton.setSensorIndicator(this)
        }
    }
}