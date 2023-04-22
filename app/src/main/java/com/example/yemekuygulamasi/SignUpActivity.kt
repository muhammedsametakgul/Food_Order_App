package com.example.yemekuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.example.yemekuygulamasi.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        auth = Firebase.auth
        binding.buttonKayitOl.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val sifre1=binding.txtSifre.text.toString()
            val sifre2=binding.txtSifre2.text.toString()
            kayitOl(email,sifre1,sifre2)

        }
        setContentView(binding.root)
    }
    fun kayitOl(email:String,sifre:String,sifre2:String){
        if(!email.isEmpty() && !sifre.isEmpty() && !sifre2.isEmpty()){
            if(sifre == sifre2){
                auth.createUserWithEmailAndPassword(email,sifre).addOnSuccessListener {

                    binding.animationView.visibility= View.VISIBLE
                    binding.constraintLayout4.visibility=View.INVISIBLE
                    val handler = Handler()
                    handler.postDelayed({
                        handler.postDelayed({
                            val animationView = findViewById<LottieAnimationView>(R.id.animationView)
                            animationView.playAnimation()
                        },1000)
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }, 2000)



                   // Toast.makeText(this,"Hoşgeldiniz ${email}", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this,"Kayıt oluşturma başarısız", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Şifreler Uyuşmuyor",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Lütfen tüm alanları doldurunuz", Toast.LENGTH_SHORT).show()
        }
    }
}