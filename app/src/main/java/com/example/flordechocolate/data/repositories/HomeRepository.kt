package com.example.flordechocolate.data.repositories

import com.example.flordechocolate.COMPANY_COLLECTION
import com.example.flordechocolate.DETAILS_COLLECTION
import com.example.flordechocolate.PRODUCTS_COLLECTION
import com.example.flordechocolate.SERVICE_COLLECTION
import com.example.flordechocolate.data.datasource.MemoryDataSource
import com.example.flordechocolate.data.db.dao.ProductsDao
import com.example.flordechocolate.data.db.dao.ServiceDao
import com.example.flordechocolate.data.models.CompanyModel
import com.example.flordechocolate.data.models.ProductsDetailModel
import com.example.flordechocolate.data.models.ProductsItemModel
import com.example.flordechocolate.data.models.ServiceItemModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class HomeRepository(private val memoryDataSource: MemoryDataSource, private val serviceDao: ServiceDao, private val productsDao: ProductsDao,
private val db: FirebaseFirestore, private val storage: FirebaseStorage) {
    suspend fun service(): List<ServiceItemModel> {
    //    delay(1000)
    //    return memoryDataSource.service()
    //    return serviceDao.getAll()
        val results = (db.collection(SERVICE_COLLECTION).get().await()).toObjects<ServiceItemModel>()
        val transformed = results.map {
            val ref = storage.reference
            val imageRef = ref.child(it.icon)
            it.icon = (imageRef.downloadUrl.await()).toString()
            return@map it
        }
        return transformed
    }

    suspend fun company(): CompanyModel? {
        val result = db.collection(COMPANY_COLLECTION).get().await()
        return result.documents.first().toObject<CompanyModel>()
    }

    suspend fun product(category: String?): List<ProductsItemModel> {
    //    val product = memoryDataSource.product()
    //    if(category != null) {
    //        return product.filter { it -> it.products_category == category }
    //    }
    //    return product
        val result: List<ProductsItemModel>
        if(category != null) {
            result = (db.collection(PRODUCTS_COLLECTION)
                .whereEqualTo("products_category", category).get().await().toObjects())
        }else{
            result = (db.collection(PRODUCTS_COLLECTION)
                .get().await().toObjects())
        }
        val transformed = result.map {
            val ref = storage.reference
            val imageRef = ref.child(it.image)
            it.image = (imageRef.downloadUrl.await()).toString()
            return@map it
        }
        return transformed

    }
    suspend fun details(id: String): ProductsDetailModel? {
        return (db.collection(DETAILS_COLLECTION).document(id).get().await()).toObject()
    }

}