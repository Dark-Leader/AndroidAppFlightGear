package com.example.ourapp.viewModel
import com.example.ourapp.model.MyModel

class ViewModel ( val model: MyModel) {
    // ip string from user
    var IP: String = ""
        set(value) {
            model.ip = value
            field = value
        }
    // port number from user
    var Port: String = ""
        set(value) {
            // checking for valid integer provided
            if (value.isEmpty()) {
                model.port = -1
            } else {
                model.port = value.toInt()
            }
            field = value
        }
    // value from joystick
    var elevator: Float = 0f
        set(value) {
            model.updateElevator(value)
            field = value
        }
    // value from joystick
    var aileron: Float = 0f
        set(value) {
            model.updateAileron(value)
            field = value
        }
    // value from vertical seek bar
    var throttle: Float = 0f
        set(value) {
            model.updateThrottle(value)
            field = value
        }
    // value from bottom seek bar
    var rudder: Float = 0f
        set(value) {
            model.updateRudder(value)
            field = value
        }

    fun connectToFG(ip: String, port: String) {
        /*
        This function attempts to connect to flight gear according to the ip, string provided
         */
        IP = ip
        Port = port
        model.attemptToConnect()
    }

    fun switchRunningMode() {
        model.changeRunningMode()
    }

    fun updateRudder(progress: Int) {
        // helper function to update the rudder
        rudder = ((progress - 100).toFloat() / 100)
    }

    fun updateThrottle(progress: Int) {
        // helper function to update the throttle
        throttle = progress.toFloat() / 100

    }

    fun updateElevator(value: Float, height: Int) {
        // helper function to update the elevator
        elevator = -(value - height.toFloat() / 2) / (height.toFloat() / 2)
    }

    fun updateAileron(value: Float, width: Int) {
        // helper function to update the aileron
        aileron = (value - width.toFloat() / 2) / (width.toFloat() / 2)
    }

}