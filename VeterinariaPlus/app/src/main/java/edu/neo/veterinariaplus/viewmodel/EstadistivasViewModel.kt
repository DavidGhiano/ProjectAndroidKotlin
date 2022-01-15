package edu.neo.veterinariaplus.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import edu.neo.veterinariaplus.dao.DbHelper
import edu.neo.veterinariaplus.model.Mascota
import edu.neo.veterinariaplus.model.Medico

class EstadistivasViewModel:ViewModel() {

    fun getAllDateToDoc(context: Context):ArrayList<Medico>{
        val db:DbHelper = DbHelper(context,null)
        return db.getAllDateToDoc()
    }

    fun getAllMascotaDoc(context: Context,doctor:String):ArrayList<Mascota>{
        val db:DbHelper = DbHelper(context,null)
        return db.getAllMascotaDoc(doctor)
    }
}