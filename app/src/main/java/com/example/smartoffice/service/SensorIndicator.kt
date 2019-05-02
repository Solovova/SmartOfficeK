package com.example.smartoffice.service

import java.util.*


class SensorIndicator  {
    private var indicatorValue: Double = 0.0
    private var alarmRange: DoubleArray = doubleArrayOf(0.0, 0.0)
    private var type: enIndicatorType

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
}