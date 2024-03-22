package com.training.shopcartecom

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.training.shopcartecom.databinding.ActivityMainBinding
import com.training.shopcartecom.view.homedashboard.HomeDashBoard
import com.training.shopcartecom.view.user.UserRegistrationFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initPref()
    }

    private fun initViews() {
        supportFragmentManager.beginTransaction().replace(R.id.container1, HomeDashBoard()).commit()
    }
    private fun initPref(){
        sharedPreferences=getSharedPreferences("Login", MODE_PRIVATE)
    }
}