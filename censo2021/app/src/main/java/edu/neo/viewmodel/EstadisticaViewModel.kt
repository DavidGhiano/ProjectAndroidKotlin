package edu.neo.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import edu.neo.db.DBHelper
import edu.neo.model.Persona

class EstadisticaViewModel : ViewModel() {

    fun getPersonasMayor(context:Context):ArrayList<Persona>{
        val db:DBHelper = DBHelper(context,null)
        return db.getPersonasMayor()
    }

    fun getPersonasPobre(context: Context):ArrayList<Persona>{
        val db:DBHelper = DBHelper(context,null)
        return db.getPersonasPobre()
    }

    fun getPersonasSexo(context:Context, genero:String):String{
        val db:DBHelper = DBHelper(context,null)
        return db.getPersonasSexo(genero).toString()
    }
}