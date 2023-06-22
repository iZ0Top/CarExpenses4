package com.alex.carexpenses4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alex.carexpenses4.databinding.ActivityMainBinding
import com.alex.carexpenses4.utils.APP_ACTIVITY
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        APP_ACTIVITY = this

        bottomNavigationView = binding.bottomNav

        navController = findNavController(R.id.fragment_container)
        val bottomNavBar: BottomNavigationView = binding.bottomNav
        bottomNavBar.setupWithNavController(navController)

    }
}