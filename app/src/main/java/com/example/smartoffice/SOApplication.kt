package com.example.smartoffice

import android.app.Application
import com.example.smartoffice.service.SensorContainer

class SOApplication : Application() {
    var sensorContainer = SensorContainer(this)
    override fun onCreate() {
        super.onCreate()
        this.sensorContainer.testGenerateData()
    }
}