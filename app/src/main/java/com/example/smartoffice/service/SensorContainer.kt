package com.example.smartoffice.service

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.example.smartoffice.soviews.SensorButton
import com.example.smartoffice.MainActivity



class SensorContainer {
    var sensors = mutableMapOf<String,Sensor>()
    private var layoutScrollContainer : LinearLayout? = null

    var app: Application? = null
    constructor(_app: Application){
        this.app = _app
    }

    fun setLayoutScrollContainer (linearLayout: LinearLayout) {
        // its call from onViewCreate of FragmentStart
        if (this.layoutScrollContainer != linearLayout) {
            this.layoutScrollContainer = linearLayout
            this.recreateSensorButtons()
        }
    }

    private fun recreateSensorButtons(){
        val layoutScrollContainer = this.layoutScrollContainer
        if (layoutScrollContainer != null) {
            if (layoutScrollContainer.childCount > 0) layoutScrollContainer.removeAllViews()
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            for (id in  sensors.keys) {
                val sensor: Sensor? = sensors[id]
                if (sensor != null){
                    val newButton = SensorButton(layoutScrollContainer.context)
                    sensor.setLinkToSensorButton(newButton)
                    params.setMargins(10, 30, 10, 30)
                    newButton.layoutParams = params
                    //Log.i("Added", sensor.sensorName)

                    layoutScrollContainer.addView(newButton)
                }
            }
            layoutScrollContainer.invalidate()
        }

    }

    fun testGenerateData() {
        val testSensorID: Array<String> = arrayOf("id123432", "id999797", "id999997")
        val testSensorName: Array<String> = arrayOf("Room 8", "Room 2", "Room 7")

        var testSensorIndicator = mutableMapOf<String,Array<enIndicatorType>>()
        testSensorIndicator[testSensorID[0]] = arrayOf(
            enIndicatorType.Temperature,
            enIndicatorType.Brightness,
            enIndicatorType.Co2,
            enIndicatorType.Humidity
            )

        testSensorIndicator[testSensorID[1]] = arrayOf(
            enIndicatorType.Temperature,
            enIndicatorType.Humidity
        )

        testSensorIndicator[testSensorID[2]] = arrayOf(
            enIndicatorType.Temperature,
            enIndicatorType.Humidity,
            enIndicatorType.Brightness
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