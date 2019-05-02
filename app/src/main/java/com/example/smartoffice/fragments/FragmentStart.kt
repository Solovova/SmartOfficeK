package com.example.smartoffice.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.example.smartoffice.R
import com.example.smartoffice.soviews.SensorButton

class FragmentStart : Fragment() {
    private var layoutScrollContainer : LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutScrollContainer = view.findViewById(R.id.LayoutScrollContainer)
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentStart().apply {

            }
    }

    fun redrawSensorButtons(){
        Log.i("Added","Start" )

        if (layoutScrollContainer?.childCount != 0 ) layoutScrollContainer?.removeAllViews()

        var params: LinearLayout.LayoutParams  = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        val myContext = layoutScrollContainer?.context
        Log.i("Added","Startpp" )
        if (myContext != null) {
            Log.i("Added","Starttt" )
            for (i in 1..3) {
                val newButton = SensorButton(myContext)
                newButton.setText("Name $i")

                params.setMargins(10, 10, 10, 10)
                newButton.layoutParams = params

//                newButton.setOnClickListener(View.OnClickListener {
//                    // your handler code here
//                    (activity as MainActivity).fragmentsShow("FragmentRoom")
//                })
                Log.i("Added","Name $i" )
                layoutScrollContainer?.addView(newButton)
            }
        }
    }
}
