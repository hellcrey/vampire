package com.example.vampire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar

enum class providerType{

}

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val fab: View= findViewById(R.id.fab)
        fab.setOnClickListener{View->
           // Snackbar.make(View, "power", Snackbar.LENGTH_LONG).show()
            val intent = Intent(this, ToDoActivity::class.java)
            startActivity(intent)

        }
    }
}