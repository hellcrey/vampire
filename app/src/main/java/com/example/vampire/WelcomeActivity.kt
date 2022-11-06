package com.example.vampire

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

enum class providerType{
    BASIC
}

class WelcomeActivity : AppCompatActivity() {
    private var textemail : TextView? = null
    private var textprovedor : TextView? = null
    private var btnLogOut : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        textemail = findViewById(R.id.textemail)
        textprovedor = findViewById(R.id.textprovedor)
        btnLogOut = findViewById(R.id.btnLogOut)

        val bundle= intent.extras
        val email= bundle?.getString("email")
        val provider = bundle?.getString("provider")
        textemail!!.text=email
        textprovedor!!.text = provider
        val prefs = getSharedPreferences(
            getString(R.string.prefs_file),Context.MODE_PRIVATE)
            .edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()


        btnLogOut?.setOnClickListener {
            val prefs = getSharedPreferences(
                getString(R.string.prefs_file),Context.MODE_PRIVATE)
                .edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
           // onBackPressed()
        }

        //setSupportActionBar(findViewById(R.id.my_toolbar)) /* no se encuentra en el video */


        val fab: View= findViewById(R.id.fab)
        fab.setOnClickListener{View->
           // Snackbar.make(View, "power", Snackbar.LENGTH_LONG).show()
            val intent = Intent(this, ToDoActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val homeIntent = Intent(this, WelcomeActivity::class.java)
            .apply {
                putExtra("email",textemail!!.text.toString())
                putExtra("provider", textprovedor!!.text.toString())
            }
        startActivity(homeIntent)
    }
}