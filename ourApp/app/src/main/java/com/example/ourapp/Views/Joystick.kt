package com.example.ourapp.Views
import android.content.Context
import android.view.View


import android.graphics.*

class Joystick @JvmOverloads constructor(context: Context): View(context) {

    private val paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.GREEN
        isAntiAlias = true;
    }
    private lateinit var extraCanvas: Canvas
    private var radius: Float = 500f
    private var center: PointF = PointF()
//to change.

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        center = PointF(50f, 50f);
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(center.x, center.y, radius,paint)

    }


}
