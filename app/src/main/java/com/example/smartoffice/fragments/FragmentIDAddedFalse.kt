package com.example.smartoffice.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.smartoffice.MainActivity

import com.example.smartoffice.R


class FragmentIDAddedFalse : FragmentParent() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_id_added_false, container, false)
    }

    override fun onShow() {
        super.onShow()
        val mView = view
        if (mView != null) {
            val textShow = mView.findViewById(R.id.textView0) as TextView
            textShow.text = Editable.Factory.getInstance().newEditable(this.strData)

            val onClickListenerBack = View.OnClickListener {
                (context as MainActivity).onBackPressed()
            }

            val buttonBack = mView.findViewById(R.id.buttonBack) as ImageView
            buttonBack.setOnClickListener(onClickListenerBack)

            val onClickListenerTry = View.OnClickListener {
                (context as MainActivity).fragmentsShow("FragmentScan")
            }

            val buttonTry = mView.findViewById(R.id.buttonTry) as Button
            buttonTry.setOnClickListener(onClickListenerTry)

        }
    }



    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentIDAddedFalse().apply {
            }
    }
}