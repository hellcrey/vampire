package com.example.vampire

import android.app.Activity
import android.content.Intent
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
import com.example.vampire.room_database.ToDoDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ToDoFragment: Fragment() {
    private  lateinit var listRecyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<MyTaskListAdapter.MyViewHolder>

    var myTaskTitles: ArrayList<String> = ArrayList()
    var myTaskTimes: ArrayList<String> = ArrayList()
    var myTaskPlaces: ArrayList<String> = ArrayList()

    var myTaskIds : ArrayList<String> = ArrayList()

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
        /*
 //comentario de las tareas por que se va hacer con el database
  var myTaskTitles: ArrayList<String> = ArrayList()
  var myTaskTimes: ArrayList<String> = ArrayList()
  var myTaskPlaces: ArrayList<String> = ArrayList()


  myTaskTitles.add("ir a super mercado")
  myTaskTitles.add("llevar el carro a mantenimiento")
  myTaskTitles.add("tarea 3")
  myTaskTitles.add("tarea 4")
  myTaskTitles.add("tarea 5")
  myTaskTitles.add("tarea 6")
  myTaskTitles.add("tarea 7")



  myTaskTimes.add("10:03 PM")
  myTaskTimes.add("12:00 M")
  myTaskTimes.add("10:03 PM")
  myTaskTimes.add("12:00 M")
  myTaskTimes.add("10:03 PM")
  myTaskTimes.add("12:00 M")
  myTaskTimes.add("10:03 PM")

  myTaskPlaces.add("superx")
  myTaskPlaces.add("tallerx")
  myTaskPlaces.add("superx")
  myTaskPlaces.add("tallerx")
  myTaskPlaces.add("superx")
  myTaskPlaces.add("tallerx")
  myTaskPlaces.add("superx")

        var info : Bundle=Bundle()
        info.putStringArrayList("titles", myTaskTitles)
        info.putStringArrayList("times", myTaskTimes)
        info.putStringArrayList("places", myTaskPlaces)
        listRecyclerView = requireView().findViewById(R.id.recyclerToDDoList)
        myAdapter = MyTaskListAdapter(activity as AppCompatActivity, info)
        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter = myAdapter
        listRecyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))

         */
        val fab : View = requireActivity().findViewById(R.id.fabHome)
        fab.setOnClickListener { view->
            val intent = Intent(activity,NewTaskActivity::class.java)
            var recursiveScope = 0

            startActivityForResult(intent,recursiveScope)
        }
        var info : Bundle=Bundle()
        info.putStringArrayList("titles", myTaskTitles)
        info.putStringArrayList("times", myTaskTimes)
        info.putStringArrayList("places", myTaskPlaces)
        info.putStringArrayList("ids", myTaskIds)

        listRecyclerView = requireView().findViewById(R.id.recyclerToDDoList)
        myAdapter = MyTaskListAdapter(activity as AppCompatActivity, info)
        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter = myAdapter
        listRecyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        updateList()
    }



    fun updateList(){
        val db = ToDoDatabase.getDatabase(requireActivity())
        val toDoDAD = db.todoDao()
 /*       runBlocking {
            launch {
                var result = toDoDAD.getAllTasks()
                var i=1
                myTaskTitles.clear()
                myTaskTimes.clear()
                myTaskPlaces.clear()
                myTaskIds.clear()

                while(i< result.size){
                    myTaskTitles.add(result[i].title.toString())
                    myTaskTimes.add(result[i].time.toString())
                    myTaskPlaces.add(result[i].place.toString())
                    myTaskIds.add(result[i].id.toString())
                    i++
                }
                myAdapter.notifyDataSetChanged()
            }
        }*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==0){
            if (resultCode==Activity.RESULT_OK){
                updateList()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}