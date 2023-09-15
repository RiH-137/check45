package com.example.kishaanmadad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kishaanmadad.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding // Declare ViewBinding variable
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Check if the user is already logged in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToHomeActivity()
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.email.editText?.text.toString().trim()
            val password = binding.password.editText?.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Authenticate the user with Firebase Authentication
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Login successful, navigate to HomeActivity
                            Toast.makeText(this@LoginActivity, "Signup Successful!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                            navigateToHomeActivity()
                        } else {
                            // Handle login failure
                            Toast.makeText(this@LoginActivity, task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // Handle empty fields
            }
        }
        binding.signupPage.setOnClickListener{
            val intent=Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun navigateToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // Close the LoginActivity to prevent going back
    }
}