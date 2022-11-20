package com.example.vampire.room_database.AdminProduct

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.net.IDN

@Dao
interface productoDao {
    @Query("SELECT * FROM productos")
    fun getAll(): LiveData<List<Producto>>
    @Query("SELECT * FROM productos  WHERE idProducto=:id")
    fun get(id: Int): LiveData<Producto>
    @Insert
    fun insert(producto: Producto): Long
    @Update
    fun update(producto: Producto)
    @Delete
    fun delete(producto: Producto)
}