package com.example.ourapp.Views
import android.content.Context
import android.view.View


import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import java.util.jar.Attributes

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
    private var center: PointF = PointF()
//to change.

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        val x = width.toFloat() / 2
        val y = height.toFloat() / 2
        center = PointF(x, y);
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return true
        }
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
            MotionEvent.ACTION_DOWN -> touchMove(event.x, event.y)
        }
        return true
    }

    private fun touchMove(x: Float, y: Float) {
        center = PointF(x,y)
        invalidate()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(center.x, center.y, radius, paint)

    }


}
