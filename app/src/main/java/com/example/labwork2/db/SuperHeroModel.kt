package com.example.labwork2.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity
@Parcelize
data class SuperHeroModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var name: String? = null,
    var realname: String? = null,
    var team: String? = null,
    var firstapperance: String? = null,
    var createdby: String? = null,
    var publisher: String? = null,
    var imageurl: String? = null,
    var bio: String? = null
) : Parcelable