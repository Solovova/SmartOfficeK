package com.example.smartoffice.fragments

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.smartoffice.R
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText




class FragmentEnterCode : FragmentParent() {
    var textEdit: EditText? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_enter_code, container, false)
    }

    override fun onShow() {
        super.onShow()
        val mView = view
        if (mView == null) {
            Log.i("FRAGMENT_ENTER","ON SHOW VIEW = NULL")
        }else{
            Log.i("FRAGMENT_ENTER","ON SHOW VIEW != NULL")
            textEdit?.requestFocus()
            val strDefaultText = "id"
            textEdit?.text = Editable.Factory.getInstance().newEditable(strDefaultText)
            textEdit?.setSelection(strDefaultText.length)
            //Show keyboard
            val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(textEdit, InputMethodManager.SHOW_IMPLICIT)
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
        fun newInstance() =
            FragmentEnterCode().apply {
            }
    }
}