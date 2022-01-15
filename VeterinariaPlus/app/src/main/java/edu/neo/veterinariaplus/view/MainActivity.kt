package edu.neo.veterinariaplus.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import edu.neo.veterinariaplus.R
import edu.neo.veterinariaplus.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    lateinit var registrar: Button
    lateinit var atender: Button
    lateinit var estadisticas:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Veterinaria++"
        val mainVM:MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        Inicializar()
        registrar.setOnClickListener(
            View.OnClickListener {
                if (mainVM.contarCitas(this) < 20) {
                    val intento: Intent = Intent(this, RegistrarActivity::class.java)
                    startActivity(intento)
                }else{
                    Toast.makeText(this,"Se superaron el límite de citas para el día de hoy",Toast.LENGTH_SHORT).show()
                }
            }
        )
        atender.setOnClickListener(
            View.OnClickListener {
                startActivity(Intent(this,AtencionActivity::class.java))
            }
        )
        estadisticas.setOnClickListener(
            View.OnClickListener {
                startActivity(Intent(this,EstadisticasActivity::class.java))
            }
        )



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun Inicializar() {
        registrar = findViewById(R.id.m_registrar)
        atender= findViewById(R.id.m_atencion)
        estadisticas = findViewById(R.id.m_estadisticas)
    }
}