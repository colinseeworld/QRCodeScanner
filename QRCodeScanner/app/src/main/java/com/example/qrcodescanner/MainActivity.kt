package com.example.qrcodescanner

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.qrcodescanner.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var btnScan: Button? = null
    var permissions = arrayOf(
        Manifest.permission.CAMERA
    )
    private val CAMERA_REQUEST_CODE = 1001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnScan = binding.button

        btnScan!!.setOnClickListener{
            startActivity(Intent(this,ScannerActivity::class.java))
        }

        checkpermissions()
    }

    private fun checkpermissions(): Boolean {
        val listofpermisssions: MutableList<String> = ArrayList()
        for (perm in permissions) {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    perm
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                listofpermisssions.add(perm)
            }
        }
        if (!listofpermisssions.isEmpty()) {
            ActivityCompat.requestPermissions(this, listofpermisssions.toTypedArray(), CAMERA_REQUEST_CODE)
            return false
        }
        return true
    }
}