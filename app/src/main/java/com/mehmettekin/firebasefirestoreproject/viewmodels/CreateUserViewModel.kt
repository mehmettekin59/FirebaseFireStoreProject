package com.mehmettekin.firebasefirestoreproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.mehmettekin.firebasefirestoreproject.data.Resource
import com.mehmettekin.firebasefirestoreproject.data.repository.AuthRepository
import com.mehmettekin.firebasefirestoreproject.data.repository.CreateUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val repository: CreateUserRepository,
    private val authRepository: AuthRepository
): ViewModel(){

    private val _createUserFlow = MutableStateFlow<Resource<FirebaseFirestore>?>(null)
    val createUserFlow: StateFlow<Resource<FirebaseFirestore>?> = _createUserFlow

    val currentUser: FirebaseUser?
        get() = authRepository.currentUser

    val name = currentUser?.displayName
    private val email = currentUser?.email


    fun createUser() = viewModelScope.launch {
        _createUserFlow.value = Resource.Loading
        val result = repository.createuser(name.toString(),email.toString())
        _createUserFlow.value = result
    }
}