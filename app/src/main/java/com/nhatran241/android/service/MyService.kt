package com.nhatran241.android.service

import android.content.Intent
import android.graphics.RectF
import android.util.Log
import com.nhatran241.accessibilityactionmodule.BaseActionService
import com.nhatran241.accessibilityactionmodule.model.Action
import com.nhatran241.accessibilityactionmodule.model.ClickAction
import kotlin.math.log

class MyService : BaseActionService(){
    companion object{
        var isServiceConnected=false
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent!=null){
            if(intent.action.equals("click")){
                performAction(ClickAction(RectF(0f,0f,720f,1500f),0,100,50)) {
                    Log.d(TAG, "onServiceConnected: $it")
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onServiceConnected() {
        isServiceConnected=true
        super.onServiceConnected()
    }
}