package com.example.fbaauthdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.fbaauthdb.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.login.setOnClickListener{
            val email= binding.eMail.text.toString()
            val pass = binding.password.text.toString()
            signIn(email, pass)
        }
        updateUI(auth.currentUser)
    }

    private fun signIn(email : String, pass: String){
        if(validateForm(email, pass)){
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
                if(it.isSuccessful){
                    updateUI(auth.currentUser)
                }else{
                    Log.w("FBAuthdb", "signInWithCredential:failure", it.exception)
                    Toast.makeText(baseContext, it.exception!!.message, Toast.LENGTH_SHORT).show()
                    updateUI(null)
                    binding.eMail.text.clear()
                    binding.password.text.clear()
                }
            }
        }
    }

    private fun validateForm(email: String, pass: String): Boolean {
        return true
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser !=null){
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}



