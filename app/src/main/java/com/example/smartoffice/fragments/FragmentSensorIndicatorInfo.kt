package com.example.smartoffice.fragments

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.smartoffice.MainActivity
import com.example.smartoffice.R

class FragmentSensorIndicatorInfo : FragmentParent() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sensor_indicator_info, container, false)
    }

    override fun onShow() {
        super.onShow()
        val mView = view
        if (mView != null) {
            val sensorIndicator = this.sensorIndicator
            if (sensorIndicator != null) {
                val topTxName = mView.findViewById(R.id.topTxName) as TextView
                topTxName.text = Editable.Factory.getInstance().newEditable(sensorIndicator.type.toString())

                val topImMain = mView.findViewById(R.id.topImMain) as ImageView
                topImMain.setImageResource(sensorIndicator.dataIndicatorTypeDef.idBigPicture)

                val topTxText = mView.findViewById(R.id.topTxText) as TextView
                topTxText.text = Editable.Factory.getInstance().newEditable(sensorIndicator.dataIndicatorTypeDef.defTextDescribe)


                val onClickListenerBack = View.OnClickListener {
                    (context as MainActivity).onBackPressed()
                }

                val buttonBack = mView.findViewById(R.id.buttonBack) as ImageView
                buttonBack.setOnClickListener(onClickListenerBack)

                val buttonOk = mView.findViewById(R.id.buttonOk) as Button
                buttonOk.setOnClickListener(onClickListenerBack)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentSensorIndicatorInfo()
    }
}
