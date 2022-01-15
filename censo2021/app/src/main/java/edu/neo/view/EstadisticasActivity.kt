package edu.neo.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import edu.neo.R
import edu.neo.fragment.GeneroFragment
import edu.neo.fragment.MayoresFragment
import edu.neo.fragment.PobrezaFragment
import edu.neo.viewmodel.EstadisticaViewModel

class EstadisticasActivity : AppCompatActivity() {
    lateinit var mayor: Button
    lateinit var pobre: Button
    lateinit var sexo: Button

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)

        Inicializar()

        val manager = supportFragmentManager

        mayor.setOnClickListener {
            val transaction = manager.beginTransaction()
            val fragmento = MayoresFragment()
            transaction.replace(R.id.est_layout, fragmento)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        pobre.setOnClickListener {
            val transaction = manager.beginTransaction()
            val fragmento = PobrezaFragment()
            transaction.replace(R.id.est_layout, fragmento)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        sexo.setOnClickListener {
            val transaction = manager.beginTransaction()
            val fragmento = GeneroFragment()
            transaction.replace(R.id.est_layout, fragmento)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }


    fun Inicializar() {
        mayor = findViewById(R.id.est_mayores)
        pobre = findViewById(R.id.est_pobre)
        sexo = findViewById(R.id.est_sexo)
    }
}