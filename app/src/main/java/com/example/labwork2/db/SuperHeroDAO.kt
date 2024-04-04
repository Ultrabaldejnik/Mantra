package com.example.labwork2.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SuperHeroDAO {
    @Upsert
    suspend fun addHero(model: SuperHeroModel)

    @Query("SELECT COUNT(*) FROM superheromodel WHERE name = :heroName")
    suspend fun getHero(heroName: String): Int

    @Query("SELECT * FROM superheromodel")
    fun getHeroes() : LiveData<List<SuperHeroModel>>

}