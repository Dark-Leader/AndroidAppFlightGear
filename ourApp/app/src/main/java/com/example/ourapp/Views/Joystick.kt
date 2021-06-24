package com.example.ourapp.Views


import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class Joystick @JvmOverloads constructor(
    context: Context, atts: AttributeSet? = null, defStyleAttr: Int = 0)
    :View(context, atts, defStyleAttr) {

    private val paint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.GREEN
        isAntiAlias = true;

    }
    private lateinit var extraCanvas: Canvas
    private var radius: Float = 200f
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    lateinit var func: (Float, Float) -> Unit
    private var screenWidth :Int = Resources.getSystem().displayMetrics.widthPixels
    private var screenHeight: Int = Resources.getSystem().displayMetrics.heightPixels

    private var center: PointF = PointF()
//to change.

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        val x = width.toFloat() / 2
        val y = height.toFloat() / 2
        center = PointF(x, y);
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return false
        }
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
            MotionEvent.ACTION_DOWN -> touchMove(event.x, event.y)
        }
        return true
    }

    private fun touchMove(x: Float, y: Float) {
        if (x < 0) {
            centerX = 0f
        } else if (x > width) {
            centerX = width.toFloat()
        } else {
            centerX = x
        }

        if (y < 0) {
            centerY = 0f
        } else if (y > height) {
            centerY = height.toFloat()
        } else {
            centerY = y
        }

        center = PointF(centerX,centerY)
        func(centerX,centerY)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(center.x, center.y, radius, paint)
    }


}
