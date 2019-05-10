package com.example.smartoffice.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.vision.barcode.Barcode
import info.androidhive.barcode.BarcodeReader
import com.example.smartoffice.R


class FragmentScan : Fragment(), BarcodeReader.BarcodeReaderListener {
    private var barcodeReader: BarcodeReader? = null
    private var useFlash: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.useFlash = false
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fragment_scan, container, false)
        barcodeReader = childFragmentManager.findFragmentById(R.id.barcode_fragment) as BarcodeReader
        barcodeReader?.setListener(this)
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