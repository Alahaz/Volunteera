package com.ziesapp.volunteera

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.list_activity.view.*

class ListActivityAdapter(private val listActivity: ArrayList<Activity>) :
    RecyclerView.Adapter<ListActivityAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(activiy: Activity) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(activiy.gambar)
                    .apply(RequestOptions().override(350, 550))
                    .into(img_item_photo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_activity, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listActivity.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(listActivity[position])
}