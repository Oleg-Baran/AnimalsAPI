package com.example.animalsapi.helpers

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getClient(url: String): Retrofit{
        if (retrofit == null) {
            val client = OkHttpClient().newBuilder()
            client.interceptors().add(Interceptor { chain ->
                val original: Request = chain.request()
                //Add Settings for request
                val request: Request = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Authorization", "auth-token")
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            } )
            retrofit = Retrofit.Builder()
                .client(client.build())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}