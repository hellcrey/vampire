package com.example.vampire

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vampire.room_database.AdminProduct.Producto
import com.example.vampire.room_database.AdminProduct.ProductoDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_nuevo_producto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NuevoProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_producto)
        val database = ProductoDatabase.getDatabase(this)
        val dbFirebase= FirebaseFirestore.getInstance()
        buttonANP.setOnClickListener {
            val nombre = editTextNombreANP.text.toString()
            val precio = editTextPrecioANP.text.toString().toFloat()
            val descripcion = editTextDescripcionANP.text.toString()
            val producto = Producto(0,nombre,precio,descripcion,R.drawable.ic_baseline_live_tv_24)
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
                setResult(Activity.RESULT_OK)
                finish()
                this@NuevoProductoActivity.finish()
            }
        }
    }
}