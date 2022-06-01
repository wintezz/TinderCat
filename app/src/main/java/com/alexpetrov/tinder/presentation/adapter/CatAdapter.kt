package com.alexpetrov.tinder.presentation.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexpetrov.tinder.R
import com.alexpetrov.tinder.data.model.Cat
import com.alexpetrov.tinder.presentation.utils.DiffUtilCallback
import com.facebook.drawee.view.SimpleDraweeView

class CatAdapter(
    private var data: ArrayList<Cat> = arrayListOf()
) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    fun setData(newData: List<Cat>) {
        val diffUtilsCallBack = DiffUtilCallback(data, newData)
        val diffResult = DiffUtil.calculateDiff(diffUtilsCallBack)
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cat_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: Cat) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                val uri = Uri.parse(data.url)

                itemView.findViewById<SimpleDraweeView>(R.id.image).setImageURI(uri)
            }
        }
    }
}