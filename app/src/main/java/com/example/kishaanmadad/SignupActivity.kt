package com.example.kishaanmadad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.kishaanmadad.databinding.ActivitySignupBinding
import com.example.kishaanmadad.utils.USER_PROFILE_FOLDER
import com.example.kishaanmadad.utils.uploadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


class SignupActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseFirestore: FirebaseFirestore

    //image uploading
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        // Check if the uri is not null
        if (uri != null) {
            // Upload the image to the `USER_PROFILE_FOLDER` folder
            uploadImage(uri, USER_PROFILE_FOLDER){

                if(it ==null){

                }else{
                    binding.addImage.setImageURI(uri)



                }}
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)     //binding initializing
        setContentView(binding.root)

        /* val log_btn=findViewById<TextView>(R.id.login_page1)

        log_btn.setOnClickListener{
            val intent= Intent(this, LoginActivity::class.java)
            startActivity*/


        //upper right
        val log: TextView? = findViewById(R.id.login_page1)
        if (log != null) {
            log.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

//main
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseFirestore = FirebaseFirestore.getInstance()

            binding.signupBtn.setOnClickListener {
                val name = binding.name.editText?.text.toString().trim()
                val email = binding.email.editText?.text.toString().trim()
                val password = binding.password.editText?.text.toString().trim()

                if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    // Create a new user with Firebase Authentication
                    lifecycleScope.launch {
                        try {
                            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val user = hashMapOf(
                                        "name" to name,
                                        "email" to email
                                    )
                                    firebaseFirestore.collection("users").document(firebaseAuth.currentUser!!.uid).set(user)
                                    Toast.makeText(this@SignupActivity, "Signup Successful!", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@SignupActivity, HomeActivity::class.java)
                                    startActivity(intent)
                                    finish()

                                } else {
                                    Toast.makeText(this@SignupActivity, task.exception?.message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        } catch (e: Exception) {
                            Toast.makeText(this@SignupActivity, e.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // Handle empty fields
                }

                //opening gallery
                binding.addImage.setOnClickListener {                                 //addImage-->button name
                    // Launch the image picker
                    launcher.launch("image/*")
                }


            }
        }
    }}


