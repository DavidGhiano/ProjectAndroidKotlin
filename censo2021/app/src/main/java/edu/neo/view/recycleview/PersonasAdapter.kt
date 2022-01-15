package edu.neo.view.recycleview

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import edu.neo.R
import edu.neo.model.Persona
import edu.neo.view.BuscarActivity
import edu.neo.view.InsertarActivity
import edu.neo.viewmodel.BuscarViewModel
import org.w3c.dom.Text

class PersonasAdapter(val lista: ArrayList<Persona>, val buscarVM: BuscarViewModel) :
    RecyclerView.Adapter<PersonasAdapter.ViewHolder>() {
    private lateinit var activity : BuscarActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonasAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.persona_layout, parent, false)
        activity = parent.context as BuscarActivity
        return ViewHolder(view)
    }

    @SuppressLint("RestrictedApi", "WrongConstant")
    override fun onBindViewHolder(holder: PersonasAdapter.ViewHolder, position: Int) {
        holder.id.text = lista[position].id.toString()
        holder.apellido_nom.text =lista[position].apellido.toUpperCase() + ", " + lista[position].nombre
        holder.tipoDoc.text = lista[position].tipoDoc
        holder.numDoc.text = lista[position].numDoc.toString()
        holder.fecNac.text = lista[position].fecNac
        holder.sexo.text = lista[position].sexo
        holder.direccion.text = lista[position].direccion
        holder.telefono.text = lista[position].telefono
        holder.ocupacion.text = lista[position].ocupacion
        holder.ingreso.text = "$" + lista[position].ingreso.toFloat().toString()
        holder.borrar.setOnClickListener {
            MaterialAlertDialogBuilder(it.context!!)
                .setTitle("Advertencia")
                .setMessage("¿seguro desea eliminar el censo de " + holder.apellido_nom.text + "?")
                .setNegativeButton("Cancelar") { dialog, which ->

                }
                .setPositiveButton("Aceptar") { dialog, which ->
                    if (!buscarVM.deletePersona(it.context, lista[position].id.toString())) {
                        Toast.makeText(it.context, "Hubo un error", Toast.LENGTH_SHORT).show()
                    } else {
                        lista.removeAt(position)
                        notifyItemRemoved(position)
                    }

                }
                .show()
        }
        holder.edit.setOnClickListener {
            val intent: Intent = Intent(it.context,InsertarActivity::class.java)
            intent.putExtra("id",lista[position].id.toString())
            it.context.startActivity(intent)
            activity.finishMe()

        }
    }


    override fun getItemCount(): Int {
        return lista.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: TextView
        var apellido_nom: TextView
        var tipoDoc: TextView
        var numDoc: TextView
        var fecNac: TextView
        var sexo: TextView
        var direccion: TextView
        var telefono: TextView
        var ocupacion: TextView
        var ingreso: TextView
        var edit: ImageButton

        var borrar: ImageButton
        init {
            id = view.findViewById(R.id.bus_id)
            apellido_nom = view.findViewById(R.id.bus_ape_nom)
            tipoDoc = view.findViewById(R.id.bus_tipo_doc)
            numDoc = view.findViewById(R.id.bus_num_doc)
            fecNac = view.findViewById(R.id.bus_fec_nac)
            sexo = view.findViewById(R.id.bus_sexo)
            direccion = view.findViewById(R.id.bus_direc)
            telefono = view.findViewById(R.id.bus_tel)
            ocupacion = view.findViewById(R.id.bus_ocup)
            ingreso = view.findViewById(R.id.bus_ingreso)
            edit = view.findViewById(R.id.bus_btn_edit)
            borrar = view.findViewById(R.id.bus_btn_borrar)
        }
    }
}