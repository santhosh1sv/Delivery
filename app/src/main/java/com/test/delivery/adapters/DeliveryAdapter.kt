package com.test.delivery.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.delivery.R
import com.test.delivery.listeners.DeliveryItemClickListener
import com.test.delivery.models.DeliveryModelItem
import com.test.delivery.room.entities.DeliveryEntity

class DeliveryAdapter(
    private val fromCache: Boolean,
    private val list: ArrayList<Any>,
    private val customClickListener: DeliveryItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DeliveryViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val deliveryViewHolder = holder as DeliveryViewHolder
        if (!fromCache)
            deliveryViewHolder.bind(list[position] as DeliveryModelItem)
        else
            deliveryViewHolder.bind(list[position] as DeliveryEntity)

        deliveryViewHolder.ctLayout.setOnClickListener {
            customClickListener.customClick(fromCache, it, position)
        }

    }

    class DeliveryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_delivery, parent, false)) {
        private val context: Context = parent.context
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        private val ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto)
        val ctLayout: ConstraintLayout = itemView.findViewById(R.id.ctLayout)


        fun bind(delivery: DeliveryModelItem) {
            try {
                val description = "${delivery.description} at"
                val address = delivery.location.address
                val photoUrl = delivery.imageUrl
                val fontReg = Typeface.createFromAsset(
                    context.assets,
                    "fonts/Barlow-Regular.ttf"
                )
                val fontSemiBold = Typeface.createFromAsset(
                    context.assets,
                    "fonts/Barlow-SemiBold.ttf"
                )
                tvDescription.text = description
                tvAddress.text = address
                tvDescription.typeface = fontReg
                tvAddress.typeface = fontSemiBold
                Picasso.with(context).load(photoUrl).fit().centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(ivPhoto)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        fun bind(delivery: DeliveryEntity) {
            try {
                val description = "${delivery.description} at"
                val address = delivery.address
                val photoUrl = delivery.imageUrl
                val fontReg = Typeface.createFromAsset(
                    context.assets,
                    "fonts/Barlow-Regular.ttf"
                )
                val fontSemiBold = Typeface.createFromAsset(
                    context.assets,
                    "fonts/Barlow-SemiBold.ttf"
                )
                tvDescription.text = description
                tvAddress.text = address
                tvDescription.typeface = fontReg
                tvAddress.typeface = fontSemiBold
                Picasso.with(context).load(photoUrl).fit().centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(ivPhoto)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }


    }


}