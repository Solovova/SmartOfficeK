package com.example.smartoffice.service

import com.example.smartoffice.soviews.SensorIndicatorButton
import com.example.smartoffice.R


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

    fun visualGetMainImage():Int {
        when (this.type) {
            enIndicatorType.Humidity -> return R.drawable.ic_sensor_humidity
            enIndicatorType.Co2 -> return R.drawable.ic_sensor_co2
            enIndicatorType.Brightness -> return R.drawable.ic_sensor_sun
            enIndicatorType.Temperature -> return R.drawable.ic_sensor_temperature
            else -> return return R.drawable.ic_sensor_temperature
        }
    }
}