package com.example.ourapp.viewModel
import android.widget.SeekBar
import com.example.ourapp.model.MyModel

class ViewModel (private val model: MyModel) {

    var IP: String = ""
        set(value) {
            println("ip is: $value")
            model.ip = value
            field = value
        }

    var Port: String = ""
        set(value) {
            println("port is: $value")
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
            println("value is: $value")
            model.rudder = value
            field = value
        }

    fun connectToFG(ip: String, port: String) {
        IP = ip
        Port = port
        model.connectToServer()
    }

    fun updateRudder(progress: Int) {
        rudder = ((progress - 100).toFloat() / 100)
    }

    fun updateThrottle(progress: Int) {
        throttle = ((progress - 100).toFloat() / 100)
    }

    fun updateElevator(progress: Int) {
        elevator = ((progress - 100).toFloat() / 100).toFloat()
    }

    fun updateAileron(progress: Int) {
        aileron = ((progress - 100).toFloat() / 100)
    }

}