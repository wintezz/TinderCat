package com.alexpetrov.tinder.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexpetrov.tinder.data.dto.CatRequest
import com.alexpetrov.tinder.data.dto.Message
import com.alexpetrov.tinder.data.ktor.CatService
import com.alexpetrov.tinder.presentation.utils.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val service = CatService.create()
    private val dataImage = MutableLiveData<AppState>()
    private val dataCat = MutableLiveData<Message>()

    val liveDataPost: LiveData<AppState> = dataImage
    val liveDataVote: LiveData<Message> = dataCat

    fun getData() {
        viewModelScope.launch { loadData() }
    }

    private suspend fun loadData() = withContext(Dispatchers.IO) {
        try {
            dataImage.postValue(AppState.SuccessMain(service.getImage()))
        } catch (e: Throwable) {
            dataImage.postValue(AppState.Error(e))
        }
    }

    fun postVote(postRequest: CatRequest) {
        viewModelScope.launch {
            createVote(postRequest)
        }
    }

    private suspend fun createVote(postRequest: CatRequest) =
        withContext(Dispatchers.IO) {
            dataCat.postValue(service.createCat(postRequest))
        }
}