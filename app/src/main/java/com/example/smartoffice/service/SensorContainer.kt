package com.example.smartoffice.service

import android.app.Application
import android.widget.LinearLayout
import com.example.smartoffice.soviews.SensorButton


class SensorContainer {
    var sensors = mutableMapOf<String,Sensor>()
    private var viewContainer : LinearLayout? = null

    var app: Application? = null
    constructor(_app: Application){
        this.app = _app
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