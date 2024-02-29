package com.raven.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.raven.home.data.HomeRepository
import com.raven.home.domain.models.NewsModel
import com.raven.home.domain.models.ResponseApiNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _news = MutableLiveData<List<NewsModel>>()
    val news: LiveData<List<NewsModel>> = _news

    val newsDataList = liveData(Dispatchers.IO) {
        emitSource(repository.getNews().asLiveData())
    }
     fun updatePeriodAndFetchNews(period: Int) {
         viewModelScope.launch {
             repository.updatePeriod(period)
             repository.getNews()
         }

    }







}
