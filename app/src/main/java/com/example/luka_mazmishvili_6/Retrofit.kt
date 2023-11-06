package com.example.luka_mazmishvili_6

import com.example.luka_mazmishvili_6.models.DataModel
import com.example.luka_mazmishvili_6.models.ItemModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object Retrofit {

    private const val BASE_URL = "https://reqres.in/api/"

    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api = retrofit.create(Service::class.java)

}

interface Service {
    @GET("unknown&#39")
    suspend fun getData() : Response<DataModel>

    @GET("unknown/{id}")
    suspend fun getDataById(@Path("id") itemId: Int) : Response<ItemModel>
}