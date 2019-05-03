package com.example.smartoffice.service

import android.app.Application
import android.view.View
import com.example.smartoffice.R
import com.example.smartoffice.soviews.SensorButton

class Sensor {
    var indicators = mutableListOf<SensorIndicator>()
    var sensorID: String = ""
    var sensorName: String = ""
    var sensorButton: SensorButton? = null

    constructor(_sensorID: String) {
        this.sensorID = _sensorID
    }

    fun setName(_sensorName:String){
        this.sensorName = _sensorName
    }

    fun setLinkToView(_sensorButton: SensorButton){
        this.sensorButton = _sensorButton
        _sensorButton.setSensor(this)
    }

    fun testGenerateData(testSensorIndicatorType : Array<enIndicatorType>?) {
        if (testSensorIndicatorType == null) return
        var sensorIndicator: SensorIndicator
        for (ind in 0 until testSensorIndicatorType.size) {
            sensorIndicator = SensorIndicator(testSensorIndicatorType[ind])
            sensorIndicator.testGenerateData()
            indicators.add(sensorIndicator)
        }
    }
}