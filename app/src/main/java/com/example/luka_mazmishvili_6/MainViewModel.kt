package com.example.luka_mazmishvili_6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun fetchData() = flow {
        val body = Retrofit.api.getData()
        if (body.isSuccessful) {
            val data = body.body()
            emit(data!!)
        } else {
            val errorBody = body.errorBody()
        }
    }

    fun fetchDataById(itemId: Int) = flow {
        val body = Retrofit.api.getDataById(itemId)
        if (body.isSuccessful) {
            val data = body.body()
            emit(data!!)
        } else {
            val errorBody = body.errorBody()
        }
    }
}