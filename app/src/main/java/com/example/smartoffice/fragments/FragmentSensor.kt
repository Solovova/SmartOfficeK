package com.example.smartoffice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.smartoffice.MainActivity

import com.example.smartoffice.R
import com.example.smartoffice.service.Sensor

class FragmentSensor : FragmentParent() {
    private var textViewSensorName: TextView? = null
    private var imageViewSensorFavorite: ImageView? = null

    private var imageViewFace: ImageView? = null
    private var textViewAlarm: TextView? = null

    private var buttonEdit: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup? ,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sensor, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentSensor()
    }

    override fun onShow() {
        super.onShow()
        val view = this.view
        if(view != null) {
            textViewSensorName = view.findViewById(R.id.textHeadSensorName)
            imageViewSensorFavorite = view.findViewById(R.id.imageView_star)

            imageViewFace = view.findViewById(R.id.imageViewFace)
            textViewAlarm = view.findViewById(R.id.textHeadAlarm)

            this.sensor?.setLinkToView(this, view.findViewById(R.id.SensorIndicatorContainer))

            val onClickListenerEdit = View.OnClickListener {
                (activity as MainActivity).fragmentsShow("FragmentSensorEdit", sensor = this.sensor)
            }

            this.buttonEdit = view.findViewById(R.id.imageView_star)
            this.buttonEdit?.setOnClickListener(onClickListenerEdit)

            this.refreshHead()
        }
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
}
