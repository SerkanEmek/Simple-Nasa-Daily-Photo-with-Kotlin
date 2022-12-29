package com.serkanemek.spacenasaphotos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serkanemek.spacenasaphotos.model.NasaDailyPhotoModel
import com.serkanemek.spacenasaphotos.service.PhotoAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val photoApiService = PhotoAPIService()
    private val disposable = CompositeDisposable()

    val photo_data = MutableLiveData<NasaDailyPhotoModel>()
    val photo_error = MutableLiveData<Boolean>()
    val photo_loading = MutableLiveData<Boolean>()


    fun refreshData(){
        getDataFromAPI()

    }

    private fun getDataFromAPI() {
        photo_loading.value = true
        disposable.add(
            photoApiService.getDataService()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NasaDailyPhotoModel>(){
                    override fun onSuccess(t: NasaDailyPhotoModel) {
                        photo_data.value = t
                        photo_error.value = false
                        photo_loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        photo_error.value = true
                        photo_loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }


}