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
import com.test.delivery.room.entities.DeliveryEntity
import com.test.delivery.utilities.addAll
import com.test.delivery.utilities.isConnectingToInternet
import com.test.delivery.utilities.showActionBar
import com.test.delivery.utilities.showToast
import com.test.delivery.viewmodels.DeliveriesFragmentViewModel
import kotlinx.android.synthetic.main.fragment_deliveries.*

class DeliveriesFragment : Fragment(), DeliveryItemClickListener {

    private lateinit var list: ArrayList<Any>
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
        viewModel = ViewModelProviders.of(act).get(DeliveriesFragmentViewModel::class.java)
        act.showActionBar(toolbar, getString(R.string.things_to_deliver))
        rvDeliveries.apply {
            layoutManager = LinearLayoutManager(context)
            if (act.isConnectingToInternet()) {
                prBar.visibility = View.VISIBLE
                list=ArrayList()
                deliveryListAdapter = DeliveryAdapter(false, list, this@DeliveriesFragment)
                loadItems(act, false)
            } else {
                prBar.visibility = View.VISIBLE
                list=ArrayList()
                deliveryListAdapter = DeliveryAdapter(true, list, this@DeliveriesFragment)
                loadItems(act, true)
            }
            adapter = deliveryListAdapter
        }



    }

    private fun loadItems(act: AppCompatActivity, fromCache: Boolean) {
        try {
            when (fromCache) {
                false -> {
                    viewModel?.getDeliveries()?.observe(act, Observer {
                        when {
                            it != null -> {
                                deliveryListAdapter?.addAll(list, it)
                                val list= ArrayList<DeliveryEntity>()
                                it.forEach {item->
                                    val deliveryEntity=DeliveryEntity(item.description,item.location.address,item.imageUrl)
                                    list.add(deliveryEntity)
                                }
                                viewModel?.insertAll(list)?.observe(act,Observer{inserted->
                                    if(inserted){
                                        prBar.visibility = View.GONE
                                    }
                                })
                            }
                            else -> {
                                prBar.visibility = View.GONE
                                act.showToast(getString(R.string.server_problem))
                            }
                        }
                    })
                }
                true -> {
                    viewModel?.getLocalDeliveries()?.observe(act, Observer {
                        when {
                            it != null ->{
                                prBar.visibility = View.GONE
                                deliveryListAdapter?.addAll(list, it)
                            }
                            else->{
                                prBar.visibility = View.GONE
                                act.showToast(getString(R.string.no_cache_data))
                            }
                        }
                    })
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    override fun customClick(fromCache: Boolean, view: View, position: Int) {

      val action=  if (!fromCache)
            DeliveriesFragmentDirections.actionDeliveriesFragmentToDeliveryMapFragment(list[position] as DeliveryModelItem,null)
        else
            DeliveriesFragmentDirections.actionDeliveriesFragmentToDeliveryMapFragment(null,list[position] as DeliveryEntity)

        Navigation.findNavController(view).navigate(action)
    }

}