package com.example.flordechocolate.di

import androidx.room.Room
import com.example.flordechocolate.data.datasource.AppDatabase
import com.example.flordechocolate.data.datasource.MemoryDataSource
import com.example.flordechocolate.data.db.dao.ProductsDao
import com.example.flordechocolate.data.db.dao.ServiceDao
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataSourceModule = module {
    single { MemoryDataSource() }
    single<AppDatabase> { Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "dbflordechocolate").build() }
    single<ProductsDao> { get<AppDatabase>().productsDao() }
    single<ServiceDao> { get<AppDatabase>().serviceDao() }
    single { Firebase.auth }
    single { Firebase.firestore }
    single { Firebase.storage("gs://flordechocolate-c4g10.appspot.com") }
}