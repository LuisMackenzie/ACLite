package com.mackenzie.aclite.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mackenzie.aclite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListeners()

        viewModel.state.observe(this) { state ->
            binding.etUser.error = state.userError?.let { getString(it) }
            /*binding.etUser.error = state.userError?.let(this::getString)
            binding.etUser.error = state.userError?.let(::getString)*/
            binding.etPass.error = state.passError?.let(::getString)
            binding.btnLogin.visibility = if (state.loggingIn) View.GONE else View.VISIBLE
            binding.showProgress.visibility = if (state.loggingIn) View.VISIBLE else View.GONE

            if (state.loggedIn) {
                startActivity(Intent(this, NextActivity::class.java))
                viewModel.onNavigateToNextScreen()
                finish()
            }

        }

    }

    private fun setUpListeners() {

        with(binding) {
            btnLogin.setOnClickListener {
                viewModel.onTryLogin(etUser.text.toString(), etPass.text.toString())
            }

        }

    }


}