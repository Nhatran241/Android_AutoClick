package com.nhatran241.accessibilityactionmodule.model

import android.accessibilityservice.GestureDescription
import android.accessibilityservice.GestureDescription.StrokeDescription
import android.graphics.Path
import android.graphics.RectF
import androidx.annotation.NonNull
import androidx.annotation.Nullable

class SwipeAction(@NonNull startRectF: RectF, @NonNull endRectF: RectF, @Nullable startTime:Long, @Nullable duration: Long, @Nullable delayTime : Long) : Action(){
    var path: Path = Path()
    init {
        this.delayTime =delayTime
        val builder = GestureDescription.Builder()
        path.moveTo(startRectF.centerX(),startRectF.centerY())
        path.lineTo(endRectF.centerX(),endRectF.centerY())
        builder.addStroke(StrokeDescription(path, startTime, duration))
        gestureDescription=builder.build()
    }
}