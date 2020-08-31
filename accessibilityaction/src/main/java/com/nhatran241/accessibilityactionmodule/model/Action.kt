package com.nhatran241.accessibilityactionmodule.model

import android.accessibilityservice.GestureDescription

open class Action {
    var actionTime : Long = System.currentTimeMillis()
    var delayTime : Long =0
    var gestureDescription: GestureDescription?=null
}