package com.test.delivery.room.daos

import androidx.room.*
import com.test.delivery.room.entities.DeliveryEntity

@Dao
interface DeliveryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(delivery: List<DeliveryEntity>)

    @Query("DELETE FROM delivery")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(delivery: DeliveryEntity)

    @Query("SELECT * FROM delivery")
    fun getAll(): List<DeliveryEntity>
}