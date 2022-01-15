package edu.neo.tpfinal.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import edu.neo.tpfinal.R
import edu.neo.tpfinal.model.Dieta
import edu.neo.tpfinal.viewmodel.AgregarViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class AgregarFragment : Fragment() {

    //Switch
    private lateinit var postre_sw: Switch
    private lateinit var antojo_sw: Switch
    private lateinit var hambre_sw: Switch

    //TextinputLayout
    private lateinit var tipo_comida_field: TextInputLayout
    private lateinit var principal_field: TextInputLayout
    private lateinit var secundaria_field: TextInputLayout
    private lateinit var bebida_field: TextInputLayout
    private lateinit var postre_field: TextInputLayout
    private lateinit var antojo_field: TextInputLayout

    //TextInputEditText
    private lateinit var principal: TextInputEditText
    private lateinit var secundaria: TextInputEditText
    private lateinit var bebida: TextInputEditText
    private lateinit var postre: TextInputEditText
    private lateinit var antojo: TextInputEditText

    //Otros
    private lateinit var mostrarPostre: LinearLayout
    private lateinit var tipo_comida: AutoCompleteTextView
    private lateinit var insertar: Button
    private lateinit var fragment_layout: Fragment
    private val PREFERENCE_FILE_KEY: String = "tpfinal.view"
    private val ESTADO_USUARIO: String = "estado.usuario.activo"


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_agregar, container, false)
        val agregarVM = ViewModelProvider(this).get(AgregarViewModel::class.java)
        Inicializar(root)
        val itemComida = listOf("Desayuno", "Almuerzo", "Merienda", "Cena")
        val adapterComida = ArrayAdapter(root.context, R.layout.list_comida, itemComida)
        tipo_comida.setText("Desayuno")
        tipo_comida.setAdapter(adapterComida)
        tipo_comida.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    mostrarPostre.isVisible =
                        tipo_comida.text.toString()
                            .equals("Almuerzo") || tipo_comida.text.toString().equals("Cena")

                }

                override fun afterTextChanged(s: Editable?) {
                }

            }
        )

        postre_sw.setOnCheckedChangeListener { buttonView, isChecked ->
            postre_field.isVisible = isChecked
        }
        antojo_sw.setOnCheckedChangeListener { buttonView, isChecked ->
            antojo_field.isVisible = isChecked
        }
        insertar.setOnClickListener {

            val manager: FragmentManager = requireActivity().supportFragmentManager
            var bebidas = BebidaFragment()
            if (!validacionFinal()) {
                var hambre: String
                val hoursNow = LocalTime.now()
                val dateNow = LocalDate.now()
                val hourFormat = DateTimeFormatter.ofPattern("HH:mm")
                val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                var hora = hoursNow.format(hourFormat)
                var fecha = dateNow.format(dateFormat)
                if (hambre_sw.isChecked) {
                    hambre = "si"
                } else {
                    hambre = "no"
                }
                var dieta = Dieta(
                    tipo_comida.text.toString(),
                    principal.text.toString(),
                    secundaria.text.toString(),
                    bebida.text.toString(),
                    postre.text.toString(),
                    antojo.text.toString(),
                    hambre,
                    fecha,
                    hora
                )
                var userID: String = obtenerUsuario(it.context)
                if (agregarVM.Registrar(dieta, userID, it.context)) {
                    VaciarCampos()

                    val dialogo = BebidaDialogoFragment()
                    dialogo.show(manager,"")

                }
            }
        }

        return root
    }

    private fun VaciarCampos() {
        tipo_comida.setText("")
        principal.setText("")
        secundaria.setText("")
        bebida.setText("")
        postre.setText("")
        antojo.setText("")
    }


    fun Inicializar(view: View) {
        //Switch
        postre_sw = view.findViewById(R.id.agr_sw_postre)
        antojo_sw = view.findViewById(R.id.agr_sw_tentacion)
        hambre_sw = view.findViewById(R.id.agr_hambre)

        //TextInputLayout
        tipo_comida_field = view.findViewById(R.id.agr_tipo_comida_field)
        principal_field = view.findViewById(R.id.agr_com_prin_field)
        secundaria_field = view.findViewById(R.id.agr_com_sec_field)
        bebida_field = view.findViewById(R.id.agr_bebida_field)
        postre_field = view.findViewById(R.id.agr_postre_field)
        antojo_field = view.findViewById(R.id.agr_tentacion_field)

        //TextInputEditText
        tipo_comida = view.findViewById(R.id.agr_tipo_comida)
        principal = view.findViewById(R.id.agr_com_prin)
        secundaria = view.findViewById(R.id.agr_com_sec)
        bebida = view.findViewById(R.id.agre_bebida)
        postre = view.findViewById(R.id.agr_postre)
        antojo = view.findViewById(R.id.agr_tentacion)

        //OTROS
        tipo_comida = view.findViewById(R.id.agr_tipo_comida)
        mostrarPostre = view.findViewById(R.id.agr_layout_postre)
        insertar = view.findViewById(R.id.agr_insertar)
    }

    private fun ValidarCampos(texto: TextInputEditText, text_campo: TextInputLayout) {
        if (texto.text.toString() == "") {
            text_campo.error = getString(R.string.obligatorio)
        } else {
            text_campo.error = null
        }
    }

    private fun validacionFinal(): Boolean {
        return if (principal.text.toString().equals("")
            || secundaria.text.toString().equals("")
            || bebida.text.toString().equals("")
        ) {
            ValidarCampos(principal, principal_field)
            ValidarCampos(secundaria, secundaria_field)
            ValidarCampos(bebida, bebida_field)
            true
        } else {
            false
        }
    }

    private fun obtenerUsuario(context: Context): String {
        val sharePref: SharedPreferences = context.getSharedPreferences(
            PREFERENCE_FILE_KEY,
            AppCompatActivity.MODE_PRIVATE
        )
        return sharePref.getString(ESTADO_USUARIO, "").toString()
    }
}