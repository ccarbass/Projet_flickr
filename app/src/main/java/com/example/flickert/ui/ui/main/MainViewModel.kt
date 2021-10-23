package com.example.flickert.ui.ui.main

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickert.model.Photo
import com.example.flickert.model.Photos
import com.example.flickert.model.SearchResult
import com.example.flickert.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    var photos = MutableLiveData<Photos>()
    var liste_photo = ArrayList<Photo>()
    var index = 0


    init{
        val repository = Repository()
        repository.getPhotos(object:Callback<SearchResult>{
            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                Log.v("flickr_callback","erreur de callback avec flickr")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val rep = response.body()
                photos.value = rep?.photos!!
                Log.v("reponse_photo", "photos récupérées")

            }
        })

    }


    fun nextImage() {

        Log.v("index",index.toString())
        if(index < photos.value?.photo?.size!!-1){
            index = index +1
        }
        else{
            index=0
        }
    }
}