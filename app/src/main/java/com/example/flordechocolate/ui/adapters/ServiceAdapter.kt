package com.example.flordechocolate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flordechocolate.interfaces.OnServiceClickListener
import com.example.flordechocolate.data.models.ServiceItemModel
import com.example.flordechocolate.databinding.ItemServiceBinding

class ServiceAdapter(var List: List<ServiceItemModel>): RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    var Listener: OnServiceClickListener? = null


    class ViewHolder(val item: ItemServiceBinding): RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemServiceBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val service = List[position]
        holder.item.itemButtonTitle.text = service.title
        Glide.with(holder.item.root).load(service.icon).into(holder.item.itemPastelImage)
//        holder.item.itemPastelImage.setImageResource(service.icon.toInt())
        holder.item.root.setOnClickListener {
            Listener?.onClick(service)
        }
    }

    override fun getItemCount(): Int {
        return List.size
    }

    fun updateDataset(list: List<ServiceItemModel> ) {
        this.List = list
        notifyDataSetChanged()
    }
}