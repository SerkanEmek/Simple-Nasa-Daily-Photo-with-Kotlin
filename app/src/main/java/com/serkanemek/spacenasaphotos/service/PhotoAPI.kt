package com.serkanemek.spacenasaphotos.service

import com.serkanemek.spacenasaphotos.model.NasaDailyPhotoModel
import io.reactivex.Single
import retrofit2.http.GET


//https://api.nasa.gov/planetary/apod?api_key=oEdLnqKufFuR8pM3yGylBIrBEoiVfgXpMLZmk5Ru


interface PhotoAPI {

    @GET("apod?api_key=oEdLnqKufFuR8pM3yGylBIrBEoiVfgXpMLZmk5Ru")
    fun getData() : Single<NasaDailyPhotoModel>

}