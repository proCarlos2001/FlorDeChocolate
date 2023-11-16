package com.example.flordechocolate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flordechocolate.data.models.ProductsItemModel
import com.example.flordechocolate.databinding.ItemProductsBinding
import com.example.flordechocolate.interfaces.OnProductsClickListener

class ProductsAdapter(var List: List<ProductsItemModel>): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    var Listener: OnProductsClickListener? = null


    class ViewHolder(val item: ItemProductsBinding): RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemProductsBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = List[position]
        holder.item.productsTextChTitle.text = product.name
        holder.item.productsFragmentSubtitle.text = product.products_category
        holder.item.productsTextAmount.text = product.amount_three
        Glide.with(holder.item.root).load(product.image).into(holder.item.productsItemFrutos)
//        holder.item.productsItemFrutos.setImageResource(product.image.toInt())
        holder.item.root.setOnClickListener {
            Listener?.onClick(product)
        }
    }

    override fun getItemCount(): Int {
        return List.size
    }

    fun updateDataset(list: List<ProductsItemModel> ) {
        this.List = list
        notifyDataSetChanged()
    }
}