package com.example.kishaanmadad

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor= Color.TRANSPARENT
        Toast.makeText(this, "Welcome...", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            if
                (FirebaseAuth.getInstance().currentUser == null)
                startActivity(Intent(this, GalActivity::class.java))             //not login
            else
                startActivity(Intent(this, HomeActivity::class.java))                  //redirect to home activity
           startActivity(Intent(this, GalActivity::class.java))
            finish()
        }, 2000)
    }
}