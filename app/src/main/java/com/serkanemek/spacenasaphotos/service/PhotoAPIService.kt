package com.serkanemek.spacenasaphotos.service

import com.serkanemek.spacenasaphotos.model.NasaDailyPhotoModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PhotoAPIService {


//https://api.nasa.gov/planetary/apod?api_key=oEdLnqKufFuR8pM3yGylBIrBEoiVfgXpMLZmk5Ru

    private val BASE_URL = "https://api.nasa.gov/planetary/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PhotoAPI::class.java)

    fun getDataService(): Single<NasaDailyPhotoModel>{
        return api.getData()
    }

}