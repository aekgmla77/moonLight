package com.example.moonlight.api

import com.example.moonlight.model.LunAge
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LunAgeApi {

    companion object{
        private const val authKey = "yxSi2XNsU548qLVORNJSZQyG4U226QMv5vqruxo6dUoDiCFbyzH1BJ1wa5NW2PrAXTONDvFhQHwCBmatLngy4g=="
    }

    @GET("getLunPhInfo")
    fun getLunAge(@Query("solYear") solYear:Int,
                  @Query("solMonth") solMonth:String,
                  @Query("solDay") solDay:String,
                  @Query("ServiceKey") serviceKey: String? = authKey
    ): Call<LunAge>
}