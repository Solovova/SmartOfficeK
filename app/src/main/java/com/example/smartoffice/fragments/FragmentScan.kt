package com.example.smartoffice.fragments

import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.vision.barcode.Barcode
import com.example.smartoffice.R
import com.notbytes.barcode_reader.BarcodeReaderFragment


class FragmentScan : FragmentParent(), BarcodeReaderFragment.BarcodeReaderListener {
    private var barcodeReader: BarcodeReaderFragment? = null
    private var useFlash: Boolean = false
    private var buttonFlash: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scan, container, false)
    }

    override fun onShow() {
        super.onShow()
        val view = this.view
        if (view != null) {
            barcodeReader = childFragmentManager.findFragmentById(R.id.barcode_fragment) as BarcodeReaderFragment
            barcodeReader?.setListener(this)

            this.useFlash = false


            val onClickListenerFlash = View.OnClickListener {

                try {
                    this.useFlash = !this.useFlash
                    barcodeReader?.setUseFlash(this.useFlash)
                    when (this.useFlash) {
                        false -> this.buttonFlash?.setImageResource(R.drawable.ic_fragment_qr_flash_off)
                        true -> this.buttonFlash?.setImageResource(R.drawable.ic_fragment_qr_flash_on)
                    }
                }catch (e: Exception){
                    this.useFlash = false
                }


            }

            this.buttonFlash = view.findViewById(R.id.imageViewFlash)
            this.buttonFlash?.setImageResource(R.drawable.ic_fragment_qr_flash_off)
            this.buttonFlash?.setOnClickListener(onClickListenerFlash)
        }
    }

    override fun onHide() {
        super.onHide()
        try {
            this.useFlash = false
            barcodeReader?.setUseFlash(this.useFlash)
        }catch (e : java.lang.Exception) {
        }

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
        Toast.makeText(activity, "Barcodes: $finalCodes", Toast.LENGTH_SHORT).show()
    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>) {

    }

    override fun onScanError(errorMessage: String) {
        Log.e(TAG, "onScanError: $errorMessage")
    }

    override fun onCameraPermissionDenied() {
        Toast.makeText(activity, "Camera permission denied!", Toast.LENGTH_LONG).show()
    }

    companion object {
        private val TAG = FragmentScan::class.java.simpleName

        fun newInstance(): FragmentScan {
            return FragmentScan()
        }
    }
}