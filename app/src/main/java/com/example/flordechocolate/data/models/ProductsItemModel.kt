package com.example.flordechocolate.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductsItemModel(
    @PrimaryKey var id: String ="",
    var name : String ="",
    var products_category: String ="",
    var amount_three: String ="",
    var image: String ="",
    var star: Int = 0,
    var description_two: String =""
    )