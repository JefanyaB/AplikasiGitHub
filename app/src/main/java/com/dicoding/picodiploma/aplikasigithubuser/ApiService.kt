package com.dicoding.picodiploma.aplikasigithubuser

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import com.dicoding.picodiploma.aplikasigithubuser.ResponseUser
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET("/search/users")
    @Headers ("Authorization: token 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")
    fun getListUsers(
        @Query("q") name : String,

    ): Call<ResponseUser>

    @GET("/users/{username}")
    @Headers ("Authorization: token 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")
    fun getDetailUser(
        @Path ("username") username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    @Headers ("Authorization: token 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")
    fun getFollowers(
        @Path ("username") username: String
    ): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    @Headers ("Authorization: token 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")
    fun getFollowing(
        @Path ("username") username: String
    ): Call<List<ItemsItem>>
}