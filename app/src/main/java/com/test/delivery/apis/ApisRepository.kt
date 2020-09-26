package com.test.delivery.apis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.delivery.models.DeliveryModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApisRepository {
    var apiRequest: ApiRequest? = null
    var deliveries: MutableLiveData<DeliveryModel>? = null

    init {
        val retrofit = ApiClient.getClient()
        apiRequest = retrofit?.create(ApiRequest::class.java)
    }

    fun getDeliveries(): LiveData<DeliveryModel>? {
        deliveries = MutableLiveData()
        apiRequest?.getDeliveries()?.enqueue(object : Callback<DeliveryModel?> {
            override fun onResponse(
                call: Call<DeliveryModel?>,
                response: Response<DeliveryModel?>
            ) {
                try {
                    val deliveryModel = response.body()
                    when {
                        deliveryModel != null -> deliveries?.value = deliveryModel
                        else -> deliveries?.value = null
                    }
                } catch (e: Exception) {
                    deliveries?.value = null
                }

            }

            override fun onFailure(call: Call<DeliveryModel?>, t: Throwable) {
                deliveries?.value = null
            }

        })

        return deliveries
    }
}