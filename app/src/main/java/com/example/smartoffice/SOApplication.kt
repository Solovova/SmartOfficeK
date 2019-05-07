package com.example.smartoffice

import android.app.Activity
import android.app.Application
import com.example.smartoffice.service.SensorContainer

class SOApplication : Application() {
    var sensorContainer = SensorContainer(this)
    var mainActivity: Activity? = null

    override fun onCreate() {
        super.onCreate()
        this.sensorContainer.testGenerateData()
    }
}