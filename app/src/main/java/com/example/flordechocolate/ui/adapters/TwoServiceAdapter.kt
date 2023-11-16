package com.example.flordechocolate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flordechocolate.data.models.TwoServiceItemModel
import com.example.flordechocolate.databinding.ItemRecomendedBinding

class TwoServiceAdapter(var List: List<TwoServiceItemModel>): RecyclerView.Adapter<TwoServiceAdapter.ViewHolder>() {

    class ViewHolder(val item: ItemRecomendedBinding): RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemRecomendedBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val service = List[position]
        holder.item.textChTitle.text = service.title_two
        holder.item.textAmount.text = service.amount
        holder.item.textSubtitle.text = service.subtitle
        holder.item.textDescription.text = service.description
        //TODO_ load from url using glide
        holder.item.itemFrutos.setImageResource(service.icon_two.toInt())

    }

    override fun getItemCount(): Int {
        return List.size
    }
}