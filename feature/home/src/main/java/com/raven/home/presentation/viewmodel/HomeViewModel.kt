package com.raven.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raven.home.data.HomeRepository
import com.raven.home.domain.models.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _news = MutableLiveData <List<NewsModel>> ()
    val news: LiveData<List<NewsModel>?>  = _news


     fun updatePeriodAndFetchNews(period: String) {
        viewModelScope.launch {
            repository.getNews(period).collect{
                _news.postValue(it)
             }

            }
        }

}
