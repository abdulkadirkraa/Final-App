package com.abdulkadirkara.finalapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var menubtn=findViewById<Button>(R.id.buttun)
        menubtn.setOnClickListener {
            var acilirmenu=PopupMenu(this@MenuActivity,menubtn)
            acilirmenu.menuInflater.inflate(R.menu.menu,acilirmenu.menu)
            acilirmenu.setOnMenuItemClickListener {i->
                when(i.itemId){
                    R.id.rgb->{
                        fragmentcagir(rgb())
                        true
                    }
                    R.id.snackbar->{
                        fragmentcagir(snackBar())
                        true
                    }
                    R.id.cikis->{
                        val tasarim=layoutInflater.inflate(R.layout.cikisaalert,null)
                        val isim=tasarim.findViewById<EditText>(R.id.isim)
                        val sifre=tasarim.findViewById<EditText>(R.id.sifre)
                        val progressexit=tasarim.findViewById<ProgressBar>(R.id.progressBar3)
                        val ad=AlertDialog.Builder(this@MenuActivity)
                        ad.setView(tasarim)
                        val name="Abdulkadir Kara"
                        val password="02200201002"
                        var ischeck1=false
                        var ischeck2=false
                        isim.addTextChangedListener {
                            if (isim.text.toString()==name){
                                ischeck1=true
                                if (ischeck2){
                                    progressexit.visibility=View.VISIBLE
                                    val sp=getSharedPreferences("com.abdulkadirkara.finalapp",Context.MODE_PRIVATE)
                                    val spedit=sp.edit()
                                    spedit.remove(anahtarisim)
                                    spedit.remove(anahtarsifre)
                                    spedit.remove(anahtarunutma)
                                    spedit.apply()
                                    val logoutintent=Intent(applicationContext,LoginActivity::class.java)
                                    startActivity(logoutintent)
                                    finish()
                                }

                            }
                        }
                        sifre.addTextChangedListener {
                            if (sifre.text.toString()==password){
                                ischeck2=true
                                if (ischeck1){
                                    progressexit.visibility=View.VISIBLE
                                    val sp=getSharedPreferences("com.abdulkadirkara.finalapp",Context.MODE_PRIVATE)
                                    val spedit=sp.edit()
                                    spedit.remove(anahtarisim)
                                    spedit.remove(anahtarsifre)
                                    spedit.remove(anahtarunutma)
                                    spedit.apply()
                                    val logoutintent=Intent(applicationContext,LoginActivity::class.java)
                                    startActivity(logoutintent)
                                    finish()
                                }
                            }
                        }

                        ad.create().show()
                        true
                    }
                    else->{
                        fragmentcagir(ilk())
                        true
                    }
                }
            }
            acilirmenu.show()
        }
    }

    fun fragmentcagir(fragment: Fragment){
        var gecis=supportFragmentManager.beginTransaction()
        gecis.replace(R.id.fragmentContainerView2,fragment)
        gecis.commit()
    }
}

