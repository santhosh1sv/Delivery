package com.test.delivery.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.delivery.apis.ApisRepository
import com.test.delivery.models.DeliveryModel
import com.test.delivery.room.AppDatabase
import com.test.delivery.room.entities.DeliveryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DeliveriesFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val app: Application = application
    private val workerScope = CoroutineScope(Dispatchers.IO)
    private val db = AppDatabase(app)
    private var localDeliveries: MutableLiveData<List<DeliveryEntity>>? = null
    private var inserted: MutableLiveData<Boolean>? = null


    fun getDeliveries(): LiveData<DeliveryModel>? {
        return ApisRepository.getDeliveries()
    }

    fun insertAll(deliveryList: List<DeliveryEntity>): LiveData<Boolean>? {
        // if (inserted == null) {
        inserted = MutableLiveData()
        workerScope.launch {
            try {
                db.deliveryDao().deleteAll()
                db.deliveryDao().insertAll(deliveryList)
                inserted?.postValue(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        //}
        return inserted
    }

    fun getLocalDeliveries(): LiveData<List<DeliveryEntity>>? {
        //if (localDeliveries == null) {
        localDeliveries = MutableLiveData()
        workerScope.launch {
            val list = db.deliveryDao().getAll()
            localDeliveries?.postValue(list)
        }
        // }
        return localDeliveries


    }

    override fun onCleared() {
        super.onCleared()
        workerScope.cancel()
    }


}