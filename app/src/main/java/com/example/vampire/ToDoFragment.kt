package com.example.vampire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class ToDoFragment: Fragment() {
    private  lateinit var listRecyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<MyTaskListAdapter.MyViewHolder>
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
 /*       val btnDetail1:Button= fragmento.findViewById(R.id.btn_detail_1)
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
        })*/



        return fragmento
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var myTaskTitles: ArrayList<String> = ArrayList()
        var myTaskTimes: ArrayList<String> = ArrayList()
        var myTaskPlaces: ArrayList<String> = ArrayList()
        myTaskTitles.add("ir a super mercado")
        myTaskTitles.add("llevar el carro a mantenimiento")
        myTaskTimes.add("10:03 PM")
        myTaskTimes.add("12:00 M")
        myTaskPlaces.add("superx")
        myTaskPlaces.add("tallerx")
        var info : Bundle=Bundle()
        info.putStringArrayList("titles", myTaskTitles)
        info.putStringArrayList("times", myTaskTimes)
        info.putStringArrayList("places", myTaskPlaces)
        listRecyclerView = requireView().findViewById(R.id.recyclerToDDoList)
        myAdapter = MyTaskListAdapter(activity as AppCompatActivity, info)
        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter = myAdapter
        listRecyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
    }

}