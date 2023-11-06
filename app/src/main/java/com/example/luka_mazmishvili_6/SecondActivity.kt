package com.example.luka_mazmishvili_6

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.luka_mazmishvili_6.databinding.ActivitySecondBinding
import com.example.luka_mazmishvili_6.models.DataModel
import com.example.luka_mazmishvili_6.models.ItemModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

         val itemId = intent.getIntExtra("itemId", -1)
        observer(itemId)
    }

    private fun updateUi(item: ItemModel) {
        with(binding) {
            root.setBackgroundColor(item.data.color.toColorInt())
            tvId.text = item.data.id.toString()
            tvName.text = item.data.name
            tvYear.text = item.data.year.toString()
            tvPantoneValue.text = item.data.pantoneValue
        }
    }

    private fun observer(itemId: Int) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchDataById(itemId).collect {
                    updateUi(it)
                }
            }
        }
    }
}