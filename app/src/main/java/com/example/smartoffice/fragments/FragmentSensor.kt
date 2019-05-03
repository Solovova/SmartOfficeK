package com.example.smartoffice.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.smartoffice.R
import com.example.smartoffice.service.Sensor

class FragmentSensor : Fragment() {
    private var sensor:Sensor? = null
    private var textViewSensorName: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_sensor, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentSensor().apply {
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewSensorName = view.findViewById(R.id.textHeadSensorName)

        this.sensor?.setLinkToFragmentSensor(this, view.findViewById(R.id.SensorIndicatorContainer))

        Log.i("SHOW","on onViewCreated ${this.sensor?.sensorID}")
    }

    private fun refreshAll() {
        val sensor = this.sensor
        if (sensor != null) {
            val textViewSensorName = this.textViewSensorName
            if (textViewSensorName != null) textViewSensorName.text = sensor.sensorName
        }
    }

    fun setSensor(_sensor:Sensor){
        this.sensor = _sensor
        this.refreshAll()
    }
}
