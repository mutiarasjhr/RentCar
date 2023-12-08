package com.il.rentcar.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val brand: String,
    val capacity: Int,
    val harga: String,
) : Parcelable
