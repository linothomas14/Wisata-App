package com.example.wisataapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ConfigNetwork {

    companion object {
        fun getRetrofit(): WisataService {
            val retrofit = Retrofit.Builder() //to build a retrofit configuration
                .baseUrl("http://udacoding.com/") //put your main URL
                .addConverterFactory(GsonConverterFactory.create()) //convert to GSON
                .build()
            val service: WisataService = retrofit.create(WisataService::class.java)
            return service
        }
    }
}