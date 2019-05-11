//ToDo Add room name editable

package com.example.smartoffice.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.smartoffice.MainActivity

import com.example.smartoffice.R
import com.example.smartoffice.service.Sensor

class FragmentSensor : FragmentParent() {
    private var sensor:Sensor? = null
    private var textViewSensorName: TextView? = null
    private var imageViewSensorFavorite: ImageView? = null

    private var imageViewFace: ImageView? = null
    private var textViewAlarm: TextView? = null

    private var buttonEdit: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup? ,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sensor, container, false)
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
        imageViewSensorFavorite = view.findViewById(R.id.imageView_star)

        imageViewFace = view.findViewById(R.id.imageViewFace)
        textViewAlarm = view.findViewById(R.id.textHeadAlarm)


        this.sensor?.setLinkToFragmentSensor(this, view.findViewById(R.id.SensorIndicatorContainer))

        val onClickListenerEdit = View.OnClickListener { _ ->
            (activity as MainActivity).fragmentsShow("FragmentEditSensor", this.sensor)
        }

        this.buttonEdit = view.findViewById(R.id.imageView_star)
        this.buttonEdit?.setOnClickListener(onClickListenerEdit)

        Log.i("SHOW","on onViewCreated ${this.sensor?.sensorID}")
    }

    fun refreshHead() {
        val sensor = this.sensor
        if (sensor != null) {
            val textViewSensorName = this.textViewSensorName
            if (textViewSensorName != null) textViewSensorName.text = sensor.sensorName


            val imageViewFace = this.imageViewFace
            val textViewAlarm = this.textViewAlarm

            if (imageViewFace != null && textViewAlarm != null) {
                val tAlarm = sensor.getAlarmState()
                val tHeadFace = arrayOf(R.drawable.normal_face,R.drawable.tired_face,R.drawable.exhaustion_face)
                val tHeadAlarm = arrayOf("Everything looks good","Poor","Unhealthy")
                imageViewFace.setImageResource(tHeadFace[tAlarm])
                textViewAlarm.text = tHeadAlarm[tAlarm]
            }
        }
    }

    fun setSensor(_sensor:Sensor){
        this.sensor = _sensor



        this.refreshHead()
    }

    fun getSensor():Sensor? {
        return this.sensor
    }
}
