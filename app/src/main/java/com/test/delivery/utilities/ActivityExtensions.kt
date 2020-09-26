package com.test.delivery.utilities

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun Activity.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Activity.isConnectingToInternet(): Boolean {
    val connMgr =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connMgr.activeNetworkInfo
    if (activeNetworkInfo != null) {
        if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
            return true
        } else if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
            return true
        }
    }
    return false
}

fun AppCompatActivity.showActionBar(toolbar: Toolbar, title: String) {
    toolbar.title = title
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
}