package edu.neo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import edu.neo.R
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var nuevo:Button
    lateinit var buscar:Button
    lateinit var estadisticas:Button
    lateinit var mensaje:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nuevo = findViewById(R.id.main_nuevo)
        buscar = findViewById(R.id.main_buscar)
        estadisticas = findViewById(R.id.main_estadisticas)
        mensaje = findViewById(R.id.main_mensaje)

        if (intent.getStringExtra("mensaje") != null) {
            mensaje.setText(intent.getStringExtra("mensaje"))
        }
        Handler().postDelayed({
            mensaje.setText("")
        },3000)



        nuevo.setOnClickListener(
            View.OnClickListener {
                startActivity(Intent(this,InsertarActivity::class.java))
            }
        )

        buscar.setOnClickListener {
            startActivity(Intent(this,BuscarActivity::class.java))
        }
        estadisticas.setOnClickListener {
            startActivity(Intent(this,EstadisticasActivity::class.java))
        }

    }
}