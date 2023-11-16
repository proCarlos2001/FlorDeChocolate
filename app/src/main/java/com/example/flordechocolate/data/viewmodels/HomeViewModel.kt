package com.example.flordechocolate.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flordechocolate.data.models.CompanyModel
import com.example.flordechocolate.data.models.ProductsDetailModel
import com.example.flordechocolate.data.models.ProductsItemModel
import com.example.flordechocolate.data.models.ServiceItemModel
import com.example.flordechocolate.data.repositories.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository): ViewModel() {
    private var _services: MutableLiveData<List<ServiceItemModel>> = MutableLiveData()
    val services: LiveData<List<ServiceItemModel>> get() = _services

    private var _company: MutableLiveData<CompanyModel> = MutableLiveData()
    val  company: LiveData<CompanyModel> get() = _company

    private var _product: MutableLiveData<List<ProductsItemModel>> = MutableLiveData()
    val product: LiveData<List<ProductsItemModel>> get() = _product

    private var _productt: MutableLiveData<ProductsItemModel> = MutableLiveData()
    val productt: LiveData<ProductsItemModel> get() = _productt

    private var _detail: MutableLiveData<ProductsDetailModel?> = MutableLiveData()
    val detail: LiveData<ProductsDetailModel?> get() = _detail

    fun services() {
        viewModelScope.launch {
            val services = repo.service()
            _services.postValue(services)
        }
    }

    fun company() {
        viewModelScope.launch {
            _company.postValue(repo.company())
        }
    }

    fun product(category: String?) {
        viewModelScope.launch {
            _product.postValue(repo.product(category))
        }
    }

    fun selected(item: ProductsItemModel) {
        _productt.postValue(item)
    }

    fun details(id: String) {
        viewModelScope.launch {
            _detail.postValue(repo.details(id))
        }
    }
}