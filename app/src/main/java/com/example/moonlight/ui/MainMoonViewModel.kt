package com.example.moonlight.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moonlight.api.RetrofitClient
import com.example.moonlight.model.LunAge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class MainMoonViewModel : ViewModel() {
    val moon = MutableLiveData<LunAge>()

    // 날짜 매개변수로 월령 얻기
    fun getMoons(year:Int, month:Int, day:Int) = viewModelScope.launch(Dispatchers.IO){
        // 월, 일이 한자리면 앞에 0 들어가야 해서 string으로 변환
        val monthString = if (month < 10) "0$month" else month.toString()
        val dayString = if (day < 10) "0$day" else day.toString()

        Log.d("MoonViewModel", "Requesting data for date: $year-$monthString-$dayString")

        RetrofitClient.lunsApi.getLunAge(year, monthString, dayString).enqueue(object: Callback<LunAge> {
            override fun onResponse(
                call: Call<LunAge>,
                response: Response<LunAge>
            ) {
                if(response.isSuccessful && response.code() == 200){
                    val items = response.body()?.body?.items
                    moon.postValue(response.body())
                }else {
                    Log.e("onResponse", "Response not successful or code is not 200: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(p0: Call<LunAge>, p1: Throwable) {
                Log.e("main", p1.message.toString())
            }
        })
    }
}