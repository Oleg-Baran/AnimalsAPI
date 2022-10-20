package com.example.animalsapi.interfaces

import com.example.animalsapi.data.Animal
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("3")
    fun getAnimalList(): Call<MutableList<Animal>>
}