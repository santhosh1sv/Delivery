package com.test.delivery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.delivery.apis.ApisRepository
import com.test.delivery.models.DeliveryModel

class DeliveriesFragmentViewModel: ViewModel() {

    fun getDeliveries(): LiveData<DeliveryModel>? {
        return ApisRepository.getDeliveries()
    }
}