package com.nhatran241.accessibilityactionmodule

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.os.Handler
import android.view.accessibility.AccessibilityEvent
import com.nhatran241.accessibilityactionmodule.model.Action
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

open class BaseActionService : AccessibilityService() {
   
    companion object{
        const val TAG = "accessibility"
    }
    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
    }

    override fun onInterrupt() {
    }
    fun performAction(action: Action, callback: ((String) -> Unit)){
        val gestureDescription = action.gestureDescription
        if(gestureDescription!=null) {
            Observable.timer(action.delayTime,TimeUnit.MILLISECONDS).doOnComplete {
                dispatchGesture(gestureDescription, object : GestureResultCallback() {
                    override fun onCompleted(gestureDescription: GestureDescription) {
                        super.onCompleted(gestureDescription)
                        callback.invoke("onCompleted")
                    }

                    override fun onCancelled(gestureDescription: GestureDescription) {
                        super.onCancelled(gestureDescription)
                        callback.invoke("onCancelled")
                    }
                }, null)
            }.observeOn(Schedulers.io()).subscribe()

        }else{
            callback.invoke("action dont have gestureDescription")
        }
    }
}