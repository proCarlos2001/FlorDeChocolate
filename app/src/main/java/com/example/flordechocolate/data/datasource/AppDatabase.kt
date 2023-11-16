package com.example.flordechocolate.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flordechocolate.data.db.dao.ProductsDao
import com.example.flordechocolate.data.db.dao.ServiceDao
import com.example.flordechocolate.data.models.ProductsItemModel
import com.example.flordechocolate.data.models.ServiceItemModel

@Database(entities = [ProductsItemModel::class, ServiceItemModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun serviceDao(): ServiceDao
    abstract fun productsDao(): ProductsDao
}