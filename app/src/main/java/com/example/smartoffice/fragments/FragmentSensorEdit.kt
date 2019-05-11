package com.example.smartoffice.fragments

import android.app.Activity
import android.os.Bundle
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
    private var textEdit: EditText? = null
    private var textID: TextView? = null
    var sensor: Sensor? = null
    private var buttonOk: Button? = null
    private var buttonBack: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sensor_edit, container, false)
    }

    override fun onShow() {
        super.onShow()
        val mView = view
        if (mView == null) {
        }else{
            val sensor = this.sensor
            if (sensor != null) {
                this.textEdit = mView.findViewById(R.id.textViewEdit)
                textEdit?.requestFocus()
                val strDefaultText = sensor.sensorName
                textEdit?.text = Editable.Factory.getInstance().newEditable(strDefaultText)
                textEdit?.setSelection(strDefaultText.length)

                this.textID = mView.findViewById(R.id.textID)
                textID?.text = Editable.Factory.getInstance().newEditable("ID: ${sensor.sensorID}")


                //Show keyboard
                val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.showSoftInput(textEdit, InputMethodManager.SHOW_IMPLICIT)

                val onClickListenerOk = View.OnClickListener {
                    sensor.setName(textEdit?.text.toString())
                    (context as MainActivity).fragmentsShow("FragmentSensor", sensor)
                }

                this.buttonOk = view?.findViewById(R.id.buttonOk)
                this.buttonOk?.setOnClickListener(onClickListenerOk)

                val onClickListenerBack = View.OnClickListener {
                    (context as MainActivity).onBackPressed()
                }

                this.buttonBack = view?.findViewById(R.id.buttonBack)
                this.buttonBack?.setOnClickListener(onClickListenerBack)
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