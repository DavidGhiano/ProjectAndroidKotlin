package edu.neo.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import edu.neo.model.Persona
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "censo.db"
        private val DATABASE_VERSION = 1

        val TABLE_NAME = "personas"
        val COLUMN_ID = "id"
        val COLUMN_TIPO = "tipo_doc"
        val COLUMN_DNI = "nro_doc"
        val COLUMN_NOMBRE = "nombre"
        val COLUMN_APELLIDO = "apellido"
        val COLUMN_FECNAC = "fecnac"
        val COLUMN_SEXO = "sexo"
        val COLUMN_DIRECCION = "direccion"
        val COLUMN_TELEFONO = "telefono"
        val COLUMN_OCUPACION = "ocupacion"
        val COLUMN_INGRESO = "ingreso"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var createTable = ("CREATE TABLE $TABLE_NAME ( " +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_TIPO TEXT, " +
                "$COLUMN_DNI INTEGER, " +
                "$COLUMN_NOMBRE TEXT, " +
                "$COLUMN_APELLIDO TEXT, " +
                "$COLUMN_FECNAC TEXT, " +
                "$COLUMN_SEXO TEXT, " +
                "$COLUMN_DIRECCION TEXT, " +
                "$COLUMN_TELEFONO TEXT, " +
                "$COLUMN_OCUPACION TEXT, " +
                "$COLUMN_INGRESO TEXT )")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    //CRUD
    fun savePersona(persona: Persona): Boolean {
        try {
            val db = this.writableDatabase
            val values = ContentValues()

            values.put(COLUMN_TIPO, persona.tipoDoc)
            values.put(COLUMN_DNI, persona.numDoc)
            values.put(COLUMN_NOMBRE, persona.nombre)
            values.put(COLUMN_APELLIDO, persona.apellido)
            values.put(COLUMN_FECNAC, persona.fecNac)
            values.put(COLUMN_SEXO, persona.sexo)
            values.put(COLUMN_DIRECCION, persona.direccion)
            values.put(COLUMN_TELEFONO, persona.telefono)
            values.put(COLUMN_OCUPACION, persona.ocupacion)
            values.put(COLUMN_INGRESO, persona.ingreso)

            db.insert(TABLE_NAME, null, values)
            return true
        } catch (e: Exception) {
            Log.e("ERROR DB:", "savePersona: " + e.message.toString())
            return false
        }
    }

    fun updatePersona(persona: Persona): Boolean {
        return try {
            val db = this.writableDatabase
            val values = ContentValues()

            values.put(COLUMN_TIPO, persona.tipoDoc)
            values.put(COLUMN_DNI, persona.numDoc)
            values.put(COLUMN_NOMBRE, persona.nombre)
            values.put(COLUMN_APELLIDO, persona.apellido)
            values.put(COLUMN_FECNAC, persona.fecNac)
            values.put(COLUMN_SEXO, persona.sexo)
            values.put(COLUMN_DIRECCION, persona.direccion)
            values.put(COLUMN_TELEFONO, persona.telefono)
            values.put(COLUMN_OCUPACION, persona.ocupacion)
            values.put(COLUMN_INGRESO, persona.ingreso)

            val whereclause = "$COLUMN_ID = ?"
            val whereargs = arrayOf(persona.id.toString())

            db.update(TABLE_NAME, values, whereclause, whereargs)
            true
        } catch (e: Exception) {
            Log.e("ERROR en DB:", e.message.toString())
            false
        }
    }

    fun eliminarPersona(id: String): Boolean {
        val db = this.writableDatabase
        return try {
            val whereclause = "$COLUMN_ID = ?"
            val wherearg = arrayOf(id)

            db.delete(TABLE_NAME, whereclause, wherearg)

            true
        } catch (e: Exception) {
            Log.e("ERROR en DB", e.message.toString())
            false
        }
    }


    //CONSULTAS
    fun getAllPersonas(): ArrayList<Persona> {
        val db = this.readableDatabase
        val listaPersonas: ArrayList<Persona> = ArrayList<Persona>()
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_APELLIDO ASC"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val tipoDoc = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                val numDoc = cursor.getInt(cursor.getColumnIndex(COLUMN_DNI))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
                val fecNac = cursor.getString(cursor.getColumnIndex(COLUMN_FECNAC))
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                val ingreso = cursor.getString(cursor.getColumnIndex(COLUMN_INGRESO))
                listaPersonas.add(
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
            } while (cursor.moveToNext())
        }
        return listaPersonas
    }

    fun getPersonaDni(dni: String): ArrayList<Persona> {
        val db = this.readableDatabase
        val listaPersonas: ArrayList<Persona> = ArrayList<Persona>()
        val query =
            "SELECT * FROM $TABLE_NAME WHERE nro_doc LIKE '%$dni%' ORDER BY $COLUMN_APELLIDO ASC"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val tipoDoc = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                val numDoc = cursor.getInt(cursor.getColumnIndex(COLUMN_DNI))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
                val fecNac = cursor.getString(cursor.getColumnIndex(COLUMN_FECNAC))
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                val ingreso = cursor.getString(cursor.getColumnIndex(COLUMN_INGRESO))
                listaPersonas.add(
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
            } while (cursor.moveToNext())
        }
        return listaPersonas
    }

    fun getPersonaId(id: String): Persona? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE id = $id"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val persona: Persona
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            val tipoDoc = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
            val numDoc = cursor.getInt(cursor.getColumnIndex(COLUMN_DNI))
            val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
            val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
            val fecNac = cursor.getString(cursor.getColumnIndex(COLUMN_FECNAC))
            val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
            val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
            val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
            val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
            val ingreso = cursor.getString(cursor.getColumnIndex(COLUMN_INGRESO))
            persona = Persona(
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
            return persona
        }
        return null
    }

    fun getPersonasMayor(): ArrayList<Persona> {
        val db = this.readableDatabase
        val listaPersonas: ArrayList<Persona> = ArrayList<Persona>()
        val query =
            "SELECT * FROM $TABLE_NAME WHERE $COLUMN_OCUPACION = 'Desocupado' ORDER BY $COLUMN_APELLIDO ASC"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                var fecha = cursor.getString(cursor.getColumnIndex(COLUMN_FECNAC))
                if (MayorEdad(fecha)) {
                    val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                    val tipoDoc = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                    val numDoc = cursor.getInt(cursor.getColumnIndex(COLUMN_DNI))
                    val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                    val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
                    val fecNac = cursor.getString(cursor.getColumnIndex(COLUMN_FECNAC))
                    val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                    val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                    val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                    val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                    val ingreso = cursor.getString(cursor.getColumnIndex(COLUMN_INGRESO))
                    listaPersonas.add(
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
            } while (cursor.moveToNext())
        }
        return listaPersonas
    }

    fun getPersonasPobre(): ArrayList<Persona> {
        val db = this.readableDatabase
        val listaPersonas: ArrayList<Persona> = ArrayList<Persona>()
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_APELLIDO ASC"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                if ((cursor.getString(cursor.getColumnIndex(COLUMN_INGRESO)).toFloat()) < 5000f) {
                    val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                    val tipoDoc = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                    val numDoc = cursor.getInt(cursor.getColumnIndex(COLUMN_DNI))
                    val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                    val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
                    val fecNac = cursor.getString(cursor.getColumnIndex(COLUMN_FECNAC))
                    val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                    val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                    val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                    val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                    val ingreso = cursor.getString(cursor.getColumnIndex(COLUMN_INGRESO))
                    listaPersonas.add(
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
            } while (cursor.moveToNext())
        }
        return listaPersonas
    }

    fun getPersonasSexo(sexo: String): Int {
        val db = this.readableDatabase
        var cuenta: Int = 0
        val query = "SELECT COUNT() as 'cantidad' FROM $TABLE_NAME WHERE $COLUMN_SEXO = '$sexo'"

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) cuenta = cursor.getInt(cursor.getColumnIndex("cantidad"))

        return cuenta
    }

    //FUNCIONES
    private fun MayorEdad(fecha: String): Boolean {
        val miFecha = SimpleDateFormat("dd/MM/yyyy").parse(fecha)
        val calendar: Calendar = Calendar.getInstance()
        val calendarToday: Calendar = Calendar.getInstance()
        calendar.time = miFecha

        val anio: Int = calendar.get(Calendar.YEAR)
        val mes: Int = calendar.get(Calendar.MONTH)
        val dia: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val anioNow = calendarToday.get(Calendar.YEAR)
        val mesNow = calendarToday.get(Calendar.MONTH)
        val diaNow = calendarToday.get(Calendar.DAY_OF_MONTH)

        if ((anioNow - 18) == anio) {
            if (mesNow == mes) {
                return diaNow >= dia
            }
        } else return (anioNow - 18) > anio

        return false
    }
}