package com.mehmettekin.firebasefirestoreproject.data.repository

import com.google.firebase.auth.FirebaseUser
import com.mehmettekin.firebasefirestoreproject.data.Resource

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String,email: String, password: String): Resource<FirebaseUser>
    fun logout()
}