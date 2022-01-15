package edu.neo.tpfinal.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.neo.tpfinal.db.dbFireBase
import edu.neo.tpfinal.model.Dieta
import edu.neo.tpfinal.model.Persona

class AgregarViewModel : ViewModel() {
    fun Registrar(dieta: Dieta, userID:String, context: Context):Boolean{
        val dbFB = dbFireBase()
        dbFB.InicializarDB(context)
        return dbFB.InsertDieta(dieta,userID)
    }

}