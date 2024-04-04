package com.example.labwork2.ui.ListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.labwork2.ui.api.RetrofitServices
import com.example.labwork2.ui.api.SuperHeroModelApi
import com.example.labwork2.ApiState
import com.example.labwork2.App
import kotlinx.coroutines.launch

class ListViewModel(private val api: RetrofitServices) : ViewModel() {


    private var _status = MutableLiveData<ApiState>()
    val status : LiveData<ApiState>
        get() = _status

    private var _data = MutableLiveData<List<SuperHeroModelApi>>()
    val data : LiveData<List<SuperHeroModelApi>>
        get() = _data

    init {
        getData()
    }

    private fun getData(){
        _status.value = ApiState.LOADING
        viewModelScope.launch {
            try {
                _data.value = api.getMovieList()
                _status.value = ApiState.SUCCESS


            }catch (e : Exception){
                _status.value = ApiState.FAILURE
            }
        }
    }
    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val api =
                    (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).apiHeroes.retrofitService

                return ListViewModel(api) as T
            }
        }
    }
}