package com.example.luka_mazmishvili_6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.luka_mazmishvili_6.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val adapter by lazy {
        RecyclerViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        observer()
    }

    private fun setupViews() {
        with(binding) {
            rvItems.adapter = adapter

            adapter.onItemClick = {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("itemId", it.id)
                startActivity(intent)
            }
        }
    }

    private fun observer() {
        lifecycleScope.launch {
          lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
              viewModel.fetchData().collect {
                  adapter.submitList(it.data.toList())
                  Log.d("VNAXOTDATA", "observer: $it")
              }
          }
        }
    }
}