package com.example.flickert.ui.ui.liste

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickert.model.Photo
import com.example.flickert.model.SearchResult
import com.example.flickert.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {
    var photos = MutableLiveData<List<Photo>>()
    var listePhoto = ArrayList<Photo>()

    init{
        val repository = Repository()
        repository.getPhotos(object: Callback<SearchResult> {
            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                Log.v("flickr_callback","erreur de callback avec flickr")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val rep = response.body()?.photos
                for(photo in rep?.photo!!){
                    listePhoto.add(photo)

                }
                photos.value=listePhoto
                Log.v("reponse", "photos récupérées dans liste")


            }
        })

    }
}