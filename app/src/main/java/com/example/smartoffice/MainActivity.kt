package com.example.smartoffice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.smartoffice.fragments.*
import com.example.smartoffice.service.Sensor


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
    fun fragmentsShow(fragmentName: String, sensor: Sensor? = null) {
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

        if (fragment == null ) {
            Log.i("SHOW CREATE",fragmentName)
            if (fragmentName.startsWith("FragmentSensor")) {
                fragment = FragmentSensor.newInstance()
                if (sensor != null) fragment.setSensor(sensor)
            }else{
                when (fragmentName) {
                    "FragmentStart"         -> fragment = FragmentStart.newInstance()
                    "FragmentStartBlank"    -> fragment = FragmentStartBlank.newInstance()
                    "FragmentScan"          -> fragment = FragmentScan.newInstance()
                    "FragmentEnterCode"     -> fragment = FragmentEnterCode.newInstance()
                    "FragmentIDAddedFalse"  -> fragment = FragmentIDAddedFalse.newInstance()
                    "FragmentIDAddedOk"     -> fragment = FragmentIDAddedOk.newInstance()
                    "FragmentEditSensor"    -> fragment = FragmentEditSensor.newInstance()

                    else -> fragment = FragmentBlank.newInstance()
                }
            }
        }

        if (fragmentName.startsWith("FragmentEditSensor")) {
            (fragment as FragmentEditSensor).sensor = sensor
        }

        fragments[fragmentName]=fragment
        Log.i("show", fragmentName)
        if (!fragment.isAdded) {
            Log.i("add", fragmentName)
            ft.add(R.id.container, fragment, fragmentName)
        }
        ft.show(fragment)
        fragment.onShow()


        ft.commit()
        //supportFragmentManager.executePendingTransactions()
    }

    private fun getActiveFragments(): String {
        var result = ""
        for ((_key, _fragment) in fragments) {
            if (_fragment != null && _fragment.isAdded && _fragment.isVisible) {
                result = _key
                break
            }
        }
        return result
    }

    private fun showStartScreen() {
        this.fragmentsShow("FragmentStart")
    }

    override fun onBackPressed() {
        val fragmentName:String = this.getActiveFragments()
        Log.i("SHOW Back press", fragmentName)
        if (fragmentName.startsWith("FragmentSensor")) {
            this.showStartScreen()
        }else{
            when (this.getActiveFragments()) {
                "FragmentStart"         -> super.onBackPressed()
                "FragmentScan"          -> this.fragmentsShow("FragmentStart")
                "FragmentEnterCode"     -> this.fragmentsShow("FragmentScan")
                "FragmentEditSensor"     -> {
                    val fragmentEditSensor = fragments["FragmentEditSensor"] as FragmentEditSensor
                    if (fragmentEditSensor != null) {
                        val fragmentName = "FragmentSensor_${fragmentEditSensor.sensor?.sensorID}"
                        this.fragmentsShow(fragmentName, fragmentEditSensor.sensor)
                    }
                }
                else -> super.onBackPressed()
            }
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
        val textEdit = fragmentEnterCode?.textEdit
        if (textEdit != null) {
            val strNewID =  textEdit.text.toString()
            (application as SOApplication).sensorContainer.addSensor(strNewID)
            this.showStartScreen()
        }
    }

    //EditSensorFragment
    fun sensorEditBack(v: View) {
        Log.i("Button click", v.id.toString())
        this.onBackPressed()
    }
}
