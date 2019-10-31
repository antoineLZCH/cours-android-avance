package com.antoinelzch.kg.network

import com.antoinelzch.kg.models.Game
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://my-json-server.typicode.com/bgdom/cours-android/"

interface GamesApi {

    @GET("games")
    fun getGames() : Call<List<Game>>

    companion object {

        operator fun invoke() : GamesApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GamesApi::class.java)
        }
    }

}