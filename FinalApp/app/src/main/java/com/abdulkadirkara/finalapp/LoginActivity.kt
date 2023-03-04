package com.abdulkadirkara.finalapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.core.widget.addTextChangedListener

val dosyayolu="com.abdulkadirkara.finalapp"
var anahtarisim = "isim"
var anahtarsifre = "sifre"
var anahtarunutma="unutma"


class LoginActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edittextisim=findViewById<EditText>(R.id.editTextisim)
        val edittextsifre=findViewById<EditText>(R.id.editTextsifre)
        val switchbtn=findViewById<Switch>(R.id.switch1)
        val progress = findViewById<ProgressBar>(R.id.progressBar2)

        val preferences = getSharedPreferences(dosyayolu, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        val name = "Abdulkadir Kara"
        val sifre = "02200201002"
        var ischeckname = false
        var ischecksifre=false
        Toast.makeText(applicationContext,
            "Kaydedilmiş \n " +
                    "isim : ${preferences.getString(anahtarisim, "Değer Yok")} \n " +
                    "sifre : ${preferences.getString(anahtarsifre,"Değer Yok")}\n " +
                    "Unutma : ${preferences.getBoolean(anahtarunutma,false)}", Toast.LENGTH_SHORT).show()
        val kontrolname=preferences.getString(anahtarisim,"")
        val sifrekontrol=preferences.getString(anahtarsifre,"")
        if (kontrolname==name && sifrekontrol==sifre){
            edittextisim.setText(name)
            edittextsifre.setText(sifre)
            switchbtn.isChecked=true
            Handler().postDelayed({
                val intent=Intent(this@LoginActivity,MenuActivity::class.java)
                startActivity(intent)
                finish()
            },500)

        }

        switchbtn.setOnCheckedChangeListener { _, b ->
            if (b){
                editor.putString(anahtarisim,name)
                editor.putString(anahtarsifre,sifre)
                editor.putBoolean(anahtarunutma,true)
                editor.apply()
            }
        }
        edittextisim.addTextChangedListener {
            if (edittextisim.text.toString() == name) {
                ischeckname = true
                if (ischecksifre) {
                    progress.visibility = View.VISIBLE
                    Handler().postDelayed({
                        val intent=Intent(this@LoginActivity,MenuActivity::class.java)
                        startActivity(intent)
                        finish()
                    },500)
                }
            }
        }
        edittextsifre.addTextChangedListener {
            if (edittextsifre.text.toString() == sifre) {
                ischecksifre = true
                if (ischeckname) {
                    progress.visibility = View.VISIBLE
                    Handler().postDelayed({
                        val intent=Intent(this@LoginActivity,MenuActivity::class.java)
                        startActivity(intent)
                        finish()
                    },500)

                }
            }
        }

    }
}