package com.example.ourapp.viewModel
import android.widget.SeekBar
import com.example.ourapp.model.MyModel

class ViewModel (private val model: MyModel) {

    var IP: String = ""
        set(value) {
            model.ip = value
            field = value
        }

    var Port: String = ""
        set(value) {
            model.port = value.toInt()
            field = value
        }

    var elevator: Float = 0f
        set(value) {
            model.elevator = value
            field = value
        }

    var aileron: Float = 0f
        set(value) {
            model.aileron = value
            field = value
        }

    var throttle: Float = 0f
        set(value) {
            model.throttle = value
            field = value
        }

    var rudder: Float = 0f
        set(value) {
            model.rudder = value
            field = value
        }

    fun connectToFG() {
        model.connectToServer()
    }

}