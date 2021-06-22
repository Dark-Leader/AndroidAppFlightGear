package com.example.ourapp.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.ourapp.databinding.ActivityMainBinding
import com.example.ourapp.model.MyModel
import com.example.ourapp.viewModel.ViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: ViewModel = ViewModel(MyModel())
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val josystickView = Joystick(this)
        setContentView(josystickView)



    }


}