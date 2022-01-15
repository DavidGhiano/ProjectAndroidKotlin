package edu.neo.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import edu.neo.R
import edu.neo.model.Persona
import edu.neo.viewmodel.InsertarViewModel
import java.text.SimpleDateFormat
import java.util.*


class InsertarActivity : AppCompatActivity() {
    //Text Input Text
    lateinit var num_dni: TextInputEditText
    lateinit var nombre: TextInputEditText
    lateinit var apellido: TextInputEditText
    lateinit var fechaNacimiento: TextInputEditText
    lateinit var direccion: TextInputEditText
    lateinit var telefono: TextInputEditText
    lateinit var ingreso: TextInputEditText

    //Button
    lateinit var agregar: Button
    lateinit var calendario: ImageButton

    //Text Input Layout
    lateinit var tipo_tipo: TextInputLayout
    lateinit var num_dni_field: TextInputLayout
    lateinit var nombre_field: TextInputLayout
    lateinit var apellido_field: TextInputLayout
    lateinit var fechaNacimiento_field: TextInputLayout
    lateinit var ingreso_Field: TextInputLayout

    //Otros
    lateinit var tipo_dni: AutoCompleteTextView
    lateinit var ocupacion: AutoCompleteTextView
    lateinit var rgsexo: RadioGroup
    lateinit var id: TextView
    lateinit var masculino: RadioButton
    lateinit var femenino: RadioButton

    @ExperimentalStdlibApi
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar)

        Inicializar()

        val insertarVM = ViewModelProvider(this).get(InsertarViewModel::class.java)


        val fecha = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Seleccionar Fecha")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        cargaListas()
        ingreso.setText("0")

        if (intent.getStringExtra("id") != null) {
            mapeoDatos(insertarVM)
        }

        num_dni.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    ValidarCampos(num_dni, num_dni_field)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )
        nombre.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    ValidarCampos(nombre, nombre_field)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )
        apellido.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    ValidarCampos(apellido, apellido_field)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )
        fechaNacimiento.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    ValidarCampos(fechaNacimiento, fechaNacimiento_field)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )
        ingreso.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    ValidarCampos(ingreso, ingreso_Field)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )

        calendario.setOnClickListener {
            fecha.show(supportFragmentManager, "tag")
        }

        fecha.addOnPositiveButtonClickListener {
            val date = Date(it + (3 * 3600 * 1000))
            val formato = SimpleDateFormat("dd/MM/yyyy")
            fechaNacimiento.setText(formato.format(date))
        }

        agregar.setOnClickListener {
            val selector: Int = rgsexo!!.checkedRadioButtonId
            val rb_selector: RadioButton = findViewById(selector)
            val sexo: String = rb_selector!!.text.toString()

            if (!ValidarObligatorios()) {
                if (agregar.text.toString().uppercase() == "AGREGAR") {
                    if (insertarVM.savePersona(
                            null,
                            tipo_dni.text.toString(),
                            num_dni.text.toString().toInt(),
                            nombre.text.toString().capitalize(),
                            apellido.text.toString().capitalize(),
                            fechaNacimiento.text.toString(),
                            sexo,
                            direccion.text.toString(),
                            telefono.text.toString(),
                            ocupacion.text.toString(),
                            ingreso.text.toString(),
                            this
                        )
                    ) {
                        val intent:Intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("mensaje","Agregado con EXITO")
                        startActivity(intent)
                        this.finish()

                    } else {
                        Toast.makeText(this, "FALLÓ :'(", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    if (insertarVM.updatePersona(
                            id.text.toString().toInt(), tipo_dni.text.toString(),
                            num_dni.text.toString().toInt(),
                            nombre.text.toString().capitalize(),
                            apellido.text.toString().capitalize(),
                            fechaNacimiento.text.toString(),
                            sexo,
                            direccion.text.toString(),
                            telefono.text.toString(),
                            ocupacion.text.toString(),
                            ingreso.text.toString(),
                            this
                        )
                    ) {
                        val intent:Intent = Intent(this, BuscarActivity::class.java)
                        intent.putExtra("mensaje","Modificado con EXITO")
                        startActivity(intent)
                        this.finish()

                    } else {
                        Toast.makeText(this, "FALLÓ :'(", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun Inicializar() {
        //Text Input Text
        num_dni = findViewById(R.id.ins_num_dni_text)
        nombre = findViewById(R.id.ins_nombre_text)
        apellido = findViewById(R.id.ins_apellido_text)
        fechaNacimiento = findViewById(R.id.ins_fecnac_text)
        direccion = findViewById(R.id.ins_direccion_text)
        telefono = findViewById(R.id.ins_telefono_text)
        ingreso = findViewById(R.id.ins_ingreso_text)

        //Button
        agregar = findViewById(R.id.ins_Agregar)
        calendario = findViewById(R.id.ins_calendar)

        //Text Input Layout
        tipo_tipo = findViewById(R.id.ins_tipo_dni)
        num_dni_field = findViewById(R.id.ins_num_dni)
        nombre_field = findViewById(R.id.ins_nombre)
        apellido_field = findViewById(R.id.ins_apellido)
        fechaNacimiento_field = findViewById(R.id.ins_fecnac)
        ingreso_Field = findViewById(R.id.ins_ingreso)

        //Otro
        tipo_dni = findViewById(R.id.ins_tipo_doc)
        ocupacion = findViewById(R.id.ins_ocupacion)
        rgsexo = findViewById(R.id.ins_sexo)
        id = findViewById(R.id.ins_id)
        masculino = findViewById(R.id.ins_masculino)
        femenino = findViewById(R.id.ins_femenino)

    }

    private fun ValidarCampos(texto: TextInputEditText, text_campo: TextInputLayout) {
        if (texto.text.toString() == "") {
            text_campo.error = getString(R.string.obligatorio)
        } else {
            text_campo.error = null
        }
    }

    private fun ValidarObligatorios(): Boolean {
        return if (tipo_dni.text.toString().equals("")
            || num_dni.text.toString().equals("")
            || nombre.text.toString().equals("")
            || apellido.text.toString().equals("")
            || fechaNacimiento.text.toString().equals("")
            || ingreso.text.toString().equals("")
        ) {

            ValidarCampos(num_dni, num_dni_field)
            ValidarCampos(nombre, nombre_field)
            ValidarCampos(apellido, apellido_field)
            ValidarCampos(fechaNacimiento, fechaNacimiento_field)
            if (ocupacion.text.toString() != "Desocupado") {
                ValidarCampos(ingreso, ingreso_Field)
            }
            true
        } else {
            false
        }
    }

    private fun mapeoDatos(insertVM: InsertarViewModel) {
        val id_modificar = intent.getStringExtra("id")
        val persona: Persona? = insertVM.getPersonaId(applicationContext, id_modificar!!)
        if (persona != null) {
            id.text = persona.id.toString()
            cargaListas(persona.tipoDoc,persona.ocupacion)
            num_dni.setText(persona.numDoc.toString())
            nombre.setText(persona.nombre)
            apellido.setText(persona.apellido)
            fechaNacimiento.setText(persona.fecNac)
            if (persona.sexo.equals("Masculino")) {
                masculino.isChecked = true
                femenino.isChecked = false
            } else {
                masculino.isChecked = false
                femenino.isChecked = true
            }
            direccion.setText(persona.direccion)
            telefono.setText(persona.telefono)
            ingreso.setText(persona.ingreso)
            agregar.text = "Modificar"
        }


    }

    private fun cargaListas(tipoDni:String = "Documento Único",tipoOcu:String = "Desocupado"){
        val itemDoc = listOf("Documento Único", "Libreta de Enrolamiento", "Libreta Cívica")
        val adapterDoc = ArrayAdapter(this, R.layout.lista_dni, itemDoc)
        tipo_dni.setText(tipoDni)
        tipo_dni.setAdapter(adapterDoc)
        val itemOcupacion = listOf("Desocupado", "Estudiante", "Empleado", "Monotribustita", "Otro")
        val adapterOcupacion = ArrayAdapter(this, R.layout.lista_oficios, itemOcupacion)
        ocupacion.setText(tipoOcu)
        ocupacion.setAdapter(adapterOcupacion)
    }
}
