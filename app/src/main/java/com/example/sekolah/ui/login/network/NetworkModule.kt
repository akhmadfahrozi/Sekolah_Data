package com.example.sekolah.ui.login.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    //sesuaikan akses domain,ga boleh localhost(harus domain atau ip)
    const val BASE_URL = "http://192.168.2.236/ci_sekolah/index.php/Sekolah/"
//////////////////////////////////////////////////////////////////////////////////
    fun getOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }


    fun getService(): ApiService {
        return getRetrofit().create<ApiService>(ApiService::class.java)
    }
}