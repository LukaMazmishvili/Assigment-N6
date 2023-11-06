package com.example.luka_mazmishvili_6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.luka_mazmishvili_6.databinding.ItemBinding
import com.example.luka_mazmishvili_6.models.DataModel

class RecyclerViewAdapter :
    ListAdapter<DataModel.Item, RecyclerViewAdapter.ViewHolder>(ItemDiffUtil()) {

    var onItemClick: ((DataModel.Item) -> Unit)? = null

    class ViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root
        val id = binding.tvId
        val name = binding.tvName
        val year = binding.tvYear
        val pantoneValue = binding.tvPantoneValue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        with(holder) {
            root.setBackgroundColor(item.color.toColorInt())
            id.text = item.id.toString()
            name.text = item.name
            year.text = item.year.toString()
            pantoneValue.text = item.pantoneValue
        }

        holder.root.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    class ItemDiffUtil() : DiffUtil.ItemCallback<DataModel.Item>() {
        override fun areItemsTheSame(oldItem: DataModel.Item, newItem: DataModel.Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataModel.Item, newItem: DataModel.Item): Boolean {
            return oldItem == newItem
        }

    }
}