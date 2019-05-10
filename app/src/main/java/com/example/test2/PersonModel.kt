package com.example.test2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonModel   (
    var firstName: String? = null ,
    var lastName: String? = null ,
    var age: String? = null ,
    var picture: String? = null
) : Parcelable