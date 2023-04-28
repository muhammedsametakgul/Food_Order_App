package com.example.yemekuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.yemekuygulamasi.databinding.ActivitySifreSifirlamaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SifreSifirlamaActivity : AppCompatActivity() {
    private lateinit var  binding : ActivitySifreSifirlamaBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySifreSifirlamaBinding.inflate(layoutInflater)
        auth = Firebase.auth
        binding.btnSifreSifirla.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            if(!email!!.isEmpty()){
              auth.sendPasswordResetEmail(email).addOnSuccessListener {
                  Toast.makeText(this,"Sıfırlama maili başarıyla gönderildi",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,SignInActivity::class.java)
                  startActivity(intent)
              }.addOnFailureListener {
                  Toast.makeText(this,"Mail Gönderilemedi!!!",Toast.LENGTH_SHORT).show()

              }
            }else{
                Toast.makeText(this,"Mail Kısmı Boş Bırakılamaz!!!",Toast.LENGTH_SHORT).show()

            }
        }


        setContentView(binding.root)
    }
}