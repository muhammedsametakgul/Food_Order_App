package com.example.yemekuygulamasi

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.yemekuygulamasi.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomnavigationview.background=null
        binding.bottomnavigationview.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED


        binding.bottomnavigationview.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.anasayfa -> {
                    navController.navigate(R.id.anasayfaFragment)
                    true
                }
                R.id.sepet -> {
                    navController.navigate(R.id.sepetFragment)
                    true
                }
                else -> false
            }
        }

    }
}