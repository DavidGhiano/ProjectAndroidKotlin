package edu.neo.veterinariaplus.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import edu.neo.veterinariaplus.model.Mascota
import edu.neo.veterinariaplus.model.Medico
import java.util.*
import kotlin.collections.ArrayList

class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "veterinariaplus.db"
        private val DATABASE_VERSION = 3
        val TABLE_NAME = "mascota"
        val COLUMN_ID = "id"
        val COLUMN_NOMBRE = "nombre"
        val COLUMN_TIPO = "tipo"
        val COLUMN_RAZA = "raza"
        val COLUMN_EDAD = "edad"
        val COLUMN_DOCTOR = "doctor"
        val COLUMN_FECHA = "fecha"
        val COLUMN_CAUSA_ATENCION = "causa_atencion"
        val COLUMN_ESTADO = "estado"

        val COLUMN_MEDICAMENTO = "medicamento"
        val COLUMN_RESULTADO = "resultado"
        val COLUMN_CAUSAS = "causas"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var createTabla =
            ("CREATE TABLE $TABLE_NAME ( $COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NOMBRE text, $COLUMN_TIPO text, $COLUMN_RAZA text, $COLUMN_EDAD text, $COLUMN_DOCTOR text, " +
                    "$COLUMN_CAUSA_ATENCION text, $COLUMN_FECHA text, $COLUMN_ESTADO text, $COLUMN_MEDICAMENTO text, $COLUMN_RESULTADO text, $COLUMN_CAUSAS text)")
        db?.execSQL(createTabla)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun registrarMascota(mascota: Mascota): Boolean {
        try {
            val db = this.writableDatabase
            val values = ContentValues()

            values.put(COLUMN_NOMBRE, mascota.nombre)
            values.put(COLUMN_TIPO, mascota.tipo)
            values.put(COLUMN_RAZA, mascota.raza)
            values.put(COLUMN_DOCTOR, mascota.doctor)
            values.put(COLUMN_EDAD, mascota.edad)
            values.put(COLUMN_CAUSA_ATENCION, mascota.causaAtencion)
            values.put(COLUMN_FECHA, mascota.fecha)
            values.put(COLUMN_ESTADO, mascota.estado)
            values.put(COLUMN_CAUSAS, mascota.causa)
            values.put(COLUMN_MEDICAMENTO, mascota.medicamento)
            values.put(COLUMN_RESULTADO, mascota.resultado)

            db.insert(TABLE_NAME, null, values)
            return true
        } catch (e: Exception) {
            Log.e("ERROR DATABASE: ", e.message.toString())
            return false
        }
    }


    fun contarCitas(fecha: String): Int {

        val db = this.readableDatabase
        val query = "SELECT count(*) FROM " + TABLE_NAME + " WHERE fecha = '" + fecha + "'"
        val cursor = db.rawQuery(query, null)
        return try {
            cursor.moveToFirst()
            val cantidad = cursor.getInt(cursor.getColumnIndex("count(*)"))
            cantidad
        } catch (e: Exception) {
            Log.e("Error a consultar", e.message.toString())
            0
        }
    }

    fun contarMedicoDia(fecha: String): ArrayList<Medico> {
        val db = this.readableDatabase
        val query =
            "SELECT doctor, count(doctor) FROM $TABLE_NAME WHERE fecha ='$fecha' GROUP BY doctor"
        val cursor = db.rawQuery(query, null)
        val listaMedicos: ArrayList<Medico> = ArrayList<Medico>()
        if (cursor.moveToFirst()) {
            do {
                val medico = cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR))
                val cantidadCitasHoy = cursor.getInt(cursor.getColumnIndex("count(doctor)"))
                listaMedicos.add(Medico(medico, cantidadCitasHoy))
            } while (cursor.moveToNext())
        }
        return listaMedicos
    }

    fun getAllDateToday(doctor: String, fecha: String): ArrayList<Mascota> {
        val db = this.readableDatabase
        val listamascota: ArrayList<Mascota> = ArrayList<Mascota>()
        val query = "SELECT * FROM $TABLE_NAME WHERE doctor='$doctor' AND fecha ='$fecha'"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val estado = cursor.getString(cursor.getColumnIndex(COLUMN_ESTADO))
                val tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                val raza = cursor.getString(cursor.getColumnIndex(COLUMN_RAZA))
                val edad = cursor.getString(cursor.getColumnIndex(COLUMN_EDAD))
                val causas = cursor.getString(cursor.getColumnIndex(COLUMN_CAUSA_ATENCION))
                listamascota.add(
                    Mascota(
                        id,
                        nombre,
                        tipo,
                        raza,
                        doctor,
                        edad,
                        fecha,
                        causas,
                        "",
                        "",
                        estado,
                        ""
                    )
                )

            } while (cursor.moveToNext())
        }
        return listamascota


    }

    fun getAllDateToDoc():ArrayList<Medico>{
        val db = this.readableDatabase
        val contador: ArrayList<Medico> = ArrayList<Medico>()

        val query = "SELECT $COLUMN_DOCTOR, count($COLUMN_DOCTOR) FROM $TABLE_NAME GROUP BY $COLUMN_DOCTOR"
        val cursor = db.rawQuery(query,null)
        if(cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR))
                val cantidad = cursor.getInt(cursor.getColumnIndex("count($COLUMN_DOCTOR)"))
                contador.add(Medico(nombre, cantidad))
            } while (cursor.moveToNext())
        }
        return contador
    }

    fun getAllMascotaDoc(medico:String):ArrayList<Mascota>{
        val db = this.readableDatabase
        val listado: ArrayList<Mascota> = ArrayList<Mascota>()

        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_DOCTOR = '$medico'"
        val cursor = db.rawQuery(query,null)
        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                val raza = cursor.getString(cursor.getColumnIndex(COLUMN_RAZA))
                val doctor = cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR))
                val edad = cursor.getString(cursor.getColumnIndex(COLUMN_EDAD))
                val fecha = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA))
                val causaAtencion = cursor.getString(cursor.getColumnIndex(COLUMN_CAUSA_ATENCION))
                val medicamento = cursor.getString(cursor.getColumnIndex(COLUMN_MEDICAMENTO))
                val resultado = cursor.getString(cursor.getColumnIndex(COLUMN_RESULTADO))
                val estado = cursor.getString(cursor.getColumnIndex(COLUMN_ESTADO))
                val causa = cursor.getString(cursor.getColumnIndex(COLUMN_CAUSAS))

                listado.add(Mascota(id,nombre,tipo,raza,doctor,edad,fecha,causaAtencion,medicamento,resultado,estado,causa))
            }while (cursor.moveToNext())
        }
        return listado
    }

    fun deleteDate(id:String):Boolean{
        val db = this.writableDatabase
        return try {
            val whereclause = "$COLUMN_ID=?"
            val whereargs = arrayOf(id)
            db.delete(TABLE_NAME,whereclause,whereargs)
            true
        } catch (e:Exception){
            Log.e("Error al eliminar en BD",e.message.toString())
            false
        }
    }
    fun updateDate(id:String, resultado:String, medicamento:String, causa:String, estado:String):Boolean{
        return try {
            val db = this.writableDatabase
            val valores = ContentValues()

            valores.put(COLUMN_RESULTADO, resultado)
            valores.put(COLUMN_MEDICAMENTO, medicamento)
            valores.put(COLUMN_CAUSAS, causa)
            valores.put(COLUMN_ESTADO, estado)

            val whereclause = "$COLUMN_ID=?"
            val wherearg = arrayOf(id)

            db.update(TABLE_NAME,valores,whereclause,wherearg)
            true
        }catch (e: Exception){
            Log.e("Error al modificar BD", e.message.toString())
            false
        }

    }
}