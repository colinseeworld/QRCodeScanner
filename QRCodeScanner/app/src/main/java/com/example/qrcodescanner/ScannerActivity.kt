package com.example.qrcodescanner

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.example.qrcodescanner.databinding.ActivityScannerBinding

class ScannerActivity : AppCompatActivity() {

    lateinit var binding: ActivityScannerBinding
    var txt: TextView? = null
    var codeScanner: CodeScanner? = null
    var codeScannerView: CodeScannerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txt = binding.textView
        codeScannerView = binding.scannerView
        codeScanner = CodeScanner(this, codeScannerView!!)

        codeScanner!!.decodeCallback =
            DecodeCallback { result -> runOnUiThread { txt!!.text = result.text } }

    }
    override fun onResume() {
        super.onResume()
        requestCamera()
    }

    private fun requestCamera() {
        codeScanner!!.startPreview()
    }
}