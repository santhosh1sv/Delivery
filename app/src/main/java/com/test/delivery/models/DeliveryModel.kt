package com.test.delivery.models

class DeliveryModel : ArrayList<DeliveryModelItem>()

data class DeliveryModelItem(
    val description: String,
    val id: Int,
    val imageUrl: String,
    val location: Location
)

data class Location(
    val address: String,
    val lat: Double,
    val lng: Double
)