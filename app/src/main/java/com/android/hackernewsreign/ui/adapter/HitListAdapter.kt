package com.android.hackernewsreign.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hackernewsreign.R
import com.android.hackernewsreign.data.model.HitModel
import com.android.hackernewsreign.databinding.ItemHitBinding
import com.android.hackernewsreign.utils.covertTimeToText

class HitListAdapter (private val list: ArrayList<HitModel>,
                      private val listener: ItemActionsListener? = null
) : RecyclerView.Adapter<HitListAdapter.HitHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HitHolder(layoutInflater.inflate(R.layout.item_hit, parent, false))
    }

    override fun onBindViewHolder(holder: HitHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addItems(hits: List<HitModel>){
        val listIds = list.map { it.id }
        for (item in hits){
            if(!listIds.contains(item.id)){
                list.add(item)
            }
        }
        this.notifyDataSetChanged()
    }

    fun deleteItem(i: Int){
        list.removeAt(i)
        notifyDataSetChanged()
    }

    fun getHitModel(position: Int): HitModel  = list[position]

    inner class HitHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemHitBinding.bind(itemView)


        fun bind(hits: HitModel){
            binding.tvTitle.text = hits.title

            val date = itemView.context.covertTimeToText(hits.date)

            binding.tvAuthorAndDate.text = hits.author + " - " + date

            itemView.setOnClickListener {

                if(!hits.url.isNullOrEmpty()) {
                    listener?.onClickItem(it, hits.url)
                } else {
                    listener?.webNotAvailable()
                }
            }
        }
    }

    interface ItemActionsListener {
        fun onClickItem(v: View, url: String)
        fun webNotAvailable()
    }
}