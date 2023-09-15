package com.example.kishaanmadad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gal)

        val sign=findViewById<Button>(R.id.signup_page)
        val log=findViewById<Button>(R.id.login_page)

        sign.setOnClickListener{
            val intent= Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        log.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}