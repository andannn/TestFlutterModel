package com.example.testfluttermodel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import com.example.testfluttermodel.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: ${this.hashCode()}")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        lifecycleScope.launch {
            navController.currentBackStack.collect {
                Log.d(TAG, "onStart: visibleEntries ============================================")
                it.forEach {
                    Log.d(TAG, "onStart: visibleEntries ${it}")
                }
            }
        }

        navController.navigate(
            resId = R.id.login_navigation,
            args = null,
            navOptions = navOptions {
                popUpTo(R.id.nav_graph)
            },
        )
    }
}