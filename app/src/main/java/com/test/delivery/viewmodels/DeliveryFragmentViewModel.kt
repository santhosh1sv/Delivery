package com.test.delivery.viewmodels

import androidx.lifecycle.LiveData
import com.test.delivery.apis.ApisRepository
import com.test.delivery.models.DeliveryModel

class DeliveryFragmentViewModel {

    fun getDeliveries(): LiveData<DeliveryModel>? {
        return ApisRepository.getDeliveries()
    }
}