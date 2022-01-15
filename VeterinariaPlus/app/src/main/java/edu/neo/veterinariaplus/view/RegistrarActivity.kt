package edu.neo.veterinariaplus.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.neo.veterinariaplus.R
import edu.neo.veterinariaplus.model.Medico
import edu.neo.veterinariaplus.viewmodel.RegistrarViewModel
import java.lang.reflect.Array
import java.time.LocalDate
import java.util.*

class RegistrarActivity : AppCompatActivity() {
    lateinit var nombre: EditText
    lateinit var tipo: Spinner
    lateinit var raza: Spinner
    lateinit var doctor: Spinner
    lateinit var edad: EditText
    lateinit var causa: EditText
    lateinit var registrar: Button
    lateinit var cancelar: Button
    val tipos = arrayOf("Seleccione tipo de mascota", "Conejo", "Gato", "Perro")
    var razas = arrayOf("")
    var tipoSeleccionado: String = ""
    var razaSeleccionada: String = ""
    var doctorSeleccionado: String = ""
    lateinit var registrarVM: RegistrarViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        title = "Registro de Mascota"


        Inicializar()
        InicializarTipo()
        val doctores = InicializarDoctor()

        cancelar.setOnClickListener(
            View.OnClickListener {
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        )

        //Se selecciona un tipo de mascota


        tipo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //Al seleccionar mascota, carga las razas segun corresponda
                tipoSeleccionado = tipos[position]
                InicializarRaza(position)
                when (position) {
                    0 -> razas = arrayOf()
                    1 -> razas = arrayOf(
                        "Blanco de Hotot",
                        "Rex",
                        "Cabeza de leon",
                        "Belier",
                        "English Angora",
                        "Toy o enano",
                        "Gigante de Flandes",
                        "Tan",
                        "Otra"
                    )
                    2 -> razas = arrayOf(
                        "Azul ruso",
                        "Persa",
                        "Siamés",
                        "Angora turco",
                        "Siberiano",
                        "Maine Coon",
                        "Bengalí",
                        "Otra"
                    )
                    3 -> razas = arrayOf(
                        "Alemán",
                        "Golden",
                        "Labrador",
                        "Bulldog",
                        "Siberiano",
                        "Poodle",
                        "Chihuahua",
                        "Otro"
                    )
                }
                comprobarCampos()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "No hay tipo seleccionado", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        raza.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                razaSeleccionada = razas[position]
                comprobarCampos()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "No hay raza Seleccionada", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        doctor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                doctorSeleccionado = doctores[position]
                comprobarCampos()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "No hay medico seleccionado", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        nombre.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                comprobarCampos()
            }
        })
        edad.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                comprobarCampos()
            }
        })
        causa.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                comprobarCampos()
            }
        })
        registrar.setOnClickListener(
            View.OnClickListener {

                if (registrarVM.registrarMascota(
                        nombre.text.toString(),
                        tipoSeleccionado,
                        razaSeleccionada,
                        doctorSeleccionado,
                        edad.text.toString(),
                        causa.text.toString(),
                        this
                    )
                ) {
                    Toast.makeText(this, "Ingresado con EXITO", Toast.LENGTH_SHORT).show()
                    this.finish()
                } else {
                    Toast.makeText(this, "Hubo un ERROR", Toast.LENGTH_SHORT).show()
                }
            })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    private fun Inicializar() {
        nombre = findViewById(R.id.r_nombre)
        tipo = findViewById(R.id.r_tipo)
        raza = findViewById(R.id.r_raza)
        doctor = findViewById(R.id.r_doctor)
        edad = findViewById(R.id.r_edad)
        causa = findViewById(R.id.r_causa)
        registrar = findViewById(R.id.r_registrar)
        cancelar = findViewById(R.id.r_cancelar)
        registrarVM =
            ViewModelProvider(this).get(edu.neo.veterinariaplus.viewmodel.RegistrarViewModel::class.java)
    }

    private fun InicializarTipo() {
        val tipos = arrayOf("Seleccione tipo de mascota", "Conejo", "Gato", "Perro")
        tipo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
    }

    private fun InicializarRaza(position: Int) {
        var razas = arrayOf("")
        when (position) {
            0 -> razas = arrayOf()
            1 -> razas = arrayOf(
                "Blanco de Hotot",
                "Rex",
                "Cabeza de leon",
                "Belier",
                "English Angora",
                "Toy o enano",
                "Gigante de Flandes",
                "Tan",
                "Otra"
            )
            2 -> razas = arrayOf(
                "Azul ruso",
                "Persa",
                "Siamés",
                "Angora turco",
                "Siberiano",
                "Maine Coon",
                "Bengalí",
                "Otra"
            )
            3 -> razas = arrayOf(
                "Alemán",
                "Golden",
                "Labrador",
                "Bulldog",
                "Siberiano",
                "Poodle",
                "Chihuahua",
                "Otro"
            )
        }
        raza.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, razas)
    }

    fun InicializarDoctor():ArrayList<String> {
        var doctores: ArrayList<String> = ArrayList<String>()
        val medicos: ArrayList<Medico> = registrarVM.contarMedicoDia(this)

        doctores.add("seleccione un médico disponible")
        doctores.add("Dr. Julio Pérez")
        doctores.add("Dra. Analia Sampedro")
        doctores.add("Dr. Juan Maestre Larios")
        doctores.add("Dra. Carolina Arribas")
        doctores.add("Dra. María Pilar Peñeloza")

        for (medico in medicos) {
            if (medico.cant >= 5) {
                doctores.remove(medico.nombre)
            }
        }
        doctor.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, doctores)
        return doctores
    }

    private fun comprobarCampos() {
        registrar.isEnabled =
            nombre.text.toString() != "" && (tipoSeleccionado.toString() != "" && tipoSeleccionado.toString() !="Seleccione tipo de mascota") && razaSeleccionada.toString() != "" && (doctorSeleccionado.toString() != "" && doctorSeleccionado.toString() != "seleccione un médico disponible") && edad.text.toString() != "" && causa.text.toString() != ""
    }
}