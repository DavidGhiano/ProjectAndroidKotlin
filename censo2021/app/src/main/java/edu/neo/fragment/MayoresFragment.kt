package edu.neo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.neo.R
import edu.neo.view.recycleview.MayoresAdapter
import edu.neo.viewmodel.EstadisticaViewModel

class MayoresFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_est_mayores,container,false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val estadiVM = ViewModelProvider(this).get(EstadisticaViewModel::class.java)

        val rv_mayores:RecyclerView = view.findViewById(R.id.rv_est_mayores)

        rv_mayores.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL,false)
        rv_mayores.adapter = MayoresAdapter(estadiVM.getPersonasMayor(view.context))


    }
}