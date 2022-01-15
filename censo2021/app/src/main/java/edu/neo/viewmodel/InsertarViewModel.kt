package edu.neo.viewmodel

import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.ViewModel
import edu.neo.db.DBHelper
import edu.neo.model.Persona

class InsertarViewModel : ViewModel() {

    fun savePersona(
        id: Int?,
        tipoDoc: String,
        numDoc: Int,
        nombre: String,
        apellido: String,
        fecNac: String,
        sexo: String,
        direccion: String,
        telefono: String,
        ocupacion: String,
        ingreso: String,
        context: Context
    ): Boolean {
        val db: DBHelper = DBHelper(context, null)
        return db.savePersona(
            Persona(
                null,
                tipoDoc,
                numDoc,
                nombre,
                apellido,
                fecNac,
                sexo,
                direccion,
                telefono,
                ocupacion,
                ingreso
            )
        )
    }

    fun updatePersona(
        id:Int,
        tipoDoc: String,
        numDoc: Int,
        nombre: String,
        apellido: String,
        fecNac: String,
        sexo: String,
        direccion: String,
        telefono: String,
        ocupacion: String,
        ingreso: String,
        context: Context
    ): Boolean {
        val db: DBHelper = DBHelper(context, null)
        return db.updatePersona(
            Persona(
                id,
                tipoDoc,
                numDoc,
                nombre,
                apellido,
                fecNac,
                sexo,
                direccion,
                telefono,
                ocupacion,
                ingreso
            )
        )
    }

    fun getPersonaId(context: Context, id: String):Persona?{
        val db:DBHelper = DBHelper(context,null)
        return db.getPersonaId(id)
    }


}