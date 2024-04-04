package com.example.labwork2.ui.favouriteScreen


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.labwork2.App
import com.example.labwork2.db.SuperHeroDAO
import com.example.labwork2.db.SuperHeroModel

class FavouriteViewModel(private val heroDB: SuperHeroDAO) : ViewModel() {

    private var _listHero : LiveData<List<SuperHeroModel>> = heroDB.getHeroes()
    val listHero: LiveData<List<SuperHeroModel>>
        get() = _listHero
    private var _message = MutableLiveData(true)
    val message : LiveData<Boolean>
        get() = _message


    fun changeMessage(){
        val state = _listHero.value?.isEmpty()
        _message.value = state
        Log.d("MESSAGEVALUE",_message.value.toString())
    }




    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val db =
                    (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).dbHeroes.dao

                return FavouriteViewModel(db) as T
            }
        }
    }
}