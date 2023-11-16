package com.example.flordechocolate.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.flordechocolate.data.models.ProductsItemModel


@Dao
interface ProductsDao {

    @Query("SELECT * FROM products")
    suspend fun getAll(): List<ProductsItemModel>

    @Query("SELECT * FROM products WHERE products_category = :products_category")
    suspend fun getAllBySpeciality(products_category: String): List<ProductsItemModel>

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getCount(): Int

    @Insert
    suspend fun insertAll(services: List<ProductsItemModel>)


}