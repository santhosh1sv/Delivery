package com.test.delivery.apis

import com.test.delivery.models.DeliveryModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {

    @GET("/deliveries")
    fun getDeliveries(): Call<DeliveryModel?>?
}