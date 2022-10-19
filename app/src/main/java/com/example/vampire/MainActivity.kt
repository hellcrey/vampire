package com.example.vampire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    //variables
    private var edtUsername: EditText?=null
    private var edtpassword: TextInputEditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtUsername = findViewById(R.id.edtUsername)
        edtpassword = findViewById(R.id.edtpassword)
    }

    fun onLogin(botonlogin: View)
    {
        val massegeusername=getString(R.string.messageusername)
        val massegepassword=getString(R.string.messagepassword)

        if (edtUsername!!.text.toString()=="juan@correo.com") {
            if (edtpassword!!.text.toString() == "12345") {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)

            } else {
                val dialog = AlertDialog.Builder(this)
                    .setTitle("ERROR")
                    .setMessage(massegepassword)
                    .create()
                    .show()
            }

        }  else {
            val dialog = AlertDialog.Builder(this)
                .setTitle("ERROR")
                .setMessage(massegeusername)
                .create()
                .show()
        }
    }
}