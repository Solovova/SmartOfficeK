package com.example.smartoffice.service

import android.util.Log
import com.example.smartoffice.soviews.SensorIndicatorButton
import com.example.smartoffice.R
import com.example.smartoffice.dataclass.DataAlarmSensorIndicator
import com.example.smartoffice.dataclass.EnumIndicatorsType



class SensorIndicator  {
    private var indicatorValue: Double = 0.0
    private var alarmRange: DoubleArray = doubleArrayOf(0.0, 0.0)
    var type: EnumIndicatorsType

    private var sensorIndicatorButton: SensorIndicatorButton? = null

    constructor(_type: EnumIndicatorsType) {
        type = _type
        when (type) {
            EnumIndicatorsType.Brightness -> {
                alarmRange[0] = 1.0
                alarmRange[1] = 1.0
            }
            EnumIndicatorsType.Temperature -> {
                indicatorValue = 19.0
                alarmRange[0] = 18.0
                alarmRange[1] = 28.0
            }
            EnumIndicatorsType.Co2 -> {
                indicatorValue = 19.0
                alarmRange[0] = 18.0
                alarmRange[1] = 28.0
            }
            EnumIndicatorsType.Humidity -> {
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
            EnumIndicatorsType.Humidity -> return R.drawable.ic_sensor_humidity
            EnumIndicatorsType.Co2 -> return R.drawable.ic_sensor_co2
            EnumIndicatorsType.Brightness -> return R.drawable.ic_sensor_brightness
            EnumIndicatorsType.Temperature -> return R.drawable.ic_sensor_temperature
            else -> return return R.drawable.ic_sensor_temperature
        }
    }

    fun getAlarmCode():Int {
        //0 - all is ok, 1 - upper when 1st border, 2 - upper when 2nd border ()
        //0 - all is ok, 1 - below when 1st border, 2 - upper when 2nd border (Temperature,Brightness indicator)
        when(this.type) {
            EnumIndicatorsType.Temperature, EnumIndicatorsType.Brightness -> {
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
            EnumIndicatorsType.Temperature -> return String.format("%.2f", this.indicatorValue)
            EnumIndicatorsType.Co2 -> return String.format("%.2f", this.indicatorValue)
            EnumIndicatorsType.Brightness -> return String.format("%.0f", this.indicatorValue)
            EnumIndicatorsType.Humidity -> return String.format("%.2f", this.indicatorValue)
        }
    }

    fun getValueSign(): String {
        var result = ""
        when {
            this.type == EnumIndicatorsType.Temperature -> result = "℃"
            this.type == EnumIndicatorsType.Co2 -> result = "ppm"
            this.type == EnumIndicatorsType.Brightness -> result = "lx"
            this.type == EnumIndicatorsType.Humidity -> result = "%"
        }
        return result
    }

    fun getAlarmStay(alarmCode: Int = getAlarmCode() ):DataAlarmSensorIndicator  {
        var resultIconVisible:Boolean = (alarmCode != 0)
        var resultIconIdPic:Int = R.drawable.ic_sensor_pic_yellow
        var resultText:String = "Excellent"
        var resultTextColorId:Int = R.color.colorSOYellow



        when(this.type) {
            EnumIndicatorsType.Temperature -> {
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
            EnumIndicatorsType.Co2 -> {
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
            EnumIndicatorsType.Humidity -> {
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
            EnumIndicatorsType.Brightness -> {
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
        Log.i("SHOW",DataAlarmSensorIndicator(resultIconVisible, resultIconIdPic, resultText , resultTextColorId).toString())
        return DataAlarmSensorIndicator(resultIconVisible, resultIconIdPic, resultText , resultTextColorId)
    }




}