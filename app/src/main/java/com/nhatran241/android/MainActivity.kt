package com.nhatran241.android

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.nhatran241.android.service.MyService

class MainActivity : AppCompatActivity() {

    private val REQUESTACCESSIBILITY = 2222
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!MyService.isServiceConnected) {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivityForResult(intent, REQUESTACCESSIBILITY)
        }
    }

    fun click(view: View) {
        val intent = Intent(this,MyService::class.java)
        intent.action="click"
        startService(intent)
    }
}