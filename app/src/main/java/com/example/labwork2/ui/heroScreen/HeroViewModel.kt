package com.example.labwork2.ui.heroScreen


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.labwork2.ui.api.SuperHeroModelApi
import com.example.labwork2.App
import com.example.labwork2.db.SuperHeroDAO
import com.example.labwork2.db.SuperHeroModel
import kotlinx.coroutines.launch

class HeroViewModel(private val heroDB: SuperHeroDAO) : ViewModel() {

    private var _toastMessage = MutableLiveData("")
    val toastMessage: LiveData<String>
        get() = _toastMessage


    fun addFavHero(heroModel: SuperHeroModelApi) = viewModelScope.launch {

        val existHero = heroDB.getHero(heroModel.name!!)
        if (existHero == 1) {
            _toastMessage.value = "Этот герой уже в избранном"
        } else {
            _toastMessage.value = "Этот герой был добавлен"
            val addHero = SuperHeroModel(
                name = heroModel.name,
                realname = heroModel.realname,
                createdby = heroModel.createdby,
                firstapperance = heroModel.firstapperance,
                imageurl = heroModel.imageurl,
                publisher = heroModel.publisher,
                team = heroModel.team,
                bio = heroModel.bio,
            )
            heroDB.addHero(addHero)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val db =
                    (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as App).dbHeroes.dao

                return HeroViewModel(db) as T
            }
        }
    }
}