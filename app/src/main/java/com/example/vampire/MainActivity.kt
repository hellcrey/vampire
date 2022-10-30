package com.example.vampire

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    //variables
    private var edtUsername: EditText?=null
    private var edtpassword: TextInputEditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        edtUsername = findViewById(R.id.edtUsername)
        edtpassword = findViewById(R.id.edtpassword)
    }

    fun onLogin(botonlogin: View)
    {
        val massegeusername=getString(R.string.messageusername)
        val massegepassword=getString(R.string.messagepassword)
        var username: String=edtUsername!!.text.toString()
        if (username=="juan@correo.com")
        {
            if (edtpassword!!.text.toString() == "12345")
            {
                val negativeButton={_:DialogInterface,_:Int->}
                val positiveButton={dialog:DialogInterface,which:Int->
                    val intent = Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                }


                val dialog= AlertDialog.Builder(this)
                                                .setTitle("welcome")
                                                .setMessage("User: "+ username)
                                                .setPositiveButton("ok", positiveButton)
                                                .setNegativeButton("Cancelar", negativeButton)
                                                .create()
                                                .show()

            } else
            {

                val dialog = AlertDialog.Builder(this)
                    .setTitle("ERROR")
                    .setMessage(massegepassword)
                    .create()
                    .show()


            }

        }  else
        {
            /*
            val dialog = AlertDialog.Builder(this)
                .setTitle("ERROR")
                .setMessage(massegeusername)
                .create()
                .show()
            */
            Toast.makeText(this, massegeusername, Toast.LENGTH_LONG).show()
        }

    }

    fun OnRegister(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}