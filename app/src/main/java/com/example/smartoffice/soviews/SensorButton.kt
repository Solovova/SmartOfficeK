package com.example.smartoffice.soviews

import android.content.Context
import android.text.Editable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.smartoffice.MainActivity
import com.example.smartoffice.R
import com.example.smartoffice.service.Sensor
import com.example.smartoffice.dataclass.EnumIndicatorsType

class SensorButton(context: Context) : ConstraintLayout(context) {
    private var textMain: TextView
    private var buttonMain: Button
    private var buttonDel: Button

    private var sensor: Sensor? = null
    private var imgBig: MutableList<ImageView>
    private var imgSmall: MutableList<ImageView>

    init {
        inflate(context, R.layout.soview_sensor_button, this)
        this.textMain = findViewById(R.id.textMain)
        this.buttonMain = findViewById(R.id.button)
        this.buttonDel = findViewById(R.id.buttonDel)

        imgBig= mutableListOf()
        var tmpImage:ImageView =  findViewById(R.id.imageView0)
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
        val onClickListenerDel = OnClickListener {
            val sensor = this.sensor
            if (sensor != null) {
                val sensorContainer = sensor.sensorContainer
                sensorContainer.deleteSensor(sensor)
                (context as MainActivity).showStartScreen()
            }
            return@OnClickListener
        }
        this.buttonDel.setOnClickListener(onClickListenerDel)
        val onClickListenerMain = OnClickListener {
            (context as MainActivity).fragmentsShow("FragmentSensor", sensor = sensor)
            return@OnClickListener
        }
        this.buttonMain.setOnClickListener(onClickListenerMain)
    }

    fun refreshValue() {

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
                        tImgSmall.visibility = View.GONE
                        tImgSmall.background = ContextCompat.getDrawable(context, dataIndicatorTypeDef.defOnButtonAlarmIdImage[tAlarm])
                    }
                }
            }

            //Refresh describe
            val tAlarmMain = sensor.getAlarmState()
            val tHeadAlarm = arrayOf("Everything looks good","Poor","Unhealthy")
            val tHeadColor = arrayOf(R.color.colorSOGreen,R.color.colorSOYellow,R.color.colorSORed)
            val textViewExBut = findViewById<TextView>(R.id.textViewExBut)
            textViewExBut.text = Editable.Factory.getInstance().newEditable(tHeadAlarm[tAlarmMain])
            textViewExBut.setTextColor(ContextCompat.getColor(context, tHeadColor[tAlarmMain]))


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
}