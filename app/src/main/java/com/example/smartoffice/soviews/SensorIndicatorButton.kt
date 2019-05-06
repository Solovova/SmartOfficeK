package com.example.smartoffice.soviews


import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
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

    private fun refreshValue() {
        val sensorIndicator = this.sensorIndicator
        if (sensorIndicator != null) {
            val alarmStay = sensorIndicator.getAlarmStay()
            visualTextValue.text = sensorIndicator.getValueText()
            if (alarmStay.icon_visible) {
                this.visualAlarmImage.visibility = View.VISIBLE
                this.visualAlarmText.visibility = View.VISIBLE
                this.visualAlarmTextEx.visibility = View.GONE
                this.visualAlarmImage.background = ContextCompat.getDrawable(context, alarmStay.icon_id_pic)
                this.visualAlarmText.text = alarmStay.text
                this.visualAlarmText.setTextColor(ContextCompat.getColor(context, alarmStay.text_color_id))
            }else{
                this.visualAlarmImage.visibility = View.GONE
                this.visualAlarmText.visibility = View.GONE
                this.visualAlarmTextEx.visibility = View.VISIBLE
                this.visualAlarmTextEx.text = alarmStay.text
                this.visualAlarmTextEx.setTextColor(ContextCompat.getColor(context, alarmStay.text_color_id))
            }

        }
    }

    private fun refreshAll() {
        val sensorIndicator = this.sensorIndicator
        if (sensorIndicator != null) {
            this.visualTextViewUp.text = sensorIndicator.type.toString()
            this.visualImageBig.setBackgroundResource(sensorIndicator.visualGetMainImage())
            this.visualTextValueSign.text = sensorIndicator.getValueSign()
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