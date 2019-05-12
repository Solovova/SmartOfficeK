package com.example.smartoffice.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.smartoffice.MainActivity

import com.example.smartoffice.R
import android.os.Handler


class FragmentIDAddedOk : FragmentParent() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_id_added_ok, container, false)
    }

    override fun onShow() {
        super.onShow()
        val mView = view
        if (mView != null) {
            val textShow = mView.findViewById(R.id.textView1) as TextView
            textShow.text = Editable.Factory.getInstance().newEditable(this.strData)

            val onClickListenerBack = View.OnClickListener {
                (context as MainActivity).onBackPressed()
            }

            val buttonBack = mView.findViewById(R.id.buttonBack) as ImageView
            buttonBack.setOnClickListener(onClickListenerBack)

            val mRunnable = Runnable {
                if ((context as MainActivity).getActiveFragments().compareTo("FragmentIDAddedOk") == 0)
                    (context as MainActivity).onBackPressed()
            }
            val handler = Handler()
            handler.postDelayed( mRunnable, 2000)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentIDAddedOk().apply {
            }
    }
}