package edu.neo.veterinariaplus.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.neo.veterinariaplus.R
import edu.neo.veterinariaplus.view.recycleview.MedicoAdapter
import edu.neo.veterinariaplus.viewmodel.EstadistivasViewModel

class EstadisticasActivity : AppCompatActivity() {

    lateinit var rv_estadisticas:RecyclerView

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)
        title = "Estadísticas de los médicos"

        val estadiVM:EstadistivasViewModel = ViewModelProvider(this).get(EstadistivasViewModel::class.java)

        rv_estadisticas = findViewById(R.id.e_rv_resumen)

        rv_estadisticas.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)

        rv_estadisticas.adapter = MedicoAdapter(estadiVM.getAllDateToDoc(this), estadiVM)


    }
}