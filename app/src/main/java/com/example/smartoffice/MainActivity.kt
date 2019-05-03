package com.example.smartoffice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import com.example.smartoffice.fragments.FragmentStart
import android.util.Log
import android.view.View
import com.example.smartoffice.fragments.FragmentBlank
import com.example.smartoffice.fragments.FragmentSensor
import com.example.smartoffice.service.Sensor
import com.example.smartoffice.soviews.SensorButton

class MainActivity : AppCompatActivity() {
    var fragments = mutableMapOf<String, Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            //(application as SOApplication).dataContainer!!.load()
            this.showStartScreen(false)
        }
    }

    //a?.equals(b) ?: (b === null)
    fun fragmentsShow(fragmentName: String, sensor: Sensor? = null) {
        Log.d("SHOW",fragmentName)
        val ft = supportFragmentManager.beginTransaction()

        //hide all fragments
        for ((key, value)  in fragments) {
            if (key.compareTo(fragmentName)  != 0) ft.hide(value)
        }

        var fragment:Fragment? =  fragments[fragmentName]

        if (fragment == null ) {
            Log.i("SHOW CREATE",fragmentName)
            if (fragmentName.startsWith("FragmentSensor")) {
                fragment = FragmentSensor.newInstance()
                if (sensor!=null)  (fragment as FragmentSensor).setSensor(sensor)
            }else{
                when (fragmentName) {
                    "FragmentStart" -> fragment = FragmentStart.newInstance()
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
        supportFragmentManager.executePendingTransactions()
    }

    private fun getActiveFragments(): String {
        var result = ""
        for ((_key, _fragment) in fragments) {
            if (_fragment != null && _fragment!!.isAdded && _fragment!!.isVisible) {
                result = _key
                break
            }
        }
        return result
    }

    private fun showStartScreen(showButtons: Boolean) {
        this.fragmentsShow("FragmentStart")
    }

    fun testClickAdd(v: View) {
        //(application as SOApplication).sensorContainer.testAdd()


    }

    fun testClickChange(v: View) {
        //(application as SOApplication).sensorContainer.testAdd()
    }

    override fun onBackPressed() {
        val fragmentName:String = this.getActiveFragments()
        if (fragmentName.startsWith("FragmentSensor")) {
            this.showStartScreen(true)
        }else{
            when (this.getActiveFragments()) {
                "FragmentStart" -> super.onBackPressed()
                else -> super.onBackPressed()
            }
        }
    }
}
