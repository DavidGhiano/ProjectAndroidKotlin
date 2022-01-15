package edu.neo.tpfinal.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.Persona
import edu.neo.tpfinal.model.User
import edu.neo.tpfinal.viewmodel.RegistrarViewModel
import java.text.SimpleDateFormat
import java.util.*

class RegistrarActivity : AppCompatActivity() {

    //TextInputEditText
    lateinit var usuario: TextInputEditText
    lateinit var password: TextInputEditText
    lateinit var nombre: TextInputEditText
    lateinit var apellido: TextInputEditText
    lateinit var numDoc: TextInputEditText
    lateinit var fecnac: TextInputEditText
    lateinit var localidad: TextInputEditText

    //TextInputLayout
    lateinit var usuario_field: TextInputLayout
    lateinit var password_field: TextInputLayout
    lateinit var nombre_field: TextInputLayout
    lateinit var apellido_field: TextInputLayout
    lateinit var numDoc_field: TextInputLayout
    lateinit var fecNac_field: TextInputLayout
    lateinit var localidad_field: TextInputLayout

    //Button
    lateinit var ingFecha: Button
    lateinit var registrar: Button

    //Otros
    lateinit var rgsexo: RadioGroup
    lateinit var tratamiento: AutoCompleteTextView
    private lateinit var firebaseDB: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference
    private lateinit var comprobarUsuario: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        Inicializacion()
        InicializarDB()
        val registrarVM = ViewModelProvider(this).get(RegistrarViewModel::class.java)


        val itemTratamiento = listOf("Anorexia", "Bulimia", "Obesidad")
        val adapterTratamiento = ArrayAdapter(this, R.layout.list_tratamiento, itemTratamiento)
        tratamiento.setText("Anorexia")
        tratamiento.setAdapter(adapterTratamiento)

        val fecha = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Seleccionar Fecha de Nacimiento")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        //Validación de campos a la hora de insertar texto y dejarlos vacios
        usuario.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    ValidarCampos(usuario, usuario_field)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )
        password.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    ValidarCampos(password, password_field)
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
        numDoc.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    ValidarCampos(numDoc, numDoc_field)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )
        localidad.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    ValidarCampos(localidad, localidad_field)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )

        //Botones
        ingFecha.setOnClickListener {
            fecha.show(supportFragmentManager, "tag")
        }
        fecha.addOnPositiveButtonClickListener {
            val date = Date(it + (3 * 3600 * 1000))
            val formato = SimpleDateFormat("dd/MM/yyyy")
            fecnac.setText(formato.format(date))
        }

        registrar.setOnClickListener {
            if (!ValidacionFinal()) {
                val userID: String = usuario.text.toString().toLowerCase()

                dbReference.child("Persona").child(userID).addValueEventListener(
                    object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.exists()) {
                                comprobarUsuario.setText(userID)
                            } else {
                                comprobarUsuario.setText("")
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                        }

                    }
                )
                Handler().postDelayed({
                    if (!comprobarUsuario.text.equals(userID)) {
                        val selector: Int = rgsexo!!.checkedRadioButtonId
                        val rb_selector: RadioButton = findViewById(selector)
                        val sex: String = rb_selector!!.text.toString()
                        var user = User(
                            usuario.text.toString(),
                            password.text.toString()
                        )
                        var persona = Persona(
                            usuario.text.toString().toLowerCase(),
                            nombre.text.toString(),
                            apellido.text.toString(),
                            numDoc.text.toString().toInt(),
                            sex,
                            fecnac.text.toString(),
                            localidad.text.toString(),
                            tratamiento.text.toString(),
                            user,
                            null
                        )

                        if (registrarVM.Registrar(persona, this)) {
                            Toast.makeText(this, "Registrado con Exito", Toast.LENGTH_SHORT).show()
                            this.finish()
                        }
                    } else {
                        usuario_field.error = "usuario ya existente"
                        Handler().postDelayed({
                            usuario_field.error = null
                        }, 5000)
                    }
                }, 3000)



            }
        }

    }

    private fun Inicializacion() {
        //TextInputEditText
        usuario = findViewById(R.id.reg_usuario)
        password = findViewById(R.id.reg_pass)
        nombre = findViewById(R.id.reg_nombre)
        apellido = findViewById(R.id.reg_apellido)
        numDoc = findViewById(R.id.reg_numdoc)
        fecnac = findViewById(R.id.reg_fecnac)
        localidad = findViewById(R.id.reg_loc)

        //TextInputLayout
        usuario_field = findViewById(R.id.reg_usuario_field)
        password_field = findViewById(R.id.reg_pass_field)
        nombre_field = findViewById(R.id.reg_nombre_field)
        apellido_field = findViewById(R.id.reg_apellido_field)
        numDoc_field = findViewById(R.id.reg_numdoc_field)
        fecNac_field = findViewById(R.id.reg_fecnac_field)
        localidad_field = findViewById(R.id.reg_loc_field)

        //Button
        ingFecha = findViewById(R.id.reg_btn_fecha)
        registrar = findViewById(R.id.reg_btn_registrar)

        //Otros
        rgsexo = findViewById(R.id.reg_rgSexo)
        tratamiento = findViewById(R.id.reg_trata)
        comprobarUsuario = findViewById(R.id.reg_comprobar)
    }

    fun InicializarDB() {
        FirebaseApp.initializeApp(this)
        firebaseDB = FirebaseDatabase.getInstance()
        dbReference = firebaseDB.getReference()
    }

    private fun ValidarCampos(texto: TextInputEditText, text_campo: TextInputLayout) {
        if (texto.text.toString() == "") {
            text_campo.error = getString(R.string.obligatorio)
        } else {
            text_campo.error = null
        }
    }

    private fun ValidacionFinal(): Boolean {
        return if (usuario.text.toString().equals("")
            || password.text.toString().equals("")
            || nombre.text.toString().equals("")
            || apellido.text.toString().equals("")
            || numDoc.text.toString().equals("")
            || fecnac.text.toString().equals("")
            || localidad.text.toString().equals("")
        ) {
            ValidarCampos(usuario, usuario_field)
            ValidarCampos(password, password_field)
            ValidarCampos(nombre, nombre_field)
            ValidarCampos(apellido, apellido_field)
            ValidarCampos(numDoc, numDoc_field)
            ValidarCampos(fecnac, fecNac_field)
            ValidarCampos(localidad, localidad_field)
            true
        } else {
            false
        }
    }
}