package com.sanjay.random_memes_app

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://meme-api.com/gimme"


interface MemeInterface {
    @GET("random")
 fun getMemes(@Query("memeurl") url: String): Call<Meme>
}

object MemeApiService {

    val memeinstance: MemeInterface
        init{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        memeinstance =    retrofit.create(MemeInterface::class.java)
        }

    }


