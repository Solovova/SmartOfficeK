package com.example.smartoffice.service

import android.widget.LinearLayout
import com.example.smartoffice.dataclass.DataIndicatorTypeDef
import com.example.smartoffice.dataclass.EnumIndicatorsType
import com.example.smartoffice.soviews.SensorButton
import com.example.smartoffice.R
import android.os.SystemClock
import android.util.Log
import com.example.smartoffice.SOApplication
import com.example.smartoffice.test.TestDataFlow
import com.example.smartoffice.test.TestDataRecordIndicator
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import java.lang.Exception


class SensorContainer(_app: SOApplication) {
    private var myThread: Thread? = null
    var sensors = mutableMapOf<String,Sensor>()
    private var sensorIndicatorDef = mutableMapOf<EnumIndicatorsType,DataIndicatorTypeDef>()
    private var viewContainer : LinearLayout? = null
    private var testDataFlow: TestDataFlow = TestDataFlow()

    var app: SOApplication? = _app

    //hubConnection
    var hubConnection: HubConnection? = null

    init {
        //hub connection
        hubConnection = HubConnectionBuilder.create("http://10.0.2.2:5000/movehub").build()
        val mHubConnection = this.hubConnection
        if (mHubConnection != null) {
//            if (hubConnection.connectionState === HubConnectionState.DISCONNECTED) {
//                hubConnection.start()
//            }


        }

//        hubConnection?.on("ReceiveNewPositions", { mID, mIndicator, mValue ->
//            Log.i("RECEIVE",mID)
//            //val testDataRecordIndicator = TestDataRecordIndicator(mID,EnumIndicatorsType.values()[mIndicator],mValue.toDouble())
//            //app?.mainActivity?.runOnUiThread {this.eventDataIn(testDataRecordIndicator)}
//        }, String::class.java, Int::class.java, Float::class.java)

        hubConnection?.on("ReceiveNewPositions", { mID:String, mIndicator:String, mValue:String->
            try{
                Log.i("RECEIVE","$mID  $mIndicator $mValue")
                val mtIndicator = mIndicator.toInt()
                val mtValue = mValue.toFloat()
                val testDataRecordIndicator = TestDataRecordIndicator(mID,EnumIndicatorsType.values()[mtIndicator],mtValue.toDouble())
                app?.mainActivity?.runOnUiThread {this.eventDataIn(testDataRecordIndicator)}
            }catch (e: Exception){}

        }, String::class.java, String::class.java , String::class.java)


        //end hub connection
        var tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 20.0
            defAlarmBorder          = arrayOf(19.0, 26.0)
            defTypeOfAlarm          = 0
            defTextAlarm            = arrayOf("Excellent", "Too cold", "Too hot")
            defTextAlarmIdColor     = arrayOf(R.color.colorSOGreen, R.color.colorSOBlue, R.color.colorSORed)
            defTextAlarmIdImage     = arrayOf(0, R.drawable.ic_sensor_pic_blue, R.drawable.ic_sensor_pic_red)
            defOnButtonAlarmIdImage = arrayOf(0, R.drawable.ic_sensor_pic_blue, R.drawable.ic_sensor_pic_red)
            idBigPicture            = R.drawable.ic_sensor_temperature
            idBigPictureGrey        = R.drawable.ic_sensor_temperature_grey
            defFormatString         = "%.2f"
            defDescribeValue        = "℃"
            defDescribe             = "Temperature"
            defTextDescribe         = "\tDescribe for Temperature"
        }
        sensorIndicatorDef[EnumIndicatorsType.Temperature] = tmpDataIndicatorTypeDef
        tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 400.0
            defAlarmBorder          = arrayOf(200.0, 600.0)
            defTypeOfAlarm          = 0
            defTextAlarm            = arrayOf("Excellent", "Too dark", "Too shine")
            defTextAlarmIdColor     = arrayOf(R.color.colorSOGreen, R.color.colorSOBlue, R.color.colorSORed)
            defTextAlarmIdImage     = arrayOf(0, R.drawable.ic_sensor_pic_blue, R.drawable.ic_sensor_pic_red)
            defOnButtonAlarmIdImage = arrayOf(0, R.drawable.ic_sensor_pic_blue, R.drawable.ic_sensor_pic_red)
            idBigPicture            = R.drawable.ic_sensor_brightness
            idBigPictureGrey        = R.drawable.ic_sensor_brightness_grey
            defFormatString         = "%.0f"
            defDescribeValue        = "lx"
            defDescribe             = "Brightness"
            defTextDescribe         = "\tDescribe for Brightness"
        }
        sensorIndicatorDef[EnumIndicatorsType.Brightness] = tmpDataIndicatorTypeDef
        tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 800.0
            defAlarmBorder          = arrayOf(1000.0, 1400.0)
            defTypeOfAlarm          = 1
            defTextAlarm            = arrayOf("Excellent", "A bit dirty", "Very dirty")
            defTextAlarmIdColor     = arrayOf(R.color.colorSOGreen, R.color.colorSOYellow, R.color.colorSORed)
            defTextAlarmIdImage     = arrayOf(0, R.drawable.ic_sensor_pic_yellow, R.drawable.ic_sensor_pic_red)
            defOnButtonAlarmIdImage = arrayOf(0, R.drawable.ic_sensor_pic_yellow, R.drawable.ic_sensor_pic_red)
            idBigPicture            = R.drawable.ic_sensor_co2
            idBigPictureGrey        = R.drawable.ic_sensor_co2_grey
            defFormatString         = "%.0f"
            defDescribeValue        = "ppm"
            defDescribe             = "Co2"
            defTextDescribe         = "\tCarbon dioxide at levels that are unusually high indoors may cause occupants to grow drowsy, to get headaches, or to function at lower activity levels. \n \tOutdoor CO2 levels are usually 350-450 ppm whereas the maximum indoor CO2 level considered acceptable is 1000 ppm. Keep this value lowe as possible."
        }
        sensorIndicatorDef[EnumIndicatorsType.Co2] = tmpDataIndicatorTypeDef
        tmpDataIndicatorTypeDef = DataIndicatorTypeDef()
        with(tmpDataIndicatorTypeDef) {
            defValue = 50.0
            defAlarmBorder          = arrayOf(70.0, 90.0)
            defTypeOfAlarm          = 1
            defTextAlarm            = arrayOf("Excellent", "Too wet", "Very wet")
            defTextAlarmIdColor     = arrayOf(R.color.colorSOGreen, R.color.colorSOYellow, R.color.colorSORed)
            defTextAlarmIdImage     = arrayOf(0, R.drawable.ic_sensor_pic_yellow, R.drawable.ic_sensor_pic_red)
            defOnButtonAlarmIdImage = arrayOf(0, R.drawable.ic_sensor_pic_yellow, R.drawable.ic_sensor_pic_red)
            idBigPicture            = R.drawable.ic_sensor_humidity
            idBigPictureGrey        = R.drawable.ic_sensor_humidity_grey
            defFormatString         = "%.0f"
            defDescribeValue        = "%"
            defDescribe             = "Humidity"
            defTextDescribe         = "\tDescribe for Humidity"
        }
        sensorIndicatorDef[EnumIndicatorsType.Humidity] = tmpDataIndicatorTypeDef
        this.myThread = Thread(
            Runnable {
                while (true) {
                    val mHubConnection = this.hubConnection
                    if (mHubConnection != null) {
                        if (mHubConnection.connectionState === HubConnectionState.DISCONNECTED) {
                            Log.i("RECEIVE", "DISCONNECTED")
                        }
                        if (mHubConnection.connectionState === HubConnectionState.CONNECTED) {
                            Log.i("RECEIVE", "CONNECTED")
                        }
                    }


//                    val mHubConnection = this.hubConnection
//                    if (mHubConnection != null) {
//                        if (mHubConnection.connectionState === HubConnectionState.DISCONNECTED)
//                            app?.mainActivity?.runOnUiThread {this.eventDataIn(this.testDataFlow.getNextTestRecord())}
//                    }else{
//                        app?.mainActivity?.runOnUiThread {this.eventDataIn(this.testDataFlow.getNextTestRecord())}
//                    }

                    SystemClock.sleep(1000)
                }
            }
        )
        this.myThread?.start()
    }

    fun setViewContainer (viewContainer: LinearLayout) {
        this.viewContainer = viewContainer
        this.createSensorButtons()
    }

    private fun createSensorButtons(){
        val viewContainer = this.viewContainer
        if (viewContainer != null) {
            if (viewContainer.childCount > 0) viewContainer.removeAllViews()
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            for (id in  sensors.keys) {
                val sensor: Sensor? = sensors[id]
                if (sensor != null){
                    val newButton = SensorButton(viewContainer.context)
                    params.setMargins(0, 0, 0, 0)
                    newButton.layoutParams = params
                    viewContainer.addView(newButton)
                    sensor.setLinkToSensorButton(newButton)
                }
            }
            viewContainer.invalidate()
        }
    }

    fun getDataIndicatorTypeDef(_type: EnumIndicatorsType):DataIndicatorTypeDef {
        val dataIndicatorTypeDef = sensorIndicatorDef[_type]
        dataIndicatorTypeDef ?: return DataIndicatorTypeDef()
        return dataIndicatorTypeDef
    }

    fun testGenerateData() {
        val testSensorID: Array<String> = arrayOf("id123432", "id999797", "id999997")
        val testSensorName: Array<String> = arrayOf("Room 8", "Room 2", "Room 7")

        val testSensorIndicator = mutableMapOf<String,Array<EnumIndicatorsType>>()
        testSensorIndicator[testSensorID[0]] = arrayOf(
            EnumIndicatorsType.Temperature,
            EnumIndicatorsType.Brightness,
            EnumIndicatorsType.Co2,
            EnumIndicatorsType.Humidity
            )

        testSensorIndicator[testSensorID[1]] = arrayOf(
            EnumIndicatorsType.Temperature,
            EnumIndicatorsType.Humidity
        )

        testSensorIndicator[testSensorID[2]] = arrayOf(
            EnumIndicatorsType.Temperature,
            EnumIndicatorsType.Humidity,
            EnumIndicatorsType.Brightness
        )


        var sensor: Sensor?
        for (ind in 0 until testSensorID.size) {
            sensor = Sensor(testSensorID[ind], this)
            sensor.setName(testSensorName[ind])
            sensor.testGenerateData(testSensorIndicator[testSensorID[ind]])
            sensors[testSensorID[ind]] = sensor
        }
    }

    private fun eventDataIn(testDataRecordIndicator: TestDataRecordIndicator) {
        val sensor = this.sensors[testDataRecordIndicator.sensorId]
        sensor?.eventDataIn(testDataRecordIndicator)
    }

    fun onChangeSensor(){

    }

    fun deleteSensor(sensor: Sensor) {
        sensors.remove(sensor.sensorID)
        this.createSensorButtons()
    }

    fun addSensor(_id:String) : String {
        if (sensors[_id] != null) return "Already exists"
        val sensor = Sensor(_id, this)
        sensor.setName(_id)

        val testSensorIndicator = arrayOf(
            EnumIndicatorsType.Temperature,
            EnumIndicatorsType.Humidity,
            EnumIndicatorsType.Brightness
        )

        sensor.testGenerateData(testSensorIndicator)
        sensors[_id] = sensor
        this.createSensorButtons()
        return "OK"
    }

    fun setLinkToViewNull(){
        for (sensor in sensors.values) {
            sensor.setLinkToViewNull()
        }
    }
}