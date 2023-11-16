package com.example.flordechocolate.interfaces

import com.example.flordechocolate.data.models.ProductsItemModel

interface OnProductsClickListener {
    fun onClick(item: ProductsItemModel)
}