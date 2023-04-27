package com.example.yemekuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        auth = Firebase.auth

        val timer = object: CountDownTimer(1600, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
            if(auth.currentUser !=null){
                val intent = Intent(this@SplashScreenActivity,MainActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this@SplashScreenActivity,SignInActivity::class.java)
                startActivity(intent)
            }

            }
        }
        timer.start()
    }
}