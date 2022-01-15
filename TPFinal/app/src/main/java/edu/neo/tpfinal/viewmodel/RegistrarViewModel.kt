package edu.neo.tpfinal.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import edu.neo.tpfinal.db.dbFireBase
import edu.neo.tpfinal.model.Persona
import java.lang.Exception

class RegistrarViewModel : ViewModel() {


    fun Registrar(persona: Persona,context: Context):Boolean{
        val dbFB = dbFireBase()
        dbFB.InicializarDB(context)
        return dbFB.InsertPersona(persona)
    }
}