package com.example.smartoffice.service

import android.app.Application
import android.widget.LinearLayout
import com.example.smartoffice.dataclass.DataIndicatorTypeDef
import com.example.smartoffice.dataclass.EnumIndicatorsType
import com.example.smartoffice.soviews.SensorButton
import com.example.smartoffice.R
import android.os.SystemClock
import android.view.View
import com.example.smartoffice.SOApplication
import com.example.smartoffice.test.TestDataFlow
import com.example.smartoffice.test.TestDataRecordIndicator

class SensorContainer {
    var myThread: Thread? = null
    var sensors = mutableMapOf<String,Sensor>()
    var sensorIndicatorDef = mutableMapOf<EnumIndicatorsType,DataIndicatorTypeDef>()
    private var viewContainer : LinearLayout? = null
    private var testDataFlow: TestDataFlow

    var app: SOApplication? = null
    constructor(_app: SOApplication){
        this.app = _app
        this.testDataFlow = TestDataFlow()

        var tmpDataIndicatorTypeDef: DataIndicatorTypeDef

        //Temperature
        tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 20.0
            defAlarmBorder          = arrayOf(19.0, 26.0)
            defTypeOfAlarm          = 0
            defTextAlarm            = arrayOf("Excellent", "Too cold", "Too hot")
            defTextAlarmIdColor     = arrayOf(R.color.colorSOGreen, R.color.colorSOBlue, R.color.colorSORed)
            defTextAlarmIdImage     = arrayOf(0, R.drawable.ic_sensor_pic_blue, R.drawable.ic_sensor_pic_red)
            defOnButtonAlarmIdImage = arrayOf(0, R.drawable.ic_sensor_pic_blue, R.drawable.ic_sensor_pic_red)
            idBigPicture            = R.drawable.ic_sensor_temperature
            defFormatString         = "%.2f"
            defDescribeValue        = "℃"
            defDescribe             = "Temperature"
        }
        sensorIndicatorDef[EnumIndicatorsType.Temperature] = tmpDataIndicatorTypeDef

        //Brightness
        tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 400.0
            defAlarmBorder          = arrayOf(200.0, 600.0)
            defTypeOfAlarm          = 0
            defTextAlarm            = arrayOf("Excellent", "Too dark", "Too shine")
            defTextAlarmIdColor     = arrayOf(R.color.colorSOGreen, R.color.colorSOBlue, R.color.colorSORed)
            defTextAlarmIdImage     = arrayOf(0, R.drawable.ic_sensor_pic_blue, R.drawable.ic_sensor_pic_red)
            defOnButtonAlarmIdImage = arrayOf(0, R.drawable.ic_sensor_pic_blue, R.drawable.ic_sensor_pic_red)
            idBigPicture            = R.drawable.ic_sensor_brightness
            defFormatString         = "%.0f"
            defDescribeValue        = "lx"
            defDescribe             = "Brightness"
        }
        sensorIndicatorDef[EnumIndicatorsType.Brightness] = tmpDataIndicatorTypeDef

        //Co2
        tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 800.0
            defAlarmBorder          = arrayOf(1000.0, 1400.0)
            defTypeOfAlarm          = 1
            defTextAlarm            = arrayOf("Excellent", "A bit dirty", "Very dirty")
            defTextAlarmIdColor     = arrayOf(R.color.colorSOGreen, R.color.colorSOYellow, R.color.colorSORed)
            defTextAlarmIdImage     = arrayOf(0, R.drawable.ic_sensor_pic_yellow, R.drawable.ic_sensor_pic_red)
            defOnButtonAlarmIdImage = arrayOf(0, R.drawable.ic_sensor_pic_yellow, R.drawable.ic_sensor_pic_red)
            idBigPicture            = R.drawable.ic_sensor_co2
            defFormatString         = "%.0f"
            defDescribeValue        = "ppm"
            defDescribe             = "Co2"
        }
        sensorIndicatorDef[EnumIndicatorsType.Co2] = tmpDataIndicatorTypeDef

        //Humidity
        tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 50.0
            defAlarmBorder          = arrayOf(70.0, 90.0)
            defTypeOfAlarm          = 1
            defTextAlarm            = arrayOf("Excellent", "Too wet", "Very wet")
            defTextAlarmIdColor     = arrayOf(R.color.colorSOGreen, R.color.colorSOYellow, R.color.colorSORed)
            defTextAlarmIdImage     = arrayOf(0, R.drawable.ic_sensor_pic_yellow, R.drawable.ic_sensor_pic_red)
            defOnButtonAlarmIdImage = arrayOf(0, R.drawable.ic_sensor_pic_yellow, R.drawable.ic_sensor_pic_red)
            idBigPicture            = R.drawable.ic_sensor_humidity
            defFormatString         = "%.0f"
            defDescribeValue        = "%"
            defDescribe             = "Humidity"
        }
        sensorIndicatorDef[EnumIndicatorsType.Humidity] = tmpDataIndicatorTypeDef

        this.myThread = Thread(
            Runnable {
                while (true) {
                    app?.mainActivity?.runOnUiThread( Runnable {
                                                 this.eventDataIn(this.testDataFlow.getNextTestRecord())
                    })
                    SystemClock.sleep(1000)
                }
            }
        )
        this.myThread?.start()
    }

    fun setViewContainer (_viewContainer: LinearLayout) {
        // its call from onViewCreate of FragmentStart
        if (this.viewContainer != _viewContainer) {
            this.viewContainer = _viewContainer
            this.createSensorButtons()
        }
    }

    private fun createSensorButtons(){
        //ToDo Add sort by name and favorites
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

    fun getDataIndicatorTypeDef(_type: EnumIndicatorsType):DataIndicatorTypeDef {
        val dataIndicatorTypeDef = sensorIndicatorDef[_type]
        dataIndicatorTypeDef ?: return DataIndicatorTypeDef()
        return dataIndicatorTypeDef
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
            sensor = Sensor(testSensorID[ind], this)
            sensor.setName(testSensorName[ind])
            sensor.testGenerateData(testSensorIndicator[testSensorID[ind]])
            sensors[testSensorID[ind]] = sensor
        }
    }

    fun eventDataIn(testDataRecordIndicator: TestDataRecordIndicator) {
        val sensor = this.sensors[testDataRecordIndicator.sensorId]
        sensor?.eventDataIn(testDataRecordIndicator)
    }

    fun onChangeSensor(){

    }
}