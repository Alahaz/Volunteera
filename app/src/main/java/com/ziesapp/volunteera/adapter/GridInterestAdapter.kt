package com.ziesapp.volunteera.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ziesapp.volunteera.Activity
import com.ziesapp.volunteera.Interest
import com.ziesapp.volunteera.R
import kotlinx.android.synthetic.main.list_interest.view.*

class GridInterestAdapter(private val listInterest: ArrayList<Interest>) :
    RecyclerView.Adapter<GridInterestAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(interest: Interest) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(interest.gambar)
                    .apply(RequestOptions().override(100, 100))
                    .into(iv_interest)
                tv_interest.text = interest.nama
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_interest, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listInterest.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listInterest[position])
    }
}