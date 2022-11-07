package com.example.vampire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private var edName:EditText?=null
    private var edNit:EditText?=null
    private var edEmail:EditText?=null
    private var edPassword:EditText?=null
    private var chbPolicies:CheckBox?=null
    private val text_Pattern: Pattern= Pattern.compile(
        "[a-zA-Z]*"
    )
    private val password_pattern:Pattern= Pattern.compile(
        "^"+
                "(?=.*[0-9])"+
                "(?=.*[a-z])"+
                ".{8,}"+
                "$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edEmail=findViewById(R.id.edEmail)
        edName=findViewById(R.id.edName)
        edNit=findViewById(R.id.edNit)
        edPassword=findViewById(R.id.edpasswordr)
        chbPolicies=findViewById(R.id.chb_pilicies)
    }



    fun OnRegister(view: View) {
        if (ValidateForm()){
            Toast.makeText(this,"correcto", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
        }
    }
    private fun ValidateForm():Boolean{
        var validate=true
        val nameInput=edName!!.text.toString()

        val nitInput=edNit!!.text.toString()
        val passwordInput=edPassword!!.text.toString()

        val emailInput=edEmail!!.text.toString()
        if(!chbPolicies!!.isChecked){
            validate=false
        }
        if (TextUtils.isEmpty(edName!!.text.toString()))
        {
            edName!!.error="Requerido"
            validate=false
        }else if(!text_Pattern.matcher(nameInput.replace(" ","")).matches()){
            edName!!.error="Nombre no Valido"
            validate=false
        }else edName!!.error=null

        if(TextUtils.isEmpty(edPassword!!.text.toString())){
            edPassword!!.error="requerido"
            validate=false
        }else if (!password_pattern.matcher(passwordInput).matches()){

            edPassword!!.error="no cumple las politicas"

        }else edPassword!!.error=null

        return validate
    }
}