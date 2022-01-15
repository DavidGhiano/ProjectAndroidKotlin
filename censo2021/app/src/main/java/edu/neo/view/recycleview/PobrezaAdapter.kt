package edu.neo.view.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.neo.R
import edu.neo.model.Persona

class PobrezaAdapter(val items:ArrayList<Persona>) : RecyclerView.Adapter<PobrezaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PobrezaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pobre_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PobrezaAdapter.ViewHolder, position: Int) {
        holder.nombre.text = items[position].apellido.toUpperCase() + ", " + items[position].nombre
        holder.dni.text = items[position].numDoc.toString()
        holder.ingreso.text = "$" + items[position].ingreso.toFloat().toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nombre:TextView
        val dni:TextView
        val ingreso:TextView
        init {
            nombre = view.findViewById(R.id.rv_est_pobre_nombre)
            dni = view.findViewById(R.id.rv_est_pobre_dni)
            ingreso = view.findViewById(R.id.rv_est_pobre_ingreso)
        }
    }

}