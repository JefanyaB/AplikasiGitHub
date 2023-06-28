package com.dicoding.picodiploma.aplikasigithubuser

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.aplikasigithubuser.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.widget.SearchView


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object{
        const val TAG = "MainActivity"
        private const val USER_LOGIN = "arif"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvList.addItemDecoration(itemDecoration)

        findUserActivity()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                findUserActivity(query)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    private fun findUserActivity(SearchUser : String = USER_LOGIN) {
        showLoading(true)
        val client = ApiConfig.getApiService().getListUsers(SearchUser)
        client.enqueue(object : Callback<ResponseUser>{
            override fun onResponse(
                call: Call<ResponseUser>,
                response: Response<ResponseUser>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setUserData(responseBody.items as List<ItemsItem>)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun setUserData(item: List<ItemsItem>) {
        val listAdapter = UserAdapter(item)
        binding.rvList.adapter = listAdapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbBar.visibility = View.VISIBLE
        } else {
            binding.pbBar.visibility = View.GONE
        }
    }

}