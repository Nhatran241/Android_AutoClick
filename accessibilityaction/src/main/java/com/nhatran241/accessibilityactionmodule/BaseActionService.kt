package com.nhatran241.accessibilityactionmodule

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.os.Handler
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.nhatran241.accessibilityactionmodule.model.Action
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

open class BaseActionService : AccessibilityService() {

    enum class Response{
        COMPLETED,FAILED,ACTIONNULL
    }
    companion object{
        const val TAG = "accessibility"
    }
    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
    }

    override fun onInterrupt() {
    }

    fun performAction(actions: MutableList<Action>, callback: ((String) -> Unit)) {
        if (actions.isEmpty()) {
            callback.invoke(Response.COMPLETED.toString())
        } else {
            val gestureDescription = actions.first().gestureDescription
            if(gestureDescription!=null){
                Observable.timer(actions[0].delayTime, TimeUnit.MILLISECONDS).doOnComplete {
                    dispatchGesture(gestureDescription, object : GestureResultCallback() {
                        override fun onCompleted(gestureDescription: GestureDescription) {
                            super.onCompleted(gestureDescription)
                            actions.removeFirst()
                            performAction(actions, callback)
                        }

                        override fun onCancelled(gestureDescription: GestureDescription) {
                            super.onCancelled(gestureDescription)
                            callback.invoke(Response.FAILED.toString())
                        }
                    }, null)
                }.observeOn(Schedulers.io()).subscribe()
            }else{
                actions.removeFirst()
                performAction(actions, callback)
            }

        }
    }
}