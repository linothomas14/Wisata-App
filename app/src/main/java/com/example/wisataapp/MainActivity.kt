package com.example.wisataapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.wisataapp.adapter.WisataAdapter
import com.example.wisataapp.model.ResponseServer
import com.example.wisataapp.model.Wisata
import com.example.wisataapp.network.ConfigNetwork
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isConnect()){

        ConfigNetwork.getRetrofit().getDataWisata().enqueue(object : Callback<ResponseServer>{
            override fun onResponse(call: Call<ResponseServer>, response: Response<ResponseServer>) {
                // this fun is run when the server is ON
                progress.visibility = View.GONE
                Log.d("response server",response.message())
                //check response server
                if (response.isSuccessful){
                    val status:Int? = response.body()?.status_code  //call the var status_code from API
                    if (status == 200){ //if the status is 200 then make a new val that contain the Object of API
                        val data:ArrayList<Wisata>? = response.body()?.data // val data is our data from API
                        showData(data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                // this fun is run when the server is OFF or there is a problem with our ConfigNetwork
                progress.visibility = View.GONE
                Log.d("error server", t.message)
            }
        })
    } else{
            progress.visibility = View.GONE
        Toast.makeText(this,"device tidak connect internet",Toast.LENGTH_SHORT).show()
        }
    }


    fun isConnect():Boolean {
        val connect = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo.isConnected
    }

    private fun showData(data: ArrayList<Wisata>?) {
        listWisata.adapter = WisataAdapter(data)
    }
}
