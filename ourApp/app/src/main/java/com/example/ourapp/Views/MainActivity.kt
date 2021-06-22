package com.example.ourapp.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.ourapp.databinding.ActivityMainBinding
import com.example.ourapp.model.MyModel

class MainActivity : AppCompatActivity() {
    private var model: MyModel = MyModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}