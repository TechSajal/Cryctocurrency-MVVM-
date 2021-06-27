package com.example.wiseleap.Repository

import com.example.wiseleap.Model.UsersResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface UserRepository {

     @GET("markets")
     suspend fun  getUsers(): Response <UsersResponse>



         //BASEURL =  https://www.cryptingup.com/api/markets

     companion object {
         operator fun invoke(): UserRepository {
             return Retrofit.Builder()
                 .addConverterFactory(GsonConverterFactory.create())
                 .baseUrl("https://www.cryptingup.com/api/")
                 .build()
                 .create(UserRepository::class.java)


         }
     }




 }
