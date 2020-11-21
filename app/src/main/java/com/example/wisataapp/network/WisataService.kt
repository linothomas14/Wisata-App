package com.example.wisataapp.network

import com.example.wisataapp.model.ResponseServer
import retrofit2.Call
import retrofit2.http.GET

interface WisataService {
// this interface is for make a request to server, and you should put the additional URL continue from the main URL in ConfigNetwotk class
    @GET("api?action=findAll")
    fun getDataWisata(): Call<ResponseServer>
}