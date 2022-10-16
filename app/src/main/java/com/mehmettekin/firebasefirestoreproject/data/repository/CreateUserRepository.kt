package com.mehmettekin.firebasefirestoreproject.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.mehmettekin.firebasefirestoreproject.data.Resource

interface CreateUserRepository {
    suspend fun createuser(name: String,email: String): Resource<FirebaseFirestore>
}