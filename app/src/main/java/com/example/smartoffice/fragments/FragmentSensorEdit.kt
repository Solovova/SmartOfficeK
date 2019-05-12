package com.example.smartoffice.fragments

import android.app.Activity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.smartoffice.R
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.smartoffice.MainActivity
import com.example.smartoffice.service.Sensor

class FragmentSensorEdit : FragmentParent() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sensor_edit, container, false)
    }

    override fun onShow() {
        super.onShow()
        val mView = view
        if (mView != null) {
            val sensor = this.sensor
            if (sensor != null) {
                val textEdit = mView.findViewById(R.id.textViewEdit) as EditText
                textEdit.requestFocus()
                val strDefaultText = sensor.sensorName
                textEdit.text = Editable.Factory.getInstance().newEditable(strDefaultText)
                textEdit.setSelection(strDefaultText.length)

                val textID = mView.findViewById(R.id.textID) as TextView
                textID.text = Editable.Factory.getInstance().newEditable("ID: ${sensor.sensorID}")


                //Show keyboard
                val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.showSoftInput(textEdit, InputMethodManager.SHOW_IMPLICIT)

                val onClickListenerOk = View.OnClickListener {
                    sensor.setName(textEdit.text.toString())
                    (context as MainActivity).fragmentsShow("FragmentSensor", sensor = sensor)
                }

                val buttonOk = mView.findViewById(R.id.buttonOk) as Button
                buttonOk.setOnClickListener(onClickListenerOk)

                val onClickListenerBack = View.OnClickListener {
                    (context as MainActivity).onBackPressed()
                }

                val buttonBack = mView.findViewById(R.id.buttonBack) as ImageView
                buttonBack.setOnClickListener(onClickListenerBack)
            }
        }
    }

    override fun onHide() {
        super.onHide()
        val mView = view
        if (mView != null) {
            val fView = mView.findFocus()
            val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(fView.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentSensorEdit()
    }
}