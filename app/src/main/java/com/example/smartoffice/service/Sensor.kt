package com.example.smartoffice.service

import android.app.Application
import android.view.View
import com.example.smartoffice.R
import com.example.smartoffice.fragments.FragmentSensor
import com.example.smartoffice.soviews.SensorButton

class Sensor {
    var indicators = mutableListOf<SensorIndicator>()
    var sensorID: String = ""
    var sensorName: String = ""
    private var sensorButton: SensorButton? = null
    private var fragmentSensor: FragmentSensor? = null


    constructor(_sensorID: String) {
        this.sensorID = _sensorID
    }

    fun setName(_sensorName:String){
        this.sensorName = _sensorName
    }

    fun setLinkToSensorButton(sensorButton: SensorButton){
        //its call when sensorButton object create
        this.sensorButton = sensorButton
        sensorButton.setSensor(this)
    }

    fun setLinkToFragmentSensor(_fragmentSensor: FragmentSensor){
        // its call from onViewCreate of FragmentSensor
        if (this.fragmentSensor != _fragmentSensor) {
            this.fragmentSensor = _fragmentSensor
            this.recreateSensorIndicatorButton()
        }
    }

    private fun recreateSensorIndicatorButton(){
        //ToDo create a indicators in

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