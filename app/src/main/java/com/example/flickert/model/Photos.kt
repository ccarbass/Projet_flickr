package com.example.flickert.model

class Photos(
    val page: Int, val pages: Int, val perpage: Int,
    val total: String, val photo: List<Photo>){
    override fun toString(): String {
        var value = ""
        for(photo in photo){
            value += photo.title
        }
        return value
    }
}
