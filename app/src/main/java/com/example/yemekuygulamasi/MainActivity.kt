package com.example.yemekuygulamasi


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.yemekuygulamasi.databinding.ActivityMainBinding
import com.example.yemekuygulamasi.ui.BadgeBox
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
       // binding.bottomnavigationview.background=null
        binding.bottomnavigationview.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED


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
                R.id.favoriler ->{
                    navController.navigate(R.id.favorilerFragment)
                    true
                }
                R.id.profil ->{
                    navController.navigate(R.id.profilFragment)
                    true
                }
                else -> false
            }
        }

    }


}