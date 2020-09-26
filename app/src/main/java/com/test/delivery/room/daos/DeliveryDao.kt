package com.test.delivery.room.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.test.delivery.room.entities.DeliveryEntity

interface DeliveryDao {

    @Insert
    fun insertAll(vararg delivery: DeliveryEntity)

    @Delete
    fun delete(delivery: DeliveryEntity)

    @Update
    fun update(delivery: DeliveryEntity)

    @Query("SELECT * FROM delivery")
    fun getAll(): List<DeliveryEntity>
}