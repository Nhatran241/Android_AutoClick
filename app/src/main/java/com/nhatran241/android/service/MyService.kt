package com.nhatran241.android.service

import android.content.Intent
import android.graphics.RectF
import android.util.Log
import com.nhatran241.accessibilityactionmodule.BaseActionService
import com.nhatran241.accessibilityactionmodule.model.ClickAction
import com.nhatran241.accessibilityactionmodule.model.SwipeAction

class MyService : BaseActionService(){
    companion object{
        var isServiceConnected=false
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent!=null){
            if(intent.action.equals("click")){
                val swipe  = SwipeAction(RectF(500f,500f,500f,500f), RectF(0f,500f,0f,500f),0,100,5000)
                val click1= ClickAction(RectF(0f,0f,500f,0f),0,100,5000)
                performAction(mutableListOf(swipe,click1)) {
                    Log.d(TAG, "onStartCommand: $it")
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