package com.example.smartoffice

import android.app.Application
import com.example.smartoffice.service.DataContainer

class SOApplication : Application() {

    private var dataContainer: DataContainer? = null

    override fun onCreate() {
        super.onCreate()
        dataContainer = DataContainer(this)
    }
}