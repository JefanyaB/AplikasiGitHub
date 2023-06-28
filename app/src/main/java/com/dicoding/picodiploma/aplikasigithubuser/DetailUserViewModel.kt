package com.dicoding.picodiploma.aplikasigithubuser

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class DetailUserViewModel(application: Application): AndroidViewModel(application) {
    val user = MutableLiveData<DetailResponse?>()

    private val _isLoading = MutableLiveData<Boolean>()

    fun setUserDetail(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ){
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        user.postValue(responseBody)
                        _isLoading.value=false
                    }
                } else {
                    Log.e(MainActivity.TAG, "onFailure: ${response.message()}")
                    _isLoading.value=false
                }
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e(MainActivity.TAG, "onFailure: ${t.message}")
                _isLoading.value=false
            }
        })
    }

    fun getUserDetail(): MutableLiveData<DetailResponse?> {
        return user
    }


}




