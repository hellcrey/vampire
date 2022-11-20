package com.example.vampire.room_database.AdminProduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.vampire.R
import kotlinx.android.synthetic.main.item_producto.view.*

class ProductoAdacter (private val mContext: Context, private val listaProductos: List< Producto>):
ArrayAdapter<Producto>(mContext, 0, listaProductos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext)
            .inflate(R.layout.item_producto, parent,false)
        val producto = listaProductos[position]
        layout.textViewNombreIP.text=producto.nombre
        layout.textViewPrecioIP.text="${producto.precio}"
        layout.imageViewProductoIP.setImageResource(producto.imagen)
        return layout
    }


}