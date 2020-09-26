package com.test.delivery.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.delivery.R
import com.test.delivery.adapters.DeliveryAdapter
import com.test.delivery.listeners.DeliveryItemClickListener
import com.test.delivery.models.DeliveryModelItem
import com.test.delivery.utilities.addAll
import com.test.delivery.utilities.isConnectingToInternet
import com.test.delivery.utilities.showActionBar
import com.test.delivery.utilities.showToast
import com.test.delivery.viewmodels.DeliveriesFragmentViewModel
import kotlinx.android.synthetic.main.fragment_deliveries.*

class DeliveriesFragment : Fragment(), DeliveryItemClickListener {

    private val list: ArrayList<Any> = ArrayList()
    private var deliveryListAdapter: DeliveryAdapter? = null
    private var viewModel: DeliveriesFragmentViewModel? = null


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
        val act = activity as AppCompatActivity
        viewModel = ViewModelProviders.of(this).get(DeliveriesFragmentViewModel::class.java)
        act.showActionBar(toolbar, getString(R.string.things_to_deliver))
        rvDeliveries.apply {
            layoutManager = LinearLayoutManager(context)
            deliveryListAdapter = DeliveryAdapter(list, this@DeliveriesFragment)
            adapter = deliveryListAdapter
        }
        if (act.isConnectingToInternet()) {
            prBar.visibility = View.VISIBLE
            loadItems(act, false)
        } else {
            loadItems(act, true)
        }


    }

    private fun loadItems(act: AppCompatActivity, fromCache: Boolean) {
        try {
            when (fromCache) {
                false -> {
                    viewModel?.getDeliveries()?.observe(act, Observer {
                        when {
                            it != null -> {
                                prBar.visibility = View.GONE
                                deliveryListAdapter?.addAll(list, it)
                            }
                            else -> {
                                prBar.visibility = View.GONE
                                act.showToast(getString(R.string.server_problem))
                            }
                        }
                    })
                }
                true -> {

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    override fun customClick(view: View, position: Int) {
        val item = list[position] as DeliveryModelItem
        val action =
            DeliveriesFragmentDirections.actionDeliveriesFragmentToDeliveryMapFragment(item)
        Navigation.findNavController(view).navigate(action)
    }

}