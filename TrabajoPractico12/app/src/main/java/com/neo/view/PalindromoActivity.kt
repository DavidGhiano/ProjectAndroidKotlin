package com.neo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.neo.R
import com.neo.viewmodel.PalindromoViewModel

class PalindromoActivity : AppCompatActivity() {

    lateinit var comprobar:Button
    lateinit var texto:EditText
    lateinit var resultado:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_palindromo)

        val palindromoVM = ViewModelProvider(this).get(PalindromoViewModel::class.java)
        inicializar()

        //Se habilita el boton comprobar cuando se ingresa tres o más caracteres y se deshabilita cuando es menor de 3
        texto.addTextChangedListener( object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                resultado.text = ""
                comprobar.isEnabled = texto.text.length>=3
            }
        })

        comprobar.setOnClickListener(
                View.OnClickListener {
                    if(palindromoVM.comprobar((texto.text.toString()))) resultado.text = "ES UN PALÍNDROMO"
                    else Toast.makeText(this,"NO ES UN PALÍNDROMO", Toast.LENGTH_SHORT).show()
                }
        )
    }

    fun inicializar(){
        comprobar = findViewById(R.id.uno_comprobar)
        texto = findViewById(R.id.uno_ingreso)
        resultado = findViewById(R.id.uno_resultado)
    }
}