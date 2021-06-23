package com.example.ourapp.Views

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ourapp.R
import com.example.ourapp.model.MyModel
import com.example.ourapp.viewModel.ViewModel
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    private var viewModel: ViewModel = ViewModel(MyModel())
    private lateinit var IP: EditText
    private lateinit var Port: EditText
    private lateinit var rudder: SeekBar
    private lateinit var throttle: SeekBar
    private lateinit var connectButton: Button

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

    }


}
