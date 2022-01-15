package com.neo.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.appcompat.app.ActionBar
import androidx.core.text.set
import androidx.lifecycle.ViewModelProvider
import com.neo.R
import com.neo.viewmodel.TemperaturaViewModel
import org.w3c.dom.Text

class TemperaturaActivity : AppCompatActivity() {
    lateinit var celsius: EditText
    lateinit var fahrenheit: EditText
    lateinit var respuesta: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)


        val tempVM = ViewModelProvider(this).get(TemperaturaViewModel::class.java)
        Inicializacion()

        val textWatcher = object : TextWatcher {
            override
            fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override
            fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.hashCode() == celsius.text.hashCode()) {
                    fahrenheit!!.removeTextChangedListener(this)
                    if (celsius!!.text.toString() == "" || celsius!!.text.toString() == "-" || celsius!!.text.toString() == "+")
                        fahrenheit!!.setText(tempVM.celToFah("0"))
                    else
                        fahrenheit!!.setText(tempVM.celToFah(celsius.text.toString()))
                    fahrenheit!!.addTextChangedListener(this)
                }
                if (charSequence.hashCode() == fahrenheit.text.hashCode()) {
                    celsius!!.removeTextChangedListener(this)
                    if (fahrenheit!!.text.toString() == "" || fahrenheit!!.text.toString() == "-" || fahrenheit!!.text.toString() == "+")
                        celsius!!.setText(tempVM.fahToCel("0"))
                    else
                        celsius!!.setText(tempVM.fahToCel(fahrenheit!!.text.toString()))
                    celsius!!.addTextChangedListener(this)
                }
            }

            override
            fun afterTextChanged(editable: Editable?) {
            }
        }

        celsius.addTextChangedListener(textWatcher)
        fahrenheit.addTextChangedListener(textWatcher)

    }



    private fun Inicializacion() {
        celsius = findViewById(R.id.temp_celsius)
        fahrenheit = findViewById(R.id.temp_fahrenheit)
        respuesta = findViewById(R.id.respuesta_temperatura)
    }
}