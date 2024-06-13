package com.example.moonlight.api

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Retrofit

class RetrofitClient {

    companion object{
        private val client = Retrofit.Builder()
            .baseUrl("https://apis.data.go.kr/B090041/openapi/service/LunPhInfoService/")
            .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
            .build()

        val lunsApi:LunAgeApi = client.create(LunAgeApi::class.java)
    }
}