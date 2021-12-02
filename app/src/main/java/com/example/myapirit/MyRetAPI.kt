package com.example.myapirit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyRetAPI {

    companion object{
        val BASE_URL:String = "http://demo.onmyfinger.com/"
    }

    @GET("home/getbyid")
    fun getStudentById(@Query("id") id:String): Call<StudentRespond>



}