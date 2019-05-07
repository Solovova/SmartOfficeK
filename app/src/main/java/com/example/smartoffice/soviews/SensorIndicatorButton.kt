package com.example.smartoffice.soviews


import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.smartoffice.R
import com.example.smartoffice.service.SensorIndicator

class SensorIndicatorButton: ConstraintLayout  {
    private var visualTextViewUp: TextView
    private var visualImageBig: ImageView
    private var visualAlarmImage: ImageView
    private var visualAlarmText: TextView
    private var visualAlarmTextEx: TextView
    private var visualTextValue: TextView
    private var visualTextValueSign: TextView

    private var sensorIndicator: SensorIndicator? = null

    constructor(context: Context):super(context){
        inflate(context, R.layout.soview_sensor_indicator_button, this)
        this.visualTextViewUp = findViewById(R.id.textView_Up)
        this.visualImageBig = findViewById(R.id.imageView)
        this.visualAlarmImage = findViewById(R.id.imageView3)
        this.visualAlarmText = findViewById(R.id.textView)
        this.visualAlarmTextEx = findViewById(R.id.textViewEx)
        this.visualTextValue = findViewById(R.id.textView_0)
        this.visualTextValueSign = findViewById(R.id.textView_1)
    }

    fun refreshValue() {
        val sensorIndicator = this.sensorIndicator
        if (sensorIndicator != null) {
            val dataIndicatorTypeDef =  sensorIndicator.sensor.sensorContainer.getDataIndicatorTypeDef(sensorIndicator.type)
            visualTextValue.text = String.format(dataIndicatorTypeDef.defFormatString, sensorIndicator.getIndicatorValue())
            val alarmCode = sensorIndicator.getAlarmCode()
            if (alarmCode != 0) {
                this.visualAlarmImage.visibility = View.VISIBLE
                this.visualAlarmText.visibility = View.VISIBLE
                this.visualAlarmTextEx.visibility = View.GONE
                this.visualAlarmText.setTextColor(ContextCompat.getColor(context, dataIndicatorTypeDef.defTextAlarmIdColor[alarmCode]))
                this.visualAlarmText.text = dataIndicatorTypeDef.defTextAlarm[alarmCode]
                this.visualAlarmImage.setBackgroundResource(dataIndicatorTypeDef.defTextAlarmIdImage[alarmCode])
            }else{
                this.visualAlarmImage.visibility = View.GONE
                this.visualAlarmText.visibility = View.GONE
                this.visualAlarmTextEx.visibility = View.VISIBLE
                this.visualAlarmTextEx.setTextColor(ContextCompat.getColor(context, dataIndicatorTypeDef.defTextAlarmIdColor[alarmCode]))
                this.visualAlarmTextEx.text = dataIndicatorTypeDef.defTextAlarm[alarmCode]
            }
        }
    }

    private fun refreshAll() {
        val sensorIndicator = this.sensorIndicator
        if (sensorIndicator != null) {
            val dataIndicatorTypeDef =  sensorIndicator.sensor.sensorContainer.getDataIndicatorTypeDef(sensorIndicator.type)
            this.visualTextViewUp.text = dataIndicatorTypeDef.defDescribe
            this.visualImageBig.setImageResource(dataIndicatorTypeDef.idBigPicture)
            this.visualTextValueSign.text = dataIndicatorTypeDef.defDescribeValue
            refreshValue()
        }
    }

    fun setSensorIndicator(_sensorIndicator: SensorIndicator) {
        if (this.sensorIndicator != _sensorIndicator) {
            this.sensorIndicator = _sensorIndicator
            this.refreshAll()
        }
    }


}