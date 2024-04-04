package com.example.labwork2

import android.app.Application
import com.example.labwork2.ui.api.ApiInstance
import com.example.labwork2.db.HeroDB

class App : Application() {

    val dbHeroes by lazy { HeroDB.getDatabase(this) }
    val apiHeroes by lazy { ApiInstance }
}