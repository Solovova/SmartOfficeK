package com.example.smartoffice.soviews

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.smartoffice.MainActivity
import com.example.smartoffice.R
import com.example.smartoffice.service.Sensor
import com.example.smartoffice.dataclass.EnumIndicatorsType

class SensorButton: ConstraintLayout  {
    private var textMain: TextView
    private var sensor: Sensor? = null
    private var imgBig: MutableList<ImageView>
    private var imgSmall: MutableList<ImageView>

    constructor(context: Context):super(context){
        inflate(context, R.layout.soview_sensor_button, this)
        this.textMain = findViewById(R.id.textMain)

        var tmpImage:ImageView

        imgBig= mutableListOf()
        tmpImage = findViewById(R.id.imageView0)
        imgBig.add(tmpImage)
        tmpImage = findViewById(R.id.imageView1)
        imgBig.add(tmpImage)
        tmpImage = findViewById(R.id.imageView2)
        imgBig.add(tmpImage)
        tmpImage = findViewById(R.id.imageView3)
        imgBig.add(tmpImage)

        imgSmall= mutableListOf()
        tmpImage = findViewById(R.id.imageView01)
        imgSmall.add(tmpImage)
        tmpImage = findViewById(R.id.imageView11)
        imgSmall.add(tmpImage)
        tmpImage = findViewById(R.id.imageView21)
        imgSmall.add(tmpImage)
        tmpImage = findViewById(R.id.imageView31)
        imgSmall.add(tmpImage)

        val onclickListener = OnClickListener {
            val sensor = this.sensor
            if (sensor!=null) {
                val fragmentName = "FragmentSensor_${sensor.sensorID}"
                (context as MainActivity).fragmentsShow(fragmentName, sensor)
            }
        }

        this.setOnClickListener(onclickListener)
    }

    private fun refreshValue() {
        val sensor = this.sensor
        if (sensor != null) {
            var tImgBig: ImageView
            var tImgSmall: ImageView
            var tAlarm: Int
            for (t_type in EnumIndicatorsType.values()) {
                val dataIndicatorTypeDef =  sensor.sensorContainer.getDataIndicatorTypeDef(t_type)
                tImgBig = imgBig[t_type.ordinal]
                tImgSmall = imgSmall[t_type.ordinal]
                tAlarm = sensor.getAlarmState(t_type)

                when (tAlarm) {
                    0 -> {
                        tImgBig.visibility = View.GONE
                        tImgSmall.visibility = View.GONE
                    }
                    1,2 -> {
                        tImgBig.visibility = View.VISIBLE
                        tImgSmall.visibility = View.VISIBLE
                        tImgSmall.background = ContextCompat.getDrawable(context, dataIndicatorTypeDef.defOnButtonAlarmIdImage[tAlarm])
                    }
                }

            }
        }
    }

    private fun refreshAll() {
        val sensor = this.sensor
        if (sensor != null) {
            textMain.text = sensor.sensorName
            refreshValue()
        }
    }

    fun setSensor(sensor: Sensor) {
        this.sensor = sensor
        this.refreshAll()
    }

    fun getSensor():Sensor? {
        return this.sensor
    }
}