package com.example.flordechocolate.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.flordechocolate.R
import com.example.flordechocolate.data.viewmodels.LoginViewModel
import com.example.flordechocolate.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activityHomeToolbar.title = ""
        setSupportActionBar(binding.activityHomeToolbar)
    }

    override fun onStart() {
        super.onStart()
        binding.activityHomeToolbar.setOnClickListener {
            Log.d("HOLA", "HOLA")
        }
        loginViewModel.currentUser()
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.ubicationFragment,
            R.id.productsFragment,
            R.id.profileFragment
        ))
        val navController = findNavController(R.id.host_home_fragment)
        binding.activityHomeBottonNavigation.setupWithNavController(navController)
        binding.activityHomeToolbar.setupWithNavController(navController, appBarConfiguration)
    }
}