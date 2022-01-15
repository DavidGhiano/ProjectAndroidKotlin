package edu.neo.veterinariaplus.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.neo.veterinariaplus.R
import edu.neo.veterinariaplus.view.recycleview.MascotaAdapter
import edu.neo.veterinariaplus.viewmodel.AtencionViewModel

class AtencionActivity : AppCompatActivity() {
    lateinit var doctor: Spinner
    lateinit var rv_mascotas: RecyclerView
    var doctores = arrayOf(
        "Seleccione un médico",
        "Dr. Julio Pérez",
        "Dra. Analia Sampedro",
        "Dr. Juan Maestre Larios",
        "Dra. Carolina Arribas",
        "Dra. María Pilar Peñeloza"
    )

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atencion)
        title = "Citas programadas"
        val atencionVM = ViewModelProvider(this).get(AtencionViewModel::class.java)
        Inicializar()
        InicializarSpinner()

        var doctorSeleccionado: String = ""
        doctor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                doctorSeleccionado = doctores[position]
                rv_mascotas.layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
                rv_mascotas.adapter = MascotaAdapter(
                    atencionVM.getAllDateToday(
                        applicationContext,
                        doctorSeleccionado
                    )
                )
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(
                    applicationContext,
                    " No hay medico seleccionado",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }

    private fun Inicializar() {
        doctor = findViewById(R.id.at_doctor)
        rv_mascotas = findViewById(R.id.at_rv_mascotas)
    }

    private fun InicializarSpinner() {
        doctor.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, doctores)
    }

}