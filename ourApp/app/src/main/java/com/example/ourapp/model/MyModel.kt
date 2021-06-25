package com.example.ourapp.model

import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread


class MyModel {

    var ip: String = "" // ip address of pc running Flightgear
    var port: Int = -1 // port number that Flightgear listens to
    // variables for logic.
    var throttle: Float = 0f
    var rudder: Float = 0f
    var aileron: Float = 0f
    var elevator: Float = 0f
    private var socket: Socket? = null
    private lateinit var outputStream: OutputStream
    var running: Boolean = true
    var connected = false // flag to check if we managed to connect
    // queue that will hold the tasks the model will perform
    private val queue:BlockingQueue<Runnable> = LinkedBlockingQueue()

    init {
        thread{ runThread() }
    }

    private fun runThread() {
        // while we want to run the model
        while (running) {
            try {
                queue.take().run() // get runnable from queue and run it
            } catch (e: Exception) {
                changeRunningMode() // stop running
            }
        }
    }

    fun changeRunningMode() {
        // add runnable to queue that will cause the thread to stop running
        queue.put(Runnable {
            running = !running
        })
    }

    fun attemptToConnect() {
        /*
        This function adds a runnable to the queue that will attempt to connect
        to the IP,Port provided
         */
        queue.put(Runnable {
            if (!connected) {
                try {
                    // try to connect
                    socket = Socket(ip, port)
                    outputStream = socket!!.getOutputStream()
                    println("CONNECTED TO IP: $ip, PORT: $port")
                    // we managed to connect
                    connected = true
                } catch (e: Exception) {
                    // didn't connect
                    connected = false
                    println(e)
                    println(e.message)
                }
            }
        })
    }

    fun updateRudder(rudderValue: Float) {
        /*
        This function adds a runnable to the queue that will update the value of rudder
        and send the update the flight gear
         */
        queue.put(Runnable {
            if (connected) { // change value and send update to flightgear only if we are already connected
                rudder = rudderValue
                val command = "set /controls/flight/rudder $rudder\r\n"
                write(command)
            }
        })
    }

    fun updateThrottle(throttleValue: Float) {
        /*
        This function adds a runnable to the queue that will update the value of throttle
        and send the update the flight gear
         */
        queue.put(Runnable {
            if (connected) { // change value and send update to flightgear only if we are already connected
                throttle = throttleValue
                val command = "set /controls/engines/current-engine/throttle $throttle\r\n"
                write(command)
            }
        })
    }

    fun updateElevator(elevatorValue: Float) {
        /*
        This function adds a runnable to the queue that will update the value of elevator
        and send the update the flight gear
         */
        queue.put(Runnable {
            if (connected) { // change value and send update to flightgear only if we are already connected
                elevator = elevatorValue
                val command = "set /controls/flight/elevator $elevator\r\n"
                write(command)
            }
        })
    }

    fun updateAileron(aileronValue: Float) {
        /*
        This function adds a runnable to the queue that will update the value of aileron
        and send the update the flight gear
         */
        queue.put(Runnable {
            if (connected) { // change value and send update to flightgear only if we are already connected
                aileron = aileronValue
                val command = "set /controls/flight/aileron $aileron\r\n"
                write(command)
            }
        })
    }

    private fun write(command: String) {
        /*
        helper function to write a string into the OutputStream of a socket
         */
        outputStream.write(command.toByteArray(Charset.defaultCharset()))
        outputStream.flush()
    }

    fun connected(): Boolean {
        /*
        Helper function to check if we managed to connect to the IP,Port provided
         */
        return connected
    }
}