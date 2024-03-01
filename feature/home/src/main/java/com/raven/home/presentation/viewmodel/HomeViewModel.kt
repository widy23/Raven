package com.raven.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raven.home.data.HomeRepository
import com.raven.home.data.remote.ServiceResult
import com.raven.home.db.NewsModelDB
import com.raven.home.domain.models.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){


    private val _news = MutableLiveData <List<NewsModelDB>> ()
    val news: LiveData<List<NewsModelDB>?>  = _news

    private val _loading = MutableLiveData <Boolean> ()
    val loading: LiveData<Boolean>  = _loading

    private val _newsSelected = MutableLiveData <NewsModelDB> ()
    val newsSelected: LiveData <NewsModelDB>  = _newsSelected


     fun updatePeriodAndFetchNews(period: String) {
        viewModelScope.launch {
            repository.getNews(period).collect{
                _news.postValue(it)
             }

            }
        }
    fun getNewsFromDataBase(){
        val result=repository.newsFromDb()
        _news.postValue(result)

    }


    fun setSelectedNews(item: NewsModelDB) {
        _newsSelected.postValue(item)
    }

    fun activateShimmer(value:Boolean) {
        _loading.postValue(value)
    }

}
