package com.example.smartoffice.service

import com.example.smartoffice.soviews.SensorIndicatorButton
import com.example.smartoffice.dataclass.EnumIndicatorsType
import com.example.smartoffice.test.TestDataRecordIndicator
import android.os.SystemClock
import com.example.smartoffice.dataclass.DataIndicatorTypeDef


class SensorIndicator(_type: EnumIndicatorsType, _sensor: Sensor) {
    private var indicatorValue: Double
    private var indicatorOldValue: Double
    private var indicatorValueTime: Long
    var dataIndicatorTypeDef: DataIndicatorTypeDef

    var type: EnumIndicatorsType = _type
    val sensor: Sensor = _sensor


    private var alarmBorder: Array<Double>


    private var sensorIndicatorButton: SensorIndicatorButton? = null

    init {
        this.dataIndicatorTypeDef =  _sensor.sensorContainer.getDataIndicatorTypeDef(this.type)
        this.alarmBorder = dataIndicatorTypeDef.defAlarmBorder.clone()
        this.indicatorValue = dataIndicatorTypeDef.defValue
        this.indicatorOldValue = 0.0
        this.indicatorValueTime = 0
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

    private fun setIndicatorValue(_value: Double){
        this.indicatorOldValue = this.indicatorValue
        this.indicatorValueTime = SystemClock.currentThreadTimeMillis()
        this.indicatorValue = _value
        sensorIndicatorButton?.refreshValue()
        sensor.onChangeSensorIndicator()
    }

    fun eventDataIn(testDataRecordIndicator: TestDataRecordIndicator) {
        this.setIndicatorValue(testDataRecordIndicator.indicatorValue)
    }
}