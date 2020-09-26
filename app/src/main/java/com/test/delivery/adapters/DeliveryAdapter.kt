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

class DeliveryAdapter(
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
        val delivery = list[position] as DeliveryModelItem
        val deliveryViewHolder = holder as DeliveryViewHolder
        deliveryViewHolder.bind(delivery)
        deliveryViewHolder.ctLayout.setOnClickListener {
            customClickListener.customClick(it, position)
        }

    }

    class DeliveryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_delivery, parent, false)) {
        private val context: Context
        private val tvDescription: TextView
        private val tvAddress: TextView
        private val ivPhoto: ImageView
        val ctLayout: ConstraintLayout


        init {
            context = parent.context
            tvDescription = itemView.findViewById(R.id.tvDescription)
            tvAddress = itemView.findViewById(R.id.tvAddress)
            ivPhoto = itemView.findViewById(R.id.ivPhoto)
            ctLayout = itemView.findViewById(R.id.ctLayout)
        }

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


    }


}