package com.neo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.neo.R

class MainActivity : AppCompatActivity() {

    lateinit var actividadUno: Button
    lateinit var actividadDos: Button
    lateinit var actividadTres: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarElementos()

        actividadUno.setOnClickListener(
                View.OnClickListener {
                    val intent: Intent = Intent(this, PalindromoActivity::class.java)

                    startActivity(intent)
                }
        )
        actividadDos.setOnClickListener(
                View.OnClickListener {
                    val intent: Intent = Intent(this, TemperaturaActivity::class.java)
                    startActivity(intent)
                }
        )
        actividadTres.setOnClickListener(
                View.OnClickListener {
                    val intent: Intent = Intent(this, FibonacciActivity::class.java)
                    startActivity(intent)
                }
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    private fun iniciarElementos() {
        actividadUno = findViewById(R.id.btn_actividadUno)
        actividadDos = findViewById(R.id.btn_actividadDos)
        actividadTres = findViewById(R.id.btn_actividadTres)
    }
}