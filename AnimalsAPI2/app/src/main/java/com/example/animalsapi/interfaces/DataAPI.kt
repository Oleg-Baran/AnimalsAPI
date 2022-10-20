package com.example.animalsapi.interfaces

import com.example.animalsapi.data.Animal
import retrofit2.Response

interface DataAPI {
    fun getDataOkHttp(result: String?)
    fun getDataRetrofit(result: Response<MutableList<Animal>>)
}