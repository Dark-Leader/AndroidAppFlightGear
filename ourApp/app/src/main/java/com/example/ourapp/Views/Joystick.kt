package com.example.ourapp.Views


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class Joystick @JvmOverloads constructor(
    context: Context, atts: AttributeSet? = null, defStyleAttr: Int = 0)
    :View(context, atts, defStyleAttr) {
    // color to draw the joystick with
    private val paint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.GREEN
        isAntiAlias = true

    }
    // variables for logic
    private var radius: Float = 200f
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    // will be used for dependency injection every time we update the location of the joystick
    lateinit var func: (Float, Float) -> Unit

    private var center: PointF = PointF()

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        /*
        This is called during layout when the size of this view has changed.
         If you were just added to the view hierarchy, you're called with the old values of 0.
         we set the default location of joystick in the middle of grid
         */
        val x = width.toFloat() / 2
        val y = height.toFloat() / 2
        center = PointF(x, y)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        /*
        This function listens for all touch events on the grid
        if we clicked, or drag-moved the joystick we update its location
         */
        if (event == null) {
            return false
        }
        when (event.action) {
            // update location
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
            MotionEvent.ACTION_DOWN -> touchMove(event.x, event.y)
        }
        return true
    }

    private fun touchMove(x: Float, y: Float) {
        /*
        This function calculates new location for the joystick on the grid
         */
        if (x < 0) { // check for out of bounds
            centerX = 0f
        } else if (x > width) { // check for out of bounds
            centerX = width.toFloat()
        } else {
            centerX = x
        }

        if (y < 0) { // check for out of bounds
            centerY = 0f
        } else if (y > height) { // check for out of bounds
            centerY = height.toFloat()
        } else {
            centerY = y
        }
        // update center of circle
        center = PointF(centerX,centerY)
        // call dependency injection function
        func(centerX,centerY)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        /*
        draw the joystick on the canvas
         */
        super.onDraw(canvas)
        canvas.drawCircle(center.x, center.y, radius, paint)
    }


}
