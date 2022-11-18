package com.example.vampire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vampire.room_database.AdminProduct.Producto
import com.example.vampire.room_database.AdminProduct.ProductoAdacter
import kotlinx.android.synthetic.main.activity_lista_producto.*

class ListaProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_producto)
        val producto1= Producto("tv",100.0F, "HDMI",R.drawable.ic_baseline_live_tv_24 )
        val producto2= Producto("tv",100.0F, "HDMI",R.drawable.ic_baseline_live_tv_24 )
        val producto3= Producto("tv",100.0F, "HDMI",R.drawable.ic_baseline_live_tv_24 )
        val listaProductos= listOf(producto1,producto2,producto3)
        val adater= ProductoAdacter(this,listaProductos)
            lista.adapter=adater
    }
}