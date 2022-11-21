package com.example.vampire

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vampire.room_database.AdminProduct.ImagenController
import com.example.vampire.room_database.AdminProduct.Producto
import com.example.vampire.room_database.AdminProduct.ProductoDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_nuevo_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NuevoProductoActivity : AppCompatActivity() {

    private val SELECT_ACTIVITY=50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_producto)
        val database = ProductoDatabase.getDatabase(this)
        val dbFirebase= FirebaseFirestore.getInstance()
        var idproducto: Int=0
        if (intent.hasExtra("producto")){
            val producto = intent.extras?.getSerializable("producto") as Producto
            editTextNombreANP.setText(producto.nombre)
            editTextPrecioANP.setText(producto.precio.toString())
            editTextDescripcionANP.setText(producto.descripcion)
            idproducto=producto.idProducto
        }
        buttonANP.setOnClickListener {
            val nombre = editTextNombreANP.text.toString()
            val precio = editTextPrecioANP.text.toString().toFloat()
            val descripcion = editTextDescripcionANP.text.toString()
            val producto = Producto(idproducto,nombre,precio,descripcion,R.drawable.ic_baseline_live_tv_24)

          if (idproducto==0) {

              CoroutineScope(Dispatchers.IO).launch {
                  var result = database.productos().insert(producto)
                  dbFirebase.collection("Productos").document(result.toString())
                      .set(
                          hashMapOf(
                              "nombre" to nombre,
                              "precio" to precio,
                              "descripcion" to descripcion
                          )
                      )
//                  setResult(Activity.RESULT_OK)
//                  finish()
                  this@NuevoProductoActivity.finish()
              }
          }else{
              CoroutineScope(Dispatchers.IO).launch {
                  database.productos().update(producto)
                  dbFirebase.collection("Productos").document(idproducto.toString())
                      .set(
                          hashMapOf(
                              "nombre" to nombre,
                              "precio" to precio,
                              "descripcion" to descripcion
                          )
                      )
                  this@NuevoProductoActivity.finish()
              }
              var principal= Intent(this,ListaProductoActivity::class.java)
              startActivity(principal)
          }
        }
        imageViewSelectANP.setOnClickListener {
            ImagenController.selectPhoneFromGallery(this, SELECT_ACTIVITY)
        }
    }

}