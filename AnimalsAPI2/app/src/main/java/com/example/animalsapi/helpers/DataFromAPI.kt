package com.example.animalsapi.helpers

import com.example.animalsapi.data.Animal
import com.example.animalsapi.interfaces.DataAPI
import com.example.animalsapi.interfaces.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import java.util.concurrent.Executors

class DataFromAPI {
    private val baseUrl = "https://zoo-animal-api.herokuapp.com/animals/rand/5"
    private val baseUrlR = "https://zoo-animal-api.herokuapp.com/animals/rand/"
    private lateinit var dataAPI: DataAPI
    private var clientOkHTTP: OkHttpClient = OkHttpClient()
    private var clientRetrofit: RetrofitService = RetrofitClient.getClient(baseUrlR).create(RetrofitService::class.java)

    // OkHTTP Request
    private fun getRequestOkHTTP(): String? {
        var result: String? = null
        try {
            val request = Request.Builder().url(baseUrl).build()
            val response = clientOkHTTP.newCall(request).execute()
            result = response.body?.string()
        }
        catch(err:Error) {
            print("Error when executing get request: "+err.localizedMessage)
        }
        return result
    }

    // Retrofit Request
    private fun getRequestRetrofit(): Response<MutableList<Animal>> {
        return clientRetrofit.getAnimalList().execute()
    }

    fun getDataFromAPI() {
        val executor = Executors.newSingleThreadExecutor()
        executor.submit { dataAPI.getDataOkHttp(getRequestOkHTTP()) }
        executor.submit { dataAPI.getDataRetrofit(getRequestRetrofit()) }
    }

    fun setData(dataAPI: DataAPI){
        this.dataAPI = dataAPI
    }
}