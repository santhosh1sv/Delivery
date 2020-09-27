package com.test.delivery.room.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "delivery")
 class DeliveryEntity(
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "imageUrl") var imageUrl: String
) : Parcelable{
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}





