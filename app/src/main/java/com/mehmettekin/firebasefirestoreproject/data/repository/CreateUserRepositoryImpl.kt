package com.mehmettekin.firebasefirestoreproject.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mehmettekin.firebasefirestoreproject.data.Resource
import com.mehmettekin.firebasefirestoreproject.data.utils.await
import javax.inject.Inject

class CreateUserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val db: FirebaseFirestore = Firebase.firestore
): CreateUserRepository {


    override suspend fun createuser(name: String,email: String
    ): Resource<FirebaseFirestore> {

        val user = hashMapOf(
            "id" to firebaseAuth.currentUser?.uid,
            "name" to name,
            "email" to email,
        )


      return try {
            val result = db.collection("users").add(user).await()
            Resource.Success(result.firestore)
        } catch (e: Exception) {
            Resource.Failure(e)
        }


    }
}