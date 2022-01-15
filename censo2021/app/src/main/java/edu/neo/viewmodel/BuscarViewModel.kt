package edu.neo.viewmodel

import android.app.Person
import android.content.Context
import androidx.lifecycle.ViewModel
import edu.neo.db.DBHelper
import edu.neo.model.Persona

class BuscarViewModel: ViewModel() {

    fun getAllPersonas(context: Context):ArrayList<Persona>{
        val db:DBHelper = DBHelper(context,null)
        return db.getAllPersonas()
    }

    fun getAllPersonasxDNI(context: Context, dni: String):ArrayList<Persona>{
        val db:DBHelper = DBHelper(context,null)
        return db.getPersonaDni(dni)
    }

    fun deletePersona(context: Context,id:String):Boolean{
        val db:DBHelper = DBHelper(context,null)
        return db.eliminarPersona(id)
    }


}