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
    private val retrofitUrl = "https://zoo-animal-api.herokuapp.com/animals/rand/"
    private lateinit var dataAPI: DataAPI
    private var oKClient: OkHttpClient = OkHttpClient()
    private var retrofitClient: RetrofitService = RetrofitClient.getClient(retrofitUrl).create(RetrofitService::class.java)

    // OkHTTP Request
    private fun getRequestOkHTTP(): String? {
        var result: String? = null
        try {
            val request = Request.Builder().url(baseUrl).build()
            val response = oKClient.newCall(request).execute()
            result = response.body?.string()
        }
        catch(err:Error) {
            print("Error when executing get request: "+err.localizedMessage)
        }
        return result
    }

    // Retrofit Request
    private fun getRequestRetrofit(): Response<MutableList<Animal>> {
        return retrofitClient.getAnimalList("3").execute()
    }

    fun getDataFromAPI() {
        val executor = Executors.newSingleThreadExecutor()
        executor.submit { dataAPI.okHttpGetData(getRequestOkHTTP()) }
        executor.submit { dataAPI.retrofitGetData(getRequestRetrofit()) }
    }

    fun setData(dataAPI: DataAPI){
        this.dataAPI = dataAPI
    }
}