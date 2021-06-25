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
    // variables for logic
    private var viewModel: ViewModel = ViewModel(MyModel())
    private lateinit var IP: EditText // ip address
    private lateinit var Port: EditText // port number
    private lateinit var rudder: SeekBar // rudder seek bar
    private lateinit var throttle: VerticalSeekBar // throttle seek bar
    private lateinit var connectButton: Button // attempt to connect button
    private lateinit var joystick: Joystick // joystick for aileron, elevator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get EditText for ip and port
        IP = findViewById(R.id.IP)
        Port = findViewById(R.id.Port)
        connectButton = findViewById(R.id.Connect)
        connectButton.setOnClickListener { // set on click listener
            var ip:String = IP.text.toString()
            var port:String = Port.text.toString()
            println("trying to connect. IP: $ip, Port: $port")
            viewModel.connectToFG(ip, port) // try to connect to ip, port provided
            Thread.sleep(500)
            if (!viewModel.model.connected()) { // if we didn't managed to connect
                val alertDialog = AlertDialog.Builder(this@MainActivity).create() // create error message
                alertDialog.setTitle("ERROR")
                alertDialog.setMessage("Failed to connect\nPlease try again")
                alertDialog.setButton(
                    AlertDialog.BUTTON_NEUTRAL, "OK"
                ) { dialog, which -> dialog.dismiss() }
                alertDialog.show() // display error message
            }
        }
        rudder = findViewById(R.id.seekBarRudder)
        rudder.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            /*
            set click listener for rudder seek bar to update the value of the rudder
             */
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
            /*
            set click listener for throttle seek bar to update the value of the rudder
             */
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
        // dependency injection of funtion inside the joystick to update the view model of the values of the aileron, elevator
        joystick.func = { aileron: Float, elevator: Float ->
            viewModel.updateAileron(aileron, joystick.width) // update aileron
            viewModel.updateElevator(elevator, joystick.height) // update elevator
        }

    }

    override fun onStop() {
        super.onStop()
        viewModel.switchRunningMode() // stop the thread that the model created
    }

}
