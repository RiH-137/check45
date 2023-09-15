package com.example.kishaanmadad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment

import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()


        val bottomView = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Replace this with your initial fragment
        replaceWithFragment(Home())

        bottomView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceWithFragment(Home())
                R.id.profile -> replaceWithFragment(Profile())
                R.id.lang -> replaceWithFragment(Lang())
                R.id.scan -> replaceWithFragment(Scan())
                else -> {

                }
            }
            true
        }
    }

    private fun replaceWithFragment(fragment: Fragment) {
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}