package com.alexpetrov.tinder.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexpetrov.tinder.data.ktor.CatService
import com.alexpetrov.tinder.data.model.Cat
import com.alexpetrov.tinder.presentation.utils.AppState
import com.alexpetrov.tinder.presentation.utils.sortList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatViewModel : ViewModel() {

    private val service = CatService.create()
    private val data = MutableLiveData<AppState>()

    val liveData: LiveData<AppState> = data

    fun getData() {
        viewModelScope.launch { loadData() }
    }

    private suspend fun loadData() = withContext(Dispatchers.IO) {
        try {
            val catResponse = service.getCatList()
            val favoriteData = arrayListOf<Cat>()
            for (item in catResponse) {
                favoriteData.add(
                    Cat(
                        item.created_at,
                        item.value,
                        service.getImageById(item.image_id).url
                    )
                )
            }
            data.postValue(AppState.SuccessFavorite(sortList(favoriteData)))
        } catch (e: Throwable) {
            data.postValue(AppState.Error(e))
        }
    }
}