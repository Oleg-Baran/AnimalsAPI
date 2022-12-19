package com.example.animalsapi.interfaces

import com.example.animalsapi.data.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("{count}")
    fun getAnimalList(
        @Path("count", encoded = true) count: String
    ): Call<MutableList<Animal>>
}