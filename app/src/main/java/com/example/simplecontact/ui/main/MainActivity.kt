package com.example.simplecontact.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.simplecontact.R
import com.example.simplecontact.databinding.ActivityMainBinding
import com.example.simplecontact.databinding.ToolbarLayoutBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbarBind: ToolbarLayoutBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        toolbarBind = ToolbarLayoutBinding.bind(binding.root)
        setContentView(binding.root)

        // setup ContainerFragment
        setupContainer()

        // setup toolbar
        setupToolbar()
    }

    private fun setupContainer(){
        val navHostContainer = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostContainer.navController

        appBarConfig = AppBarConfiguration(navController.graph)
    }

    private fun setupToolbar(){
        setSupportActionBar(toolbarBind.toolbar)
        toolbarBind.toolbar.setupWithNavController(navController,appBarConfig)
    }
}