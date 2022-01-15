package edu.neo.veterinariaplus.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import edu.neo.veterinariaplus.dao.DbHelper

class ModificarMascotaViewModel: ViewModel() {

    fun deleteDate(context: Context, id:String):Boolean{
        val db: DbHelper = DbHelper(context,null)
        return db.deleteDate(id)
    }

    fun updateDate(context: Context, id: String,resultado:String, medicamentos:String,causa:String):Boolean{
        val db:DbHelper = DbHelper(context,null)
        return db.updateDate(id,resultado,medicamentos,causa,"FINALIZADO")
    }
}