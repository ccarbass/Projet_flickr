package com.example.flickert.repository

import android.telecom.Call
import com.example.flickert.model.SearchResult
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPI {
    @GET("/services/rest?format=json&nojsoncallback=1")
    fun getInterestingPhotos(@Query("method") method : String,
                             @Query("api_key") key: String,
                             @Query("per_page") perpage: String): Callback<SearchResult>
}