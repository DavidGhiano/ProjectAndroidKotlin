package edu.neo.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Adapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.neo.R
import edu.neo.model.Persona
import edu.neo.view.recycleview.PersonasAdapter
import edu.neo.viewmodel.BuscarViewModel
import java.nio.file.WatchEvent

class BuscarActivity : AppCompatActivity() {

    lateinit var rv_buscar:RecyclerView
    lateinit var adapter:PersonasAdapter
    lateinit var buscador:EditText
    lateinit var mensaje:TextView

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        val buscarVM = ViewModelProvider(this).get(BuscarViewModel::class.java)

        rv_buscar = findViewById(R.id.rv_bus_persona)
        buscador = findViewById(R.id.busq_num_dni)
        mensaje = findViewById(R.id.bus_mensaje)

        rv_buscar.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)

        adapter = PersonasAdapter(buscarVM.getAllPersonas(this),buscarVM)

        rv_buscar.adapter = adapter

        buscador.addTextChangedListener(
            object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Handler().postDelayed({
                        adapter = PersonasAdapter(buscarVM.getAllPersonasxDNI(applicationContext,buscador.text.toString()),buscarVM)
                        rv_buscar.adapter = adapter
                    },1500)
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )
        if (intent.getStringExtra("mensaje") != null) {
            mensaje.setText(intent.getStringExtra("mensaje"))
            Handler().postDelayed({
                mensaje.setText("")
            },3000)
        }



    }

    fun finishMe() { finish() }


}