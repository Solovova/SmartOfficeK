package com.example.smartoffice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.smartoffice.MainActivity
import com.example.smartoffice.R
import com.example.smartoffice.SOApplication
import com.microsoft.signalr.HubConnectionState

class FragmentStart : FragmentParent() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onShow() {
        super.onShow()
        val view = this.view
        if (view != null) {
            (activity?.application as SOApplication).sensorContainer.setViewContainer(view.findViewById(R.id.container))

            val btnStart = view.findViewById<Button>(R.id.buttonSignalR)

            val onClickListenerStart = View.OnClickListener {

                val hubConnection =
                    ((context as MainActivity).application as SOApplication).sensorContainer.hubConnection

                if (hubConnection != null && btnStart != null) {
                    if (btnStart.text.toString().toLowerCase().compareTo("start") == 0) {
                        if (hubConnection.connectionState === HubConnectionState.DISCONNECTED) {
                            hubConnection.start()
                        }
                        btnStart.text = "stop"
                    } else if (btnStart.text.toString().toLowerCase().compareTo("stop") == 0) {
                        if (hubConnection.connectionState === HubConnectionState.CONNECTED) {
                            hubConnection.stop()
                        }
                        btnStart.text = "start"
                    }
                }
            }
            btnStart.setOnClickListener(onClickListenerStart)

        }


    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentStart()
    }
}
