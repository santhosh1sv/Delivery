package com.test.delivery.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import com.test.delivery.R
import com.test.delivery.utilities.showActionBar
import kotlinx.android.synthetic.main.fragment_delivery_map.*
import kotlinx.android.synthetic.main.item_delivery.*

class DeliveryMapFragment : Fragment(), OnMapReadyCallback {

    private var args: DeliveryMapFragmentArgs? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_delivery_map,
            container, false
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val act = activity as AppCompatActivity
        act.showActionBar(toolbar, getString(R.string.delivery_details))
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        val fontLight = Typeface.createFromAsset(
            act.assets,
            "fonts/Barlow-Light.ttf"
        )
        val fontMed = Typeface.createFromAsset(
            act.assets,
            "fonts/Barlow-Medium.ttf"
        )
        tvDescription.typeface = fontLight
        tvAddress.typeface = fontMed
        args = arguments?.let { DeliveryMapFragmentArgs.fromBundle(it) }
        val item = args?.deliveryItem
        if (item != null) {
            val description = "${item.description} at"
            tvDescription.text = description
            tvAddress.text = item.location.address
            Picasso.with(act).load(item.imageUrl).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivPhoto)
        } else {
            val itm = args?.deliverEntity
            val description = "${itm?.description} at"
            tvDescription.text = description
            tvAddress.text = itm?.address
            Picasso.with(act).load(itm?.imageUrl).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivPhoto)
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        try {
            val loc = args?.deliveryItem?.location
            val latLng = loc?.let { LatLng(loc.lat, loc.lng) }
            map?.addMarker(latLng?.let {
                MarkerOptions().position(it).icon(
                    BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_GREEN
                    )
                )
            })
            val zoomLevel = 16.0f
            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
        }catch(e:Exception){
            e.printStackTrace()
        }
    }
}