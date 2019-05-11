package com.example.smartoffice.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.smartoffice.R
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.content.Context.INPUT_METHOD_SERVICE
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import android.widget.EditText
import kotlin.coroutines.coroutineContext


class FragmentEnterCode : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_enter_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Log.i("FRAGMENT_ENTER","RESUME")
//        val editText = view?.findViewById(R.id.textViewEdit) as EditText
//        editText?.requestFocus()
//
//        //Show keyboard
//        val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
//        imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }



    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentEnterCode().apply {
            }
    }
}