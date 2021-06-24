package com.example.ourapp.Views

import android.os.Bundle
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ourapp.R
import com.example.ourapp.model.MyModel
import com.example.ourapp.viewModel.ViewModel


class MainActivity : AppCompatActivity() {
    private var viewModel: ViewModel = ViewModel(MyModel())
    private lateinit var IP: EditText
    private lateinit var Port: EditText
    private lateinit var rudder: SeekBar
    private lateinit var throttle: VerticalSeekBar
    private lateinit var connectButton: Button
    private lateinit var joystick: Joystick

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        IP = findViewById(R.id.IP)
        Port = findViewById(R.id.Port)
        connectButton = findViewById(R.id.Connect)
        connectButton.setOnClickListener {
            var ip:String = IP.text.toString()
            var port:String = Port.text.toString()
            println("trying to connect. IP: $ip, Port: $port")
            viewModel.connectToFG(ip, port)
            Thread.sleep(250)
            if (viewModel.model.Connected() == false) {
                val alertDialog = AlertDialog.Builder(this@MainActivity).create()
                alertDialog.setTitle("ERROR")
                alertDialog.setMessage("Failed to connect\nPlease try again")
                alertDialog.setButton(
                    AlertDialog.BUTTON_NEUTRAL, "OK"
                ) { dialog, which -> dialog.dismiss() }
                alertDialog.show()
            }
        }
        rudder = findViewById(R.id.seekBarRudder)
        rudder.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                viewModel.updateRudder(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                return
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                return
            }
        })

        throttle = findViewById(R.id.SeekBarThrottle)
        throttle.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                viewModel.updateThrottle(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                return
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                return
            }
        })

        joystick = findViewById(R.id.Joystick)

        joystick.func = { aileron: Float, elevator: Float ->
            viewModel.updateAileron(aileron, joystick.width)
            viewModel.updateElevator(elevator, joystick.height)
        }

    }

}
