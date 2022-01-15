package com.neo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.neo.R
import com.neo.viewmodel.FibonacciViewModel

class FibonacciActivity : AppCompatActivity() {
    lateinit var etCantidad:EditText
    lateinit var etNumComienzo:EditText
    lateinit var bComienzo: Button
    lateinit var tvSecuencia:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fibonacci)

        val fiboVM = ViewModelProvider(this).get(FibonacciViewModel::class.java)
        Inicializar()
        etCantidad.addTextChangedListener( object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                bComienzo.isEnabled = etCantidad.text.length >=1
            }
        })

        bComienzo.setOnClickListener(
                View.OnClickListener {
                    if (etNumComienzo.text.toString()=="")
                        tvSecuencia.text = "Secuencia:" + fiboVM.SecuenciaFibonacci(etCantidad.text.toString().toInt())
                    else
                        tvSecuencia.text = "Secuencia:" + fiboVM.SecuenciaFibonacci(etCantidad.text.toString().toInt(), etNumComienzo.text.toString().toInt())
                }
        )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun Inicializar(){
        etCantidad = findViewById(R.id.fibo_et_cantidad)
        etNumComienzo = findViewById(R.id.fibo_et_numComienzo)
        bComienzo = findViewById(R.id.fibo_btn_empezar)
        tvSecuencia =findViewById(R.id.fibo_t_secuencia)
    }
}

