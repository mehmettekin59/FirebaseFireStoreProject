package com.mehmettekin.firebasefirestoreproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mehmettekin.firebasefirestoreproject.data.Resource
import com.mehmettekin.firebasefirestoreproject.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,

): ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signFlow: StateFlow<Resource<FirebaseUser>?> = _signFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null){
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = repository.login(email, password)
        _loginFlow.value = result
    }
    fun signup(name: String, email: String, password: String) = viewModelScope.launch {
        _signFlow.value = Resource.Loading
        val result = repository.signup(name,email, password)
        _signFlow.value = result
    }

    fun logout(){
        repository.logout()
        _loginFlow.value =  null
        _signFlow.value = null
    }
}