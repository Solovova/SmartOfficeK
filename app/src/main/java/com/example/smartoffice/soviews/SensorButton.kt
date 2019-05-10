package com.example.smartoffice.soviews

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.smartoffice.MainActivity
import com.example.smartoffice.R
import com.example.smartoffice.service.Sensor
import com.example.smartoffice.dataclass.EnumIndicatorsType

class SensorButton: ConstraintLayout {
    private var textMain: TextView
    private var buttonMain: Button
    private var buttonDel: Button

    private var sensor: Sensor? = null
    private var imgBig: MutableList<ImageView>
    private var imgSmall: MutableList<ImageView>

    private var onTouchListenerDownX:Float = 0.0f
    private var onTouchListenerDownButtonStartPos:Float = 0.0f
    private var buttonDelShow:Boolean = false


    constructor(context: Context):super(context){
        inflate(context, R.layout.soview_sensor_button, this)
        this.textMain = findViewById(R.id.textMain)
        this.buttonMain = findViewById(R.id.button)
        this.buttonDel = findViewById(R.id.buttonDel)


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

        val onTouchListener = OnTouchListener { _, motionEvent ->
            when(motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                    //Log.i("DRAG","ACTION_DOWN ${motionEvent.rawX} ${motionEvent.rawY}")
                    this.onTouchListenerDownX = motionEvent.rawX
                    this.onTouchListenerDownButtonStartPos = this.buttonDel.x
                }
                MotionEvent.ACTION_UP -> {
                    //Log.i("DRAG","ACTION_UP ${motionEvent.rawX} ${motionEvent.rawY}")
                    if ((this.onTouchListenerDownX - motionEvent.rawX) > 20) {
                        Log.i("DRAG","Slide left")
                        if (!this.buttonDelShow) {
                            this.buttonDel.x = this.onTouchListenerDownButtonStartPos - this.buttonDel.width
                            this.buttonDelShow = true
                        }else{
                            this.buttonDel.x = this.onTouchListenerDownButtonStartPos
                        }


                    } else if ((this.onTouchListenerDownX - motionEvent.rawX) < -20) {
                        Log.i("DRAG","Slide right")
                        if (this.buttonDelShow) {
                            this.buttonDel.x = this.onTouchListenerDownButtonStartPos + this.buttonDel.width
                            this.buttonDelShow = false
                            refreshValue()
                        }else{
                            this.buttonDel.x = this.onTouchListenerDownButtonStartPos
                        }
                    } else {
                        Log.i("DRAG","Click")
                        this.buttonDel.x = this.onTouchListenerDownButtonStartPos
                        val sensor = this.sensor
                        if (sensor!=null) {
                            val fragmentName = "FragmentSensor_${sensor.sensorID}"
                            (context as MainActivity).fragmentsShow(fragmentName, sensor)
                        }
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.i("DRAG","ACTION_MOVE ${motionEvent.rawX} ${motionEvent.rawY}")
                    if ((this.buttonDelShow && this.onTouchListenerDownX < motionEvent.rawX) ||
                            (!this.buttonDelShow && this.onTouchListenerDownX > motionEvent.rawX)){

                        hideAlarm()
                        if ((this.onTouchListenerDownX - motionEvent.rawX) < this.buttonDel.width)
                            this.buttonDel.x = this.onTouchListenerDownButtonStartPos - (this.onTouchListenerDownX - motionEvent.rawX)
                    }
                }
            }
            return@OnTouchListener true
        }

        val onClickListenerDel = OnClickListener { _ ->
            val sensor = this.sensor
            if (sensor != null) {
                val sensorContainer = sensor.sensorContainer
                sensorContainer.deleteSensor(sensor)
            }
            return@OnClickListener
        }

        this.buttonMain.setOnTouchListener(onTouchListener)
        this.buttonDel.setOnClickListener(onClickListenerDel)
    }

    private fun hideAlarm() {
        val sensor = this.sensor

        if (sensor != null) {
            var tImgBig: ImageView
            var tImgSmall: ImageView
            for (t_type in EnumIndicatorsType.values()) {
                tImgBig = imgBig[t_type.ordinal]
                tImgSmall = imgSmall[t_type.ordinal]
                tImgBig.visibility = View.GONE
                tImgSmall.visibility = View.GONE
            }
        }
    }

    fun refreshValue() {
        if (this.buttonDelShow) return
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