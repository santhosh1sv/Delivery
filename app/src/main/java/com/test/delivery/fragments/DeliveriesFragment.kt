package com.test.delivery.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.delivery.R
import com.test.delivery.adapters.DeliveryAdapter
import com.test.delivery.utilities.showActionBar
import kotlinx.android.synthetic.main.activity_main.*

class DeliveriesFragment : Fragment() {

    private var deliveryListAdapter: DeliveryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_deliveries,
            container, false
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).showActionBar(toolbar,getString(R.string.things_to_deliver))


    }

}