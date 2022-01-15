package edu.neo.veterinariaplus.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import edu.neo.veterinariaplus.dao.DbHelper
import edu.neo.veterinariaplus.model.Mascota
import java.time.LocalDate

class AtencionViewModel: ViewModel() {
    fun getAllDateToday(context: Context, doctor:String):ArrayList<Mascota>{
        val db:DbHelper = DbHelper(context,null)

        return db.getAllDateToday(doctor,getFecha())
    }



    fun getFecha():String{
        return LocalDate.now().toString().replace("-", "")
    }
}