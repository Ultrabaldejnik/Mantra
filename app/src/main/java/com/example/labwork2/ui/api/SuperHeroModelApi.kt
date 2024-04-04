package com.example.labwork2.ui.api


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SuperHeroModelApi(
    var name: String? = null,
    var realname: String? = null,
    var team: String? = null,
    var firstapperance: String? = null,
    var createdby: String? = null,
    var publisher: String? = null,
    var imageurl: String? = null,
    var bio: String? = null
) : Parcelable