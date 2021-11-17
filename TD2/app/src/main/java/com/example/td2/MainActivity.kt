package com.example.td2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Change this val to switch from 'repas' to 'meteo'
        val contentView = R.layout.meteo

        setContentView(contentView)

        if (contentView == R.layout.meteo){
            // Took this part from https://developer.android.com/guide/topics/ui/controls/spinner
            val spinner: Spinner = findViewById(R.id.weather_spinner)
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter.createFromResource(
                this,
                R.array.weather,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }

    }
}