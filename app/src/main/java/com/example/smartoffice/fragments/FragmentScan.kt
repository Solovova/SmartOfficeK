package com.example.smartoffice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.ModalDialog
import com.afollestad.materialdialogs.input.input
import com.google.android.gms.vision.barcode.Barcode
import com.example.smartoffice.R
import com.notbytes.barcode_reader.BarcodeReaderFragment


class FragmentScan : Fragment(), BarcodeReaderFragment.BarcodeReaderListener {
    private var barcodeReader: BarcodeReaderFragment? = null
    private var useFlash: Boolean = false
    private var buttonInput: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.useFlash = false
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fragment_scan, container, false)
        barcodeReader = childFragmentManager.findFragmentById(R.id.barcode_fragment) as BarcodeReaderFragment
        barcodeReader?.setListener(this)

        val onClickListenerInput = View.OnClickListener { _ ->
            val mContext = context
            if (mContext != null){
                MaterialDialog(mContext).show {
                    input  ()

                        .autoDismissEnabled
                    ModalDialog
                    //positiveButton("Test")
                }
            }


            return@OnClickListener
        }
        this.buttonInput = view.findViewById(R.id.buttonInput)
        this.buttonInput?.setOnClickListener(onClickListenerInput)
        return view
    }

    override fun onScanned(barcode: Barcode) {
        Log.e(TAG, "onScanned: " + barcode.displayValue)
        barcodeReader?.playBeep()
        Toast.makeText(activity, "Barcode: " + barcode.displayValue, Toast.LENGTH_SHORT).show()
    }

    override fun onScannedMultiple(barcodes: List<Barcode>) {
        Log.e(TAG, "onScannedMultiple: " + barcodes.size)

        var codes = ""
        for (barcode in barcodes) {
            codes += barcode.displayValue + ", "
        }

        val finalCodes = codes
        Toast.makeText(getActivity(), "Barcodes: $finalCodes", Toast.LENGTH_SHORT).show()
    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>) {

    }

    override fun onScanError(errorMessage: String) {
        Log.e(TAG, "onScanError: $errorMessage")
    }

    override fun onCameraPermissionDenied() {
        Toast.makeText(activity, "Camera permission denied!", Toast.LENGTH_LONG).show()
    }

//    fun switchFlash() {
//        this.useFlash = !this.useFlash
//        this.barcodeReader!!.setUseFlash(this.useFlash)
//    }

    companion object {
        private val TAG = FragmentScan::class.java.simpleName

        fun newInstance(): FragmentScan {
            return FragmentScan()
        }
    }
}