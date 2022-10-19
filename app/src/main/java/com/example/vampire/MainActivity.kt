package com.example.vampire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
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

    fun onLogin(botonlogin: View) {
        if (edtUsername!!.text.toString()=="juan@correo.com" &&
            edtpassword!!.text.toString()=="12345" ){
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)

        }
    }
}