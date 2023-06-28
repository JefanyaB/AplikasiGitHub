package com.dicoding.picodiploma.aplikasigithubuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel: ViewModel(){
    private val _listFollower = MutableLiveData<List<ItemsItem>>()
    val listFollower: LiveData<List<ItemsItem>> = _listFollower

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setListFollower(login: String){
        _isLoading.value = true
        ApiConfig.getApiService().getFollowers(login).
            enqueue(object : Callback<List<ItemsItem>>{
                override fun onResponse(
                    call: Call<List<ItemsItem>>,
                    response: Response<List<ItemsItem>>
                ) {
                    if (response.isSuccessful) {
                        _listFollower.value = response.body()
                        _isLoading.value=false
                    } else {
                        Log.e(MainActivity.TAG, "onFailure: ${response.message()}")
                        _isLoading.value=false
                    }
                }
                override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                    Log.e(MainActivity.TAG, "onFailure: ${t.message}")
                    _isLoading.value=false
                }
            })
    }

}