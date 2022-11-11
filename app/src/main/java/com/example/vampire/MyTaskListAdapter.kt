package com.example.vampire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MyTaskListAdapter(context : AppCompatActivity,
                        val info:Bundle ):RecyclerView.Adapter<MyTaskListAdapter.MyViewHolder>()
{
                        class  MyViewHolder(val layout: View):RecyclerView.ViewHolder(layout)
    private var context :AppCompatActivity=context
    var myTAskTitles:ArrayList<String> = info.getStringArrayList("titles") as ArrayList<String>
    var myTAskPlace:ArrayList<String> = info.getStringArrayList("places") as ArrayList<String>
    var myTAskTime:ArrayList<String> = info.getStringArrayList("times") as ArrayList<String>
    var myTaskIds: ArrayList<String> = info.getStringArrayList("ids") as ArrayList<String>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent,false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var  textViewTask = holder.layout.findViewById<TextView>(R.id.textViewTask)
        textViewTask.text=myTAskTitles[position]

        var textViewTime=holder.layout.findViewById<TextView>(R.id.textViewTaskTime)
        textViewTime.text=myTAskTime[position]



        holder.layout.setOnClickListener {
            Toast.makeText(holder.itemView.context,textViewTask.text, Toast.LENGTH_LONG).show()
            val datos= Bundle()
            datos.putString("tarea",textViewTask.text as String)
            datos.putString("hora",textViewTime.text as String)
            datos.putString("lugar",myTAskPlace[position])
            datos.putString("id", myTaskIds[position])

            context.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.fcv,DetailFragment::class.java,datos,"detail")
                ?.addToBackStack("")
                ?.commit()
        }
    }

    override fun getItemCount(): Int {
        return myTAskTitles.size
    }

}