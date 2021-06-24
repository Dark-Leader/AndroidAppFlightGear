package com.example.ourapp.model

import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import kotlin.concurrent.thread


class MyModel {
    var ip: String = "" // ip address of pc running Flightgear
    var port: Int = -1 // port number that Flightgear listens to
    // variables for logic.
    var throttle: Float = 0f
    var rudder: Float = 0f
    var aileron: Float = 0f
    var elevator: Float = 0f
    var fps: Int = 60
    private var socket: Socket? = null
    var connected = false // flag to check if we managed to connect

    fun connectToServer() {
        /*
        This function opens a thread, and gives it a function to run.
         */
        thread { run() }
    }

    private fun run() {
        /*
        This function attempts to connect to the pc running flight gear
        by opening a socket and trying to connect to said pc with
        the IP,Port fields inside this class
        if we managed to connect, then we begin an infinite loop, where we send
        data the socket, in order to update flight gear of the input of the user.
         */
        try { // an exception is thrown if
            // open socket
            socket = Socket(ip, port)
            var outputStream: OutputStream = socket!!.getOutputStream()
            // if we got here then we managed to connect
            println("CONNECTED TO $ip, $port")
            connected = true
            while (socket!!.isConnected) {
                // update flight gear inside an infinite loop
                // update about throttle,rudder, aileron,elevator values
                write("set /controls/engines/current-engine/throttle $throttle\r\n", outputStream)
                write("set /controls/flight/aileron $aileron\r\n", outputStream)
                write("set /controls/flight/elevator $elevator\r\n", outputStream)
                write("set /controls/flight/rudder $rudder\r\n", outputStream)
                // sleep for 1000 / fps.
                // at default fps is 60 -> so we update flight gear roughly once every 33 milliseconds
                Thread.sleep((1000 / fps).toLong())
            }
        } catch (e: Exception) { // catch error in the attempt to open a socket from invalid IP or Port
            println("ERROR")
            println(e.message)
            connected = false
        } finally {
            /*
            kill the thread at the end
            if we managed to connect then we still need to close the thread
            else if we didn't manage to connect for any reason -> timeout/invalid
            IP,Port then we kill the thread
             */
            Thread.currentThread().join()
        }
    }

    private fun write(command: String, outputStream: OutputStream) {
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