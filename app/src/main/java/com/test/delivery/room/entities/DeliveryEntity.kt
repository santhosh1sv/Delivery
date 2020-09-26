package com.test.delivery.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "delivery")
data class DeliveryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "imageUrl") var imageUrl: String
)





