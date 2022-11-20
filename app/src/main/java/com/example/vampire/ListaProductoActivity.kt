package com.example.vampire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.vampire.room_database.AdminProduct.Producto
import com.example.vampire.room_database.AdminProduct.ProductoAdacter
import com.example.vampire.room_database.AdminProduct.ProductoDatabase
import kotlinx.android.synthetic.main.activity_lista_producto.*

class ListaProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_producto)
     /*   val producto1= Producto(0,"tv",100.0F, "HDMI",R.drawable.ic_baseline_live_tv_24 )
        val producto2= Producto(0,"tv",100.0F, "HDMI",R.drawable.ic_baseline_live_tv_24 )
        val producto3= Producto(0,"tv",100.0F, "HDMI",R.drawable.ic_baseline_live_tv_24 )
        val listaProductos= listOf(producto1,producto2,producto3)*/

        var listaProductos= emptyList<Producto>()
        var database=ProductoDatabase.getDatabase(this)
        database.productos().getAll().observe(
            this, Observer { listaProductos=it
                val adater= ProductoAdacter(this,listaProductos)
                lista.adapter=adater
            }
        )



            lista.setOnItemClickListener() { parent, view, position, id ->
                val intent = Intent(this, ProductoActivity::class.java)
                intent.putExtra("producto",listaProductos[position])
                startActivity(intent)
            }
    }
}