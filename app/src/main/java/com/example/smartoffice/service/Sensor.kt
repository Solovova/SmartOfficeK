package com.example.smartoffice.service

import android.widget.LinearLayout
import com.example.smartoffice.dataclass.EnumIndicatorsType
import com.example.smartoffice.fragments.FragmentSensor
import com.example.smartoffice.soviews.SensorButton
import com.example.smartoffice.soviews.SensorIndicatorButton
import com.example.smartoffice.test.TestDataRecordIndicator

class Sensor {
    var indicators = mutableListOf<SensorIndicator>()
    var sensorID: String = ""
    var sensorName: String = ""
    val sensorContainer: SensorContainer
    private var sensorButton: SensorButton? = null
    private var fragmentSensor: FragmentSensor? = null
    private var sensorIndicatorContainer: LinearLayout? = null


    constructor(_sensorID: String, _sensorContainer: SensorContainer) {
        this.sensorContainer = _sensorContainer
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

    fun setLinkToFragmentSensor(_fragmentSensor: FragmentSensor, _sensorIndicatorContainer: LinearLayout){
        // its call from onViewCreate of FragmentSensor
        if (this.fragmentSensor != _fragmentSensor || this.sensorIndicatorContainer != _sensorIndicatorContainer) {
            this.fragmentSensor = _fragmentSensor
            this.sensorIndicatorContainer = _sensorIndicatorContainer
            _fragmentSensor.setSensor(this)
            this.createSensorIndicatorButton()
        }
    }

    private fun createSensorIndicatorButton(){
        val sensorIndicatorContainer = this.sensorIndicatorContainer
        if (sensorIndicatorContainer != null) {
            if (sensorIndicatorContainer.childCount > 0) sensorIndicatorContainer.removeAllViews()
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            for (indicator in indicators) {
                val newButtonIndicator = SensorIndicatorButton(sensorIndicatorContainer.context)
                params.setMargins(5, 5, 5, 5)
                newButtonIndicator.layoutParams = params
                sensorIndicatorContainer.addView(newButtonIndicator)
                indicator.setLinkToSensorIndicatorButton(newButtonIndicator)
            }
            sensorIndicatorContainer.invalidate()
        }
    }

    fun testGenerateData(testSensorIndicatorType : Array<EnumIndicatorsType>?) {
        if (testSensorIndicatorType == null) return
        var sensorIndicator: SensorIndicator
        for (ind in 0 until testSensorIndicatorType.size) {
            sensorIndicator = SensorIndicator(testSensorIndicatorType[ind],this)
            sensorIndicator.testGenerateData()
            indicators.add(sensorIndicator)
        }
    }

    fun getAlarmState(type: EnumIndicatorsType): Int {
        var maxAlarm  = 0
        for (indicator in indicators){
            if (indicator.type == type) {
                val tmpAlarm = indicator.getAlarmCode()
                if (tmpAlarm > maxAlarm) maxAlarm = tmpAlarm
            }
        }
        return maxAlarm
    }

    fun eventDataIn(testDataRecordIndicator: TestDataRecordIndicator) {
        for (indicator in indicators) {
            if (indicator.type == testDataRecordIndicator.indicatorType) {
                indicator.eventDataIn(testDataRecordIndicator)
                break
            }
        }
    }

    fun onChangeSensorIndicator(){
        sensorButton?.refreshValue()
        sensorContainer.onChangeSensor()
    }
}