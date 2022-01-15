package edu.neo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.neo.R
import edu.neo.viewmodel.EstadisticaViewModel

class GeneroFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_est_sexo,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val estadiVM = ViewModelProvider(this).get(EstadisticaViewModel::class.java)

        val hombres:TextView = view.findViewById(R.id.frag_sex_man)
        val mujeres:TextView = view.findViewById(R.id.frag_sex_woman)
        val imgHombre:ImageView = view.findViewById(R.id.img_sex_man)
        val imgMujer:ImageView = view.findViewById(R.id.img_sex_woman)

        hombres.setText(estadiVM.getPersonasSexo(view.context,"Masculino"))
        mujeres.setText(estadiVM.getPersonasSexo(view.context,"Femenino"))
        imgHombre.setImageResource(R.mipmap.man)
        imgMujer.setImageResource(R.mipmap.woman)
    }
}