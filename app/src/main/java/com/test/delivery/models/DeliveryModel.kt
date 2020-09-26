package com.test.delivery.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class DeliveryModel : ArrayList<DeliveryModelItem>()

@Parcelize
data class DeliveryModelItem(
    val description: String,
    val id: Int,
    val imageUrl: String,
    val location: Location
):Parcelable

@Parcelize
data class Location(
    val address: String,
    val lat: Double,
    val lng: Double
):Parcelable