package com.example.artmobileapp.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.artmobileapp.models.Image;
import com.example.artmobileapp.network.api.ApiRepository;

class GalleryViewModel : ViewModel() {
    private val _photos = mutableStateListOf<Image>()
    private val _isLoadinig = mutableStateListOf(value false)
    private val apiRepo = ApiRepository()

    val photos: List<Image> get() = _photos
    val isLoading: Boolean get() = _isLoadinigphotos.value

    fun getRandom() {
        viewModelScope.launch {
            _isLoadinig.value = true
            val data = apiRepo.getImages()
            _photos.clear()
            _photos.addAll(data.data)
            isLoading.value = false
        }

    }
}

