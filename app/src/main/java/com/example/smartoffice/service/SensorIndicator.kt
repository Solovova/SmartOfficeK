package com.example.smartoffice.service

import com.example.smartoffice.soviews.SensorIndicatorButton
import com.example.smartoffice.dataclass.EnumIndicatorsType



class SensorIndicator  {
    private var indicatorValue: Double
    var type: EnumIndicatorsType
    val sensor: Sensor


    private var alarmBorder: Array<Double>


    private var sensorIndicatorButton: SensorIndicatorButton? = null

    constructor(_type: EnumIndicatorsType, _sensor: Sensor) {
        this.sensor = _sensor
        this.type = _type
        val dataIndicatorTypeDef =  _sensor.sensorContainer.getDataIndicatorTypeDef(this.type)
        this.alarmBorder = dataIndicatorTypeDef.defAlarmBorder.clone()
        this.indicatorValue = dataIndicatorTypeDef.defValue
    }


    fun setIndicatorVal(_value: Double){
        this.indicatorValue = _value
    }

    fun testGenerateData() {

    }

    fun setLinkToSensorIndicatorButton(_sensorIndicatorButton: SensorIndicatorButton) {
        if (this.sensorIndicatorButton !=_sensorIndicatorButton) {
            this.sensorIndicatorButton = _sensorIndicatorButton
            _sensorIndicatorButton.setSensorIndicator(this)
        }
    }

    fun getAlarmCode():Int {
        val dataIndicatorTypeDef =  this.sensor.sensorContainer.getDataIndicatorTypeDef(this.type)
        when (dataIndicatorTypeDef.defTypeOfAlarm) {
            0 -> {
                if (this.indicatorValue <= this.alarmBorder[0]) return 1
                if (this.indicatorValue >= this.alarmBorder[1]) return 2
                return 0
            }
            1 -> {
                if (this.indicatorValue >= this.alarmBorder[1]) return 2
                if (this.indicatorValue >= this.alarmBorder[0]) return 1
                return 0
            }
            else -> return 0
        }
    }

    fun getIndicatorValue(): Double {
        return this.indicatorValue
    }






}