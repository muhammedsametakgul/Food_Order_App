package com.example.yemekuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.yemekuygulamasi.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding:ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        auth = Firebase.auth
        val current=auth.currentUser
        if(current != null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        binding.buttonGirisYap.setOnClickListener {
            val email= binding.txtEmail.text.toString()
            val sifre = binding.txtSifre.text.toString()
            Log.e("email and sifre",email+sifre)
            girisYap(email, sifre)
        }
        binding.txtHesapOlustur.setOnClickListener {
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)
    }

    fun girisYap(email:String,sifre:String){
        if(!email.isEmpty() && !sifre.isEmpty()){
            auth.signInWithEmailAndPassword(email,sifre).addOnSuccessListener {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Hoşgeldiniz ${email}",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Giriş başarısız. Lütfen bilgileri kontrol ediniz",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Lütfen tüm alanları doldurunuz",Toast.LENGTH_SHORT).show()
        }
    }
}