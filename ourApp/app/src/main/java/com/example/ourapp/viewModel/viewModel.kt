package com.example.ourapp.viewModel

import com.example.ourapp.model.MyModel

class ViewModel (private val model: MyModel) {

    fun updateRudder(value: Double){
        model.rudder = value
    }

    fun updateElevator(value: Double){
        model.elevator = value
    }

    fun updateThrottle(value: Double){
        model.throttle = value
    }

    fun updateAileron(value: Double){
        model.aileron = value
    }
}