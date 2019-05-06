package com.example.smartoffice.service

import android.app.Application
import android.widget.LinearLayout
import com.example.smartoffice.dataclass.DataIndicatorTypeDef
import com.example.smartoffice.dataclass.EnumIndicatorsType
import com.example.smartoffice.soviews.SensorButton
import com.example.smartoffice.R


class SensorContainer {
    var sensors = mutableMapOf<String,Sensor>()
    var sensorIndicatorDef = mutableMapOf<EnumIndicatorsType,DataIndicatorTypeDef>()
    private var viewContainer : LinearLayout? = null

    var app: Application? = null
    constructor(_app: Application){
        this.app = _app

        var tmpDataIndicatorTypeDef: DataIndicatorTypeDef

        //Temperature
        tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 20.0
            defAlarmBorder          = Array(2){19.0; 26.0}
            defTypeOfAlarm          = 0
            defTextAlarm            = Array(3) {"Excellent"; "Too cold"; "Too hot"}
            defTextAlarmIdColor     = Array(3) {R.color.colorSOGreen; R.color.colorSOBlue; R.color.colorSORed}
            defTextAlarmIdImage     = Array(3) {0; R.drawable.ic_sensor_pic_blue; R.drawable.ic_sensor_pic_red}
            defOnButtonAlarmIdImage = Array(3) {0; R.drawable.ic_sensor_pic_blue; R.drawable.ic_sensor_pic_red}
            idBigPicture            = R.drawable.ic_sensor_temperature
            defFormatString         = "%.0f"
            defDescribeValue        = "℃"
            defDescribe             = "Temperature"
        }
        sensorIndicatorDef[EnumIndicatorsType.Temperature] = tmpDataIndicatorTypeDef

        //Brightness
        tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {

        }
        sensorIndicatorDef[EnumIndicatorsType.Brightness] = tmpDataIndicatorTypeDef

        //Co2
        tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {

        }
        sensorIndicatorDef[EnumIndicatorsType.Co2] = tmpDataIndicatorTypeDef

        //Humidity
        tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {

        }
        sensorIndicatorDef[EnumIndicatorsType.Humidity] = tmpDataIndicatorTypeDef
    }

    fun setViewContainer (_viewContainer: LinearLayout) {
        // its call from onViewCreate of FragmentStart
        if (this.viewContainer != _viewContainer) {
            this.viewContainer = _viewContainer
            this.createSensorButtons()
        }
    }

    private fun createSensorButtons(){
        val viewContainer = this.viewContainer
        if (viewContainer != null) {
            if (viewContainer.childCount > 0) viewContainer.removeAllViews()
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            for (id in  sensors.keys) {
                val sensor: Sensor? = sensors[id]
                if (sensor != null){
                    val newButton = SensorButton(viewContainer.context)
                    params.setMargins(10, 30, 10, 30)
                    newButton.layoutParams = params
                    viewContainer.addView(newButton)
                    sensor.setLinkToSensorButton(newButton)
                }
            }
            viewContainer.invalidate()
        }
    }

    fun testGenerateData() {
        val testSensorID: Array<String> = arrayOf("id123432", "id999797", "id999997")
        val testSensorName: Array<String> = arrayOf("Room 8", "Room 2", "Room 7")

        var testSensorIndicator = mutableMapOf<String,Array<EnumIndicatorsType>>()
        testSensorIndicator[testSensorID[0]] = arrayOf(
            EnumIndicatorsType.Temperature,
            EnumIndicatorsType.Brightness,
            EnumIndicatorsType.Co2,
            EnumIndicatorsType.Humidity
            )

        testSensorIndicator[testSensorID[1]] = arrayOf(
            EnumIndicatorsType.Temperature,
            EnumIndicatorsType.Humidity
        )

        testSensorIndicator[testSensorID[2]] = arrayOf(
            EnumIndicatorsType.Temperature,
            EnumIndicatorsType.Humidity,
            EnumIndicatorsType.Brightness
        )


        var sensor: Sensor?
        for (ind in 0 until testSensorID.size) {
            sensor = Sensor(testSensorID[ind])
            sensor.setName(testSensorName[ind])
            sensor.testGenerateData(testSensorIndicator[testSensorID[ind]])
            sensors[testSensorID[ind]] = sensor
        }
    }
}