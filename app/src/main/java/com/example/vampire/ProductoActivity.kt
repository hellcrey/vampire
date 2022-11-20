package com.example.vampire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vampire.room_database.AdminProduct.Producto
import kotlinx.android.synthetic.main.activity_producto.*

class ProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)
        val producto = intent.getSerializableExtra("producto") as Producto
        textViewNombreAP.text= producto.nombre
        textViewPrecioAP.text="${producto.precio}"
        textViewDetalleeAP.text=producto.descripcion
        imageViewPerdilAP.setImageResource(producto.imagen)
    }
}