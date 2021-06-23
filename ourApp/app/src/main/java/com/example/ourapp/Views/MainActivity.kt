package com.example.ourapp.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.ourapp.R
import com.example.ourapp.model.MyModel
import com.example.ourapp.viewModel.ViewModel

class MainActivity : AppCompatActivity() {
    private var viewModel: ViewModel = ViewModel(MyModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }


}
