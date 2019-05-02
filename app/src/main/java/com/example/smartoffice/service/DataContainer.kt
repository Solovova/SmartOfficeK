package com.example.smartoffice.service

import android.app.Application

class DataContainer {
    var sensorContainer:SensorContainer?  = null
    var app: Application? = null
    constructor(_app: Application){
        this.app = _app
        this.sensorContainer = SensorContainer()
    }
}