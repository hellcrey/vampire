package com.example.vampire

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.security.Provider

class MainActivity : AppCompatActivity() {
    //variables
    private val GOOGLE_SING_IN=100
    private  var btnGoogle : SignInButton? = null
   private var edtUsername: EditText?=null
    private var edtpassword: EditText?=null
    private var authLayout: LinearLayout?=null
    private var tvForgotPassword: TextView?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        edtUsername = findViewById(R.id.edtUsername)
        edtpassword = findViewById(R.id.edtpassword)
        authLayout = findViewById(R.id.authLayout)
        btnGoogle = findViewById(R.id.btnGoogle)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        session()
        loginGoogle()
        tvForgotPassword!!.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun onLogin(botonlogin: View)
    {
        val massegeusername=getString(R.string.messageusername)
        val massegepassword=getString(R.string.messagepassword)
        var username : String = edtUsername!!.text.toString()
        //var password : String = edtpassword!!.text.toString()
        if (username == "juan@correo.com")
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
                                                .setMessage("User: "+ username) //por cambiar la variable
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

    fun OnRegisterEmail(view: View) {
        title = "Autentificacion"
        if (edtUsername!!.text.isNotEmpty() && edtpassword!!.text.isNotEmpty()) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                edtUsername!!.text.toString() ,
                edtpassword!!.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "correcto", Toast.LENGTH_LONG).show()
                    showHome(it.result?.user?.email?:"",providerType.BASIC)
                } else {
                    showAlert()
                }
            }
        }
    }
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("error al autenticar")
        builder.setPositiveButton("Acepta", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email : String, provider : providerType ){
        val homeIntent = Intent(this,WelcomeActivity::class.java)
            .apply {
                putExtra("email",email)
                putExtra("provider", provider.name)

            }
        startActivity(homeIntent)
    }

    fun OnLoginEmail(view: View) {
        title = "Autentificacion"
        if (edtUsername!!.text.isNotEmpty() && edtpassword!!.text.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                edtUsername!!.text.toString() ,
                edtpassword!!.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    //Toast.makeText(this, "correcto", Toast.LENGTH_LONG).show()
                    showHome(it.result?.user?.email?:"",providerType.BASIC)
                } else {
                    showAlert()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        authLayout!!.visibility = View.VISIBLE
    }

    private fun session(){
        val prefs : SharedPreferences= getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )
        val email: String? =prefs.getString("email", null)
        val provider :String? = prefs.getString("provider", null)
        if (email!=null && provider!= null){
            authLayout!!.visibility= View.INVISIBLE
            showHome(email, providerType.valueOf(provider))

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()
        FirebaseAuth.getInstance().signOut()
        onBackPressed()
    }

    fun loginGoogle(){
        btnGoogle!!.setOnClickListener{
            val googleleConf : GoogleSignInOptions = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build()
            val googleClient : GoogleSignInClient = GoogleSignIn.getClient(
                this,googleleConf
            )
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent,GOOGLE_SING_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==GOOGLE_SING_IN){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account : GoogleSignInAccount? = task.getResult(ApiException::class.java)
                if (account != null){
                   val credential : AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(account.email ?:"",providerType.GOOGLE)
                        }else{
                            showAlert()
                        }
                    }
                }
            }catch (e: ApiException){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("error")
                builder.setMessage(e.toString())
                builder.setPositiveButton("Aceptar", null)
                val dialog : AlertDialog = builder.create()
                    dialog.show()
            }
        }
    }
}