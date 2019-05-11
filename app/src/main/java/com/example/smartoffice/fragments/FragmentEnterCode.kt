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
import android.content.Context.INPUT_METHOD_SERVICE
import androidx.core.content.ContextCompat.getSystemService




class FragmentEnterCode : FragmentParent() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_enter_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onShow() {
        super.onShow()
        val mView = view
        if (mView == null) {
            Log.i("FRAGMENT_ENTER","ON SHOW VIEW = NULL")
        }else{
            Log.i("FRAGMENT_ENTER","ON SHOW VIEW != NULL")
            val editText: EditText = mView.findViewById(R.id.textViewEdit) as EditText
            editText.requestFocus()
            val strDefaultText = "id"
            editText.text = Editable.Factory.getInstance().newEditable(strDefaultText)
            editText.setSelection(strDefaultText.length)
            //Show keyboard
            val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
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