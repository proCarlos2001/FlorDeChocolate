package com.example.flordechocolate.data.viewmodels

import android.graphics.Bitmap
import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flordechocolate.data.models.UserModel
import com.example.flordechocolate.data.repositories.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: LoginRepository): ViewModel() {
    private var _login: MutableLiveData<Boolean> = MutableLiveData()
    val login: LiveData<Boolean> get() = _login

    private var _signUp: MutableLiveData<Boolean> = MutableLiveData()
    val signUp: LiveData<Boolean> get() = _signUp

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    private var _user: MutableLiveData<UserModel?> = MutableLiveData()
    val user: LiveData<UserModel?> get() = _user

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                repo.login(email, password)
                _login.postValue(true)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

    fun signUp(email: String, password: String, name: String, gender: String) {
        viewModelScope.launch {
            try {
                repo.signUp(email, password, name, gender)
                _signUp.postValue(true)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

    fun currentUser() {
        viewModelScope.launch {
            try {
                _user.postValue(repo.currentUser())
            }catch (e: Exception) {
                _user.postValue(null)
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            repo.logOut()
            _user.postValue(null)
        }
    }

    fun uploadImage(image: Bitmap) {
        viewModelScope.launch {
            try {
                _user.postValue(repo.uploadImage(image))
            } catch (e: Exception) {
                _user.postValue(null)
            }
        }
    }
}