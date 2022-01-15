package edu.neo.veterinariaplus.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import edu.neo.veterinariaplus.R
import edu.neo.veterinariaplus.viewmodel.ModificarMascotaViewModel
import org.w3c.dom.Text

class ModificarMascotaActivity : AppCompatActivity() {

    lateinit var id:TextView
    lateinit var nombre:TextView
    lateinit var causaAtencion:TextView
    lateinit var resultado:TextView
    lateinit var medicamentos:TextView
    lateinit var causado:TextView
    lateinit var finalizar:Button
    lateinit var eliminar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_mascota)
        title = "Diagnóstico"
        val modificarVM = ViewModelProvider(this).get(ModificarMascotaViewModel::class.java)
        Inicializar()
        MapeoDatos()

        eliminar.setOnClickListener(
            View.OnClickListener {
                if (modificarVM.deleteDate(this, id.text.toString())) {
                    Toast.makeText(this, "Dado de baja con EXITO", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                } else Toast.makeText(this, "Hubo un problema", Toast.LENGTH_SHORT).show()
            }
        )
        finalizar.setOnClickListener(
            View.OnClickListener {
                if (modificarVM.updateDate(
                        this,
                        id.text.toString(),
                        resultado.text.toString(),
                        medicamentos.text.toString(),
                        causado.text.toString()
                    )
                ) {
                    Toast.makeText(this, "Consulta finalizada", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                } else Toast.makeText(this, "Hubo un problema", Toast.LENGTH_SHORT).show()
            }
        )



    }

    fun Inicializar(){
        id = findViewById(R.id.mm_id)
        nombre = findViewById(R.id.mm_nombre)
        causaAtencion = findViewById(R.id.mm_causa_atencion)
        resultado = findViewById(R.id.mm_resultado)
        medicamentos = findViewById(R.id.mm_medicamentos)
        causado = findViewById(R.id.mm_causa)
        finalizar = findViewById(R.id.mm_finalizar)
        eliminar = findViewById(R.id.mm_eliminar)

    }


    fun MapeoDatos(){
        id.setText(intent.getStringExtra("id"))
        nombre.setText("Nombre: ${intent.getStringExtra("nombre")}" )
        causaAtencion.setText("Ingresado por: ${intent.getStringExtra("causas")}")
    }
}