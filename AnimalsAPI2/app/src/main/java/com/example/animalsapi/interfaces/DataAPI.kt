package com.example.animalsapi.interfaces

import com.example.animalsapi.data.Animal
import retrofit2.Response

interface DataAPI {
    fun okHttpGetData(result: String?)
    fun retrofitGetData(result: Response<MutableList<Animal>>)
}