package com.example.smartoffice

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.View
import com.example.smartoffice.fragments.*
import com.example.smartoffice.service.Sensor
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.content.Context.INPUT_METHOD_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager
import android.widget.EditText



class MainActivity : AppCompatActivity() {

    //SwipeLayout
    private var fragments = mutableMapOf<String, Fragment?>()

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
                if (key.compareTo(fragmentName)  != 0) ft.hide(value)
        }

        var fragment: Fragment? =  fragments[fragmentName]

        if (fragment == null ) {
            Log.i("SHOW CREATE",fragmentName)
            if (fragmentName.startsWith("FragmentSensor")) {
                fragment = FragmentSensor.newInstance()
                if (sensor!=null)  fragment.setSensor(sensor)
            }else{
                when (fragmentName) {
                    "FragmentStart"         -> fragment = FragmentStart.newInstance()
                    "FragmentStartBlank"    -> fragment = FragmentStartBlank.newInstance()
                    "FragmentScan"          -> fragment = FragmentScan.newInstance()
                    "FragmentEnterCode"     -> fragment = FragmentEnterCode.newInstance()
                    "FragmentIDAddedFalse"  -> fragment = FragmentIDAddedFalse.newInstance()
                    "FragmentIDAddedOk"     -> fragment = FragmentIDAddedOk.newInstance()
                    "FragmentSensorEdit"    -> fragment = FragmentSensorEdit.newInstance()

                    else -> fragment = FragmentBlank.newInstance()
                }
            }
        }

        fragments[fragmentName]=fragment
        Log.i("show", fragmentName)
        if (!fragment.isAdded) {
            Log.i("add", fragmentName)
            ft.add(R.id.container, fragment, fragmentName)
        }
        ft.show(fragment)


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
}
