package com.example.vampire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ToDoFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmento= inflater.inflate(R.layout.fragment_to_do, container,false)
        val btnDetail1:Button= fragmento.findViewById(R.id.btn_detail_1)
        val btnDetail2:Button= fragmento.findViewById(R.id.btn_detail_2)
        val btnDetail3:Button= fragmento.findViewById(R.id.btn_detail_3)



        btnDetail1.setOnClickListener(View.OnClickListener {
            val datos= Bundle()
            datos.putString("tarea","Ir al super mercado")
            datos.putString("hora","8:37am")
            datos.putString("lugar", "supermistic")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fcv,DetailFragment::class.java,datos,"detail1")
                ?.addToBackStack("")
                ?.commit()
        })



        return fragmento
    }
}