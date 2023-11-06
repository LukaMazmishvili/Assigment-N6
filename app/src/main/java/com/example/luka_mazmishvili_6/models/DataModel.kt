package com.example.luka_mazmishvili_6.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class DataModel(
    val data: List<Item>
) {
    data class Item(
        val id: Int,
        val name: String,
        val year: Int,
        val color: String,
        @SerializedName("pantone_value")
        val pantoneValue: String,
    )
}
