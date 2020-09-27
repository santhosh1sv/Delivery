package com.test.delivery.listeners

import android.view.View

interface DeliveryItemClickListener {

    fun customClick(fromCache:Boolean,view: View, position: Int)
}