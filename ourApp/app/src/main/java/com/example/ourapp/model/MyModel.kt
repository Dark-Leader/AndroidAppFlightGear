package com.example.ourapp.model

import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset
import kotlin.concurrent.thread


class MyModel {
    var ip: String = ""
    var port: Int = -1

    var throttle: Float = 0f
    var rudder: Float = 0f
    var aileron: Float = 0f
    var elevator: Float = 0f
    var fps: Int = 60

    private var connected: Boolean = false

     fun connectToServer(): Boolean {
        try {
            val socket = Socket(ip, port)
            val outputStream: OutputStream = socket.getOutputStream()
            connected = true
            thread { run(outputStream) }
        } catch (e: Exception) {
            return false
        }
         return true

    }

    private fun run(outputStream: OutputStream) {
        while (connected) {
            write("set /controls/flight/aileron $aileron\r\n", outputStream)
            write("set /controls/flight/elevator $elevator\r\n", outputStream)
            write("set /controls/flight/rudder $rudder\r\n", outputStream)
            write("set /controls/flight/current-engine/throttle $throttle\r\n", outputStream)
            Thread.sleep((1000 / fps).toLong())
        }
    }

    private fun write(command: String, outputStream: OutputStream) {
        outputStream.write(command.toByteArray(Charset.defaultCharset()))
        outputStream.flush()
    }
}