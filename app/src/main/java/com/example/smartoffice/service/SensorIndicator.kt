package com.example.smartoffice.service

import android.util.Log
import com.example.smartoffice.soviews.SensorIndicatorButton
import com.example.smartoffice.R

data class AlarmSensorIndicator(val icon_visible: Boolean,
                                val icon_id_pic: Int,
                                val text: String,
                                val text_color_id: Int)

class SensorIndicator  {
    private var indicatorValue: Double = 0.0
    private var alarmRange: DoubleArray = doubleArrayOf(0.0, 0.0)
    var type: enIndicatorType

    private var sensorIndicatorButton: SensorIndicatorButton? = null

    constructor(_type: enIndicatorType) {
        type = _type
        when (type) {
            enIndicatorType.Brightness -> {
                alarmRange[0] = 1.0
                alarmRange[1] = 1.0
            }
            enIndicatorType.Temperature -> {
                indicatorValue = 19.0
                alarmRange[0] = 18.0
                alarmRange[1] = 28.0
            }
        }
    }


    fun setIndicatorVal(_value: Double){
        this.indicatorValue = _value
    }

    fun testGenerateData() {

    }

    fun nextTestData() {

    }

    fun setLinkToSensorIndicatorButton(_sensorIndicatorButton: SensorIndicatorButton) {
        if (this.sensorIndicatorButton !=_sensorIndicatorButton) {
            this.sensorIndicatorButton = _sensorIndicatorButton
            _sensorIndicatorButton.setSensorIndicator(this)
        }
    }

    fun visualGetMainImage():Int {
        when (this.type) {
            enIndicatorType.Humidity -> return R.drawable.ic_sensor_humidity
            enIndicatorType.Co2 -> return R.drawable.ic_sensor_co2
            enIndicatorType.Brightness -> return R.drawable.ic_sensor_sun
            enIndicatorType.Temperature -> return R.drawable.ic_sensor_temperature
            else -> return return R.drawable.ic_sensor_temperature
        }
    }

    fun getAlarmCode():Int {
        //0 - all is ok, 1 - upper when 1st border, 2 - upper when 2nd border ()
        //0 - all is ok, 1 - below when 1st border, 2 - upper when 2nd border (Temperature,Brightness indicator)
        when(this.type) {
            enIndicatorType.Temperature, enIndicatorType.Brightness -> {
                if (this.indicatorValue <= this.alarmRange[0]) {
                    return 1
                }else if (this.indicatorValue >= this.alarmRange[1]) {
                    return 2
                }else return 0
            }
            else -> {
                if (this.indicatorValue >= this.alarmRange[1]) {
                    return 2
                }else if (this.indicatorValue >= this.alarmRange[0]) {
                    return 1
                }else return 0
            }
        }
    }

    fun getValueText(): String {
        when (this.type) {
            enIndicatorType.Temperature -> return String.format("%.2f", this.indicatorValue)
            enIndicatorType.Co2 -> return String.format("%.2f", this.indicatorValue)
            enIndicatorType.Brightness -> return String.format("%.0f", this.indicatorValue)
            enIndicatorType.Humidity -> return String.format("%.2f", this.indicatorValue)
        }
    }

    fun getValueSign(): String {
        when (this.type) {
            enIndicatorType.Temperature -> return "℃"
            enIndicatorType.Co2 -> return "ppm"
            enIndicatorType.Brightness -> return "lx"
            enIndicatorType.Humidity -> return "%"
        }
    }

    fun getAlarmStay(alarmCode: Int = getAlarmCode() ):AlarmSensorIndicator  {
        var resultIconVisible:Boolean = (alarmCode != 0)
        var resultIconIdPic:Int = R.drawable.ic_sensor_pic_yellow
        var resultText:String = "Excellent"
        var resultTextColorId:Int = R.color.colorSOYellow



        when(this.type) {
            enIndicatorType.Temperature -> {
                when(alarmCode){
                    0 -> {
                        resultText = "Excellent"
                        resultTextColorId = R.color.colorSOGreen
                    }
                    1 -> {
                        resultText = "Too cold"
                        resultTextColorId = R.color.colorSOYellow
                        resultIconIdPic = R.drawable.ic_sensor_pic_yellow
                    }
                    2 -> {
                        resultText = "Too hot"
                        resultTextColorId = R.color.colorSORed
                        resultIconIdPic = R.drawable.ic_sensor_pic_red
                    }
                }
            }
            enIndicatorType.Co2 -> {
                when(alarmCode){
                    0 -> {
                        resultText = "Excellent"
                        resultTextColorId = R.color.colorSOGreen
                    }
                    1 -> {
                        resultText = "A bit dirty"
                        resultTextColorId = R.color.colorSOYellow
                        resultIconIdPic = R.drawable.ic_sensor_pic_yellow
                    }
                    2 -> {
                        resultText = "Very dirty"
                        resultTextColorId = R.color.colorSORed
                        resultIconIdPic = R.drawable.ic_sensor_pic_red
                    }
                }
            }
            enIndicatorType.Humidity -> {
                when(alarmCode){
                    0 -> {
                        resultText = "Excellent"
                        resultTextColorId = R.color.colorSOGreen
                    }
                    1 -> {
                        resultText = "Wet"
                        resultTextColorId = R.color.colorSOYellow
                        resultIconIdPic = R.drawable.ic_sensor_pic_yellow
                    }
                    2 -> {
                        resultText = "Water around"
                        resultTextColorId = R.color.colorSORed
                        resultIconIdPic = R.drawable.ic_sensor_pic_red
                    }
                }
            }
            enIndicatorType.Brightness -> {
                when(alarmCode){
                    0 -> {
                        resultText = "Excellent"
                        resultTextColorId = R.color.colorSOGreen
                    }
                    1 -> {
                        resultText = "Too dark"
                        resultTextColorId = R.color.colorSOYellow
                        resultIconIdPic = R.drawable.ic_sensor_pic_yellow
                    }
                    2 -> {
                        resultText = "Too shine"
                        resultTextColorId = R.color.colorSORed
                        resultIconIdPic = R.drawable.ic_sensor_pic_red
                    }
                }
            }
        }
        Log.i("SHOW",AlarmSensorIndicator(resultIconVisible, resultIconIdPic, resultText , resultTextColorId).toString())
        return AlarmSensorIndicator(resultIconVisible, resultIconIdPic, resultText , resultTextColorId)
    }




}