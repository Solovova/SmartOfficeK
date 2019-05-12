package com.example.smartoffice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.smartoffice.fragments.*
import com.example.smartoffice.service.Sensor
import com.example.smartoffice.service.SensorIndicator


class MainActivity : AppCompatActivity() {

    //SwipeLayout
    private var fragments = mutableMapOf<String, FragmentParent?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as SOApplication).mainActivity = this

        if (savedInstanceState == null) {
            //(application as SOApplication).dataContainer!!.load()
            this.showStartScreen()
        }
    }

    //a?.equals(b) ?: (b === null)
    fun fragmentsShow(fragmentName: String,
                      sensor: Sensor? = null,
                      sensorIndicator: SensorIndicator? = null,
                      strData: String? = null) {
        Log.d("SHOW",fragmentName)
        val ft = supportFragmentManager.beginTransaction()

        //hide all fragments
        for ((key, value)  in fragments) {
            if (value != null)
                if (key.compareTo(fragmentName)  != 0 && value.isVisible) {
                    ft.hide(value)
                    value.onHide()
                }
        }

        var fragment: FragmentParent? =  fragments[fragmentName]

        if (fragment == null) {
            Log.i("SHOW CREATE", fragmentName)
            when (fragmentName) {
                "FragmentSensor" -> fragment = FragmentSensor.newInstance()
                "FragmentStart" -> fragment = FragmentStart.newInstance()
                "FragmentStartBlank" -> fragment = FragmentStartBlank.newInstance()
                "FragmentScan" -> fragment = FragmentScan.newInstance()
                "FragmentEnterCode" -> fragment = FragmentEnterCode.newInstance()
                "FragmentIDAddedFalse" -> fragment = FragmentIDAddedFalse.newInstance()
                "FragmentIDAddedOk" -> fragment = FragmentIDAddedOk.newInstance()
                "FragmentSensorEdit" -> fragment = FragmentSensorEdit.newInstance()
                "FragmentSensorIndicatorInfo" -> fragment = FragmentSensorIndicatorInfo.newInstance()
                else -> fragment = FragmentBlank.newInstance()
            }
        }


        fragment.sensor = sensor
        fragment.sensorIndicator = sensorIndicator
        fragment.strData = strData



        fragments[fragmentName]=fragment
        if (!fragment.isAdded) ft.add(R.id.container, fragment, fragmentName)

        ft.show(fragment)
        fragment.onShow()

        ft.commit()
    }

    fun getActiveFragments(): String {
        var result = ""
        for ((_key, _fragment) in fragments) {
            if (_fragment != null && _fragment.isAdded && _fragment.isVisible) {
                result = _key
                break
            }
        }
        return result
    }

    fun showStartScreen() {
        when ((application as SOApplication).sensorContainer.sensors.isEmpty()) {
            true -> this.fragmentsShow("FragmentStartBlank")
            false -> this.fragmentsShow("FragmentStart")
        }
    }

    override fun onBackPressed() {
        when (this.getActiveFragments()) {
            "FragmentSensor"        -> this.showStartScreen()
            "FragmentStart"         -> super.onBackPressed()
            "FragmentScan"          -> this.showStartScreen()
            "FragmentIDAddedOk"     -> this.showStartScreen()
            "FragmentIDAddedFalse"  -> this.showStartScreen()
            "FragmentEnterCode"     -> this.fragmentsShow("FragmentScan")
            "FragmentSensorEdit"    -> {
                val fragmentEditSensor = fragments["FragmentSensorEdit"]
                this.fragmentsShow("FragmentSensor", sensor = fragmentEditSensor?.sensor)
            }
            "FragmentSensorIndicatorInfo"    -> {
                val fragmentSensorInfo = fragments["FragmentSensorIndicatorInfo"]
                this.fragmentsShow("FragmentSensor", sensor = fragmentSensorInfo?.sensorIndicator?.sensor)
            }
            else -> super.onBackPressed()
        }
    }

    //StartFragment
    fun startFragmentScan(v: View) {
        Log.i("Button click", v.id.toString())
        this.fragmentsShow("FragmentScan")
    }

    //SensorFragment
    fun sensorFragmentBack(v: View) {
        Log.i("Button click", v.id.toString())
        this.onBackPressed()
    }

    //ScanFragment
    fun scanFragmentEnterCode(v: View) {
        Log.i("Button click", v.id.toString())
        this.fragmentsShow("FragmentEnterCode")
    }

    fun scanFragmentBack(v: View) {
        Log.i("Button click", v.id.toString())
        this.onBackPressed()
    }

    //EnterCodeFragment
    fun sensorEnterCodeBack(v: View) {
        Log.i("Button click", v.id.toString())
        this.onBackPressed()
    }

    fun sensorEnterCodeOk(v: View) {
        Log.i("Button click", v.id.toString())
        val fragmentEnterCode = fragments["FragmentEnterCode"] as FragmentEnterCode
        val textEdit = fragmentEnterCode.textEdit
        if (textEdit != null) {
            val strNewID =  textEdit.text.toString()
            val strResult = (application as SOApplication).sensorContainer.addSensor(strNewID)
            if (strResult.contentEquals("OK")) {
                this.fragmentsShow("FragmentIDAddedOk", strData = strNewID)
            }else{
                this.fragmentsShow("FragmentIDAddedFalse", strData = strResult)
            }
        }
    }
}
