package com.pumi.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import com.pumi.app.databinding.ActivityMainBinding
import com.pumi.app.utils.KeepStateNavigator
import com.pumi.app.view.mi.MiFragment
import com.pumi.app.view.fragment.PhumFragment

class MainActivity : PhumiActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.fragment_container_view)
        navController.navigatorProvider += navigator
        navController.setGraph(R.navigation.app_navigtion)
        binding.bottomNav.setupWithNavController(navController)

    }

}